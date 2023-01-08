package edu.miu.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import edu.miu.quizapp.model.QuizViewModel
import edu.miu.quizapp.R
import edu.miu.quizapp.database.Quiz
import edu.miu.quizapp.database.QuizDatabase
import edu.miu.quizapp.global.BaseFragment
import edu.miu.quizapp.toast
import kotlinx.android.synthetic.main.fragment_quiz.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizFragment : BaseFragment() {

    private lateinit var radioGroup: RadioGroup
    private lateinit var tvQuestion: TextView

    private lateinit var questions: List<Quiz>
    private lateinit var curQuiz: Quiz

    private var curAnswer: String? = null
    private var quizModel: QuizViewModel? = null
    private var answers: MutableList<String> = mutableListOf()
    private var firstQuestion = true
    private var questionIndex = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz, container, false)
        loadQuestions()

        radioGroup = view.radioGroup
        tvQuestion = view.tvQuestion

        quizModel = ViewModelProvider(this)[QuizViewModel::class.java]
        quizModel!!.initScore()
        view.btnNext.setOnClickListener {
            if (curAnswer != null) {
                checkAnswer(curAnswer!!)
                nextQuestion()
            } else {
                context?.toast("Please choice your answer!!!")
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId >= 0) {
                val clicked = group.findViewById(checkedId) as android.widget.RadioButton
                curAnswer = clicked.text.toString()
            }
        }
        return view
    }

    fun loadQuestions() {
        lifecycleScope.launch(Dispatchers.IO) {
            this@QuizFragment.context?.let {
                questions = QuizDatabase(it).getQuizDao().getAllQuizzes()
                nextQuestion()
            }
        }
    }

    fun nextQuestion() {
        if (!firstQuestion) {
            val answered = curAnswer?: ""
            answers.add(answered)
        }
        firstQuestion = false

        if (questionIndex == 2) {
            val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(
                correct = quizModel?.totalScore()?.value!!, answers = answers.toTypedArray()
            )
            Navigation.findNavController(requireView()).navigate(action)
            return
        }
        curQuiz = questions[questionIndex]
        tvQuestion.text = curQuiz.question

        val listQuestions = listOf(curQuiz.answerA, curQuiz.answerB, curQuiz.answerC, curQuiz.answerD)
        for (i in 0 until radioGroup.childCount) {
            (radioGroup.getChildAt(i) as RadioButton).text = listQuestions[i]
        }

        questionIndex += 1
        curAnswer = null
        radioGroup.clearCheck()
    }

    fun checkAnswer(ans: String) {
        if (curQuiz.answer == ans) {
            quizModel!!.curScore()
        }
    }
}