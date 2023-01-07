package edu.miu.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import edu.miu.quizapp.R
import edu.miu.quizapp.database.Quiz
import edu.miu.quizapp.database.QuizDatabase
import edu.miu.quizapp.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        view.btn_start.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.quizFragment)
        }
        prepareDatabase()
        return view
    }

    fun prepareDatabase() {
        val quiz1 = Quiz(1,"1) Kotlin is developed by?","JetBrains","Google","JetBrains","Microsoft","Adobe")
        val quiz2 = Quiz(2,"2) Which of following is used to handle null exceptions in Kotlin?","Elvis Operator","Range","Sealed Class","Elvis Operator","Lambda function")
        lifecycleScope.launch(Dispatchers.IO) {
            this@HomeFragment.context?.let {
                QuizDatabase(it).getQuizDao().deleteAllQuiz()
                QuizDatabase(it).getQuizDao().addMultipleQuizzes(quiz1, quiz2)
            }
        }
    }
}