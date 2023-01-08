package edu.miu.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import edu.miu.quizapp.R
import edu.miu.quizapp.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_result.view.*

class ResultFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_result, container, false)
        val score = ResultFragmentArgs.fromBundle(requireArguments()).correct
        val wrongAnswer = 15 - score
        val total = "$score / 15"
        val textResult = String.format(
            "Total Questions: 15\n\nCorrect Answers(Score): %d\n\nWrong Answer: %d\n\nYour Score is: %s",
            score, wrongAnswer, total
        )
        view.tvResult.text = textResult
        val answers = ResultFragmentArgs.fromBundle(requireArguments()).answers
        view.btnAgain.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_resultFragment_to_quizFragment)
        }
        view.btnAnalysis.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToAnalysisFragment(answers)
            Navigation.findNavController(requireView()).navigate(action)
        }
        return view
    }

}