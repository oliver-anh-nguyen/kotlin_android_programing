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
import edu.miu.quizapp.databinding.FragmentQuizBinding
import edu.miu.quizapp.global.BaseFragment
import edu.miu.quizapp.toast
import kotlinx.android.synthetic.main.fragment_quiz.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizFragment : BaseFragment<FragmentQuizBinding>(FragmentQuizBinding::inflate) {


    private var questions: List<Quiz> = emptyList()
    private var curQuiz: Quiz? = null

    private var curAnswer: String? = null
    private var quizModel: QuizViewModel? = null
    private var answers: MutableList<String> = mutableListOf()
    private var firstQuestion = true
    private var questionIndex = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadQuestions()
        quizModel = ViewModelProvider(this)[QuizViewModel::class.java]
        quizModel!!.initScore()

        binding.btnNext.setOnClickListener {
            if (curAnswer != null) {
                checkAnswer(curAnswer!!)
                nextQuestion()
            } else {
                context?.toast("Please choice your answer!!!")
            }
        }

        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId >= 0) {
                val clicked = group.findViewById(checkedId) as RadioButton
                curAnswer = clicked.text.toString()
            }

        }
    }

    private fun loadQuestions() {
        lifecycleScope.launch(Dispatchers.IO) {
            this@QuizFragment.context?.let {
                questions = QuizDatabase(it).getQuizDao().getAllQuizzes()
                nextQuestion()
            }
        }
    }

    private fun nextQuestion() {
        if (!firstQuestion) {
            val answered = curAnswer ?: ""
            answers.add(answered)
        }
        firstQuestion = false

        if (questionIndex == 15) {
            val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(
                correct = quizModel?.totalScore()?.value!!, answers = answers.toTypedArray()
            )
            Navigation.findNavController(requireView()).navigate(action)
            return
        }
        curQuiz = questions[questionIndex]

        curQuiz?.let { quiz ->
            val listQuestions =
                listOf(quiz.answerA, quiz.answerB, quiz.answerC, quiz.answerD)
            for (i in 0 until binding.radioGroup.childCount) {
                (binding.radioGroup.getChildAt(i) as RadioButton).text = listQuestions[i]
            }

            questionIndex += 1
            binding.radioGroup.clearCheck()
            curAnswer = null
        }

        lifecycleScope.launch {
            binding.tvQuestion.text = curQuiz?.question ?: ""
        }

    }

    private fun checkAnswer(ans: String) {
        if (curQuiz?.answer == ans) {
            quizModel!!.curScore()
        }
    }
}