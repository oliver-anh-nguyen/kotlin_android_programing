package edu.miu.quizapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import edu.miu.quizapp.R
import edu.miu.quizapp.database.Quiz
import edu.miu.quizapp.database.QuizDatabase
import edu.miu.quizapp.databinding.FragmentAnalysisBinding
import edu.miu.quizapp.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_analysis.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnalysisFragment : BaseFragment<FragmentAnalysisBinding>(FragmentAnalysisBinding::inflate) {

    private lateinit var questions: List<Quiz>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val answers = ResultFragmentArgs.fromBundle(requireArguments()).answers
        lifecycleScope.launch(Dispatchers.IO) {
            this@AnalysisFragment.context?.let {
                questions = QuizDatabase(it).getQuizDao().getAllQuizzes()
                questions.forEach { q ->
                    q.answer
                }
                binding.listAnalysis.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    popularDataAnalysis(questions, answers.toList())
                )
            }
        }
    }

    private fun popularDataAnalysis(questions: List<Quiz>, answers: List<String>): List<String> {
        val items = mutableListOf<String>()
        questions.forEachIndexed { index, quiz ->
            val listItem = String.format(
                "%s\nYour Ans: %s\nCorrect Ans: %s",
                quiz.question,
                answers[index],
                quiz.answer
            )
            items.add(listItem)
        }
        return items
    }

}