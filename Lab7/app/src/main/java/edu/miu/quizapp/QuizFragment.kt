package edu.miu.quizapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_quiz.*
import kotlinx.coroutines.launch

class QuizFragment : BaseFragment() {

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
        quizModel = ViewModelProvider(this).get(QuizViewModel::class.java)
        btnNext.setOnClickListener {
            if (curAnswer != null) {
                nextQuestion()
            } else {
                context?.toast("Please choice your answer!!!")
            }
        }

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val clicked = group.findViewById(checkedId) as RadioButton
            curAnswer = clicked.text.toString()
        }
        return view
    }

    fun loadQuestions() {
        launch {
            context?.let {
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