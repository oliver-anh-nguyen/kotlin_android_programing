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
import edu.miu.quizapp.databinding.FragmentHomeBinding
import edu.miu.quizapp.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    init {
        prepareDatabase()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStart.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.quizFragment)
        }
    }

    private fun prepareDatabase() {
        val quiz1 = Quiz(
            1,
            "1) Kotlin is developed by?",
            "JetBrains",
            "Google",
            "JetBrains",
            "Microsoft",
            "Adobe"
        )
        val quiz2 = Quiz(
            2,
            "2) Which of following is used to handle null exceptions in Kotlin?",
            "Elvis Operator",
            "Range",
            "Sealed Class",
            "Elvis Operator",
            "Lambda function"
        )
        val quiz3 = Quiz(
            3,
            "3) Which file extension is used to save Kotlin files",
            ".kt or .kts",
            ".java",
            ".kot",
            ".kt or .kts",
            ".android"
        )
        val quiz4 = Quiz(
            4,
            "4) All classes in Kotlin classes are by default?",
            "final",
            "public",
            "final",
            "sealed",
            "abstract"
        )
        val quiz5 = Quiz(
            5,
            "5) What is an immutable variable?",
            "A variable that cannot change, read-only",
            "A variable that cannot change, read-only",
            "A variable that can be changed",
            "A variable used for string interpolation",
            "All of the above"
        )
        val quiz6 = Quiz(
            6,
            "6) Which of the following extension methods are used in Kotlin?",
            "All of the above",
            "Read texts () & Headlines ()",
            "Buffer reader ()",
            "Read each line ()",
            "All of the above"
        )
        val quiz7 = Quiz(
            7,
            "7) There are two types of constructors in Kotlin which are",
            "Primary & Secondary constructor",
            "Primary & Secondary constructor",
            "Default & No-arg constructor",
            "Parameterized & constant Constructor",
            "None of the above"
        )
        val quiz8 = Quiz(
            8,
            "8) Which of the following is not the basic data type in Kotlin?",
            "Lists",
            "Numbers",
            "Strings",
            "Arrays",
            "Lists"
        )
        val quiz9 = Quiz(
            9,
            "9) Kotlin was developed under the _ license",
            "Apache 2.0",
            "Apache 1.0",
            "Apache 2.0",
            "Apache 1.1",
            "None of the above"
        )
        val quiz10 = Quiz(
            10,
            "10) Which keyword is used to declare a function?",
            "fun",
            "fun",
            "define",
            "decl",
            "function"
        )
        val quiz11 = Quiz(
            11,
            "11) How can you create a variable with the floating number 2.8?",
            "val num = 2.8",
            "float num = 2.8",
            "double num = 2.8",
            "num = 2.8 float",
            "val num = 2.8"
        )
        val quiz12 = Quiz(
            12,
            "12) What is the output of the following code: println(5 > 3 && 5 < 10)",
            "true",
            "true",
            "2",
            "false",
            "5"
        )
        val quiz13 = Quiz(
            13,
            "13) Which operator can be used to compare two values?",
            "==",
            "><",
            "<>",
            "==",
            "="
        )
        val quiz14 = Quiz(
            14,
            "14) Which property can be used to find the length of a string?",
            "The length property",
            "The sizeof property",
            "The size property",
            "The len property",
            "The length property"
        )
        val quiz15 = Quiz(
            15,
            "15) Which keyword is used to return a value inside a function?",
            "return",
            "void",
            "get",
            "return",
            "break"
        )
        lifecycleScope.launch(Dispatchers.IO) {
            this@HomeFragment.context?.let {
                QuizDatabase(it).getQuizDao().deleteAllQuiz()
                QuizDatabase(it).getQuizDao().addMultipleQuizzes(quiz1, quiz2, quiz3, quiz4, quiz5, quiz6, quiz7, quiz8, quiz9, quiz10, quiz11, quiz12, quiz13, quiz14, quiz15)
            }
        }
    }
}