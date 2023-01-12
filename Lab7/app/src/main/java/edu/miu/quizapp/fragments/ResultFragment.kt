package edu.miu.quizapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import edu.miu.quizapp.R
import edu.miu.quizapp.databinding.FragmentResultBinding
import edu.miu.quizapp.global.BaseFragment
import kotlinx.android.synthetic.main.fragment_result.view.*

class ResultFragment : BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val score = ResultFragmentArgs.fromBundle(requireArguments()).correct
        val wrongAnswer = 15 - score
        val total = "$score / 15"
        val textResult = String.format(
            "Total Questions: 15\n\nCorrect Answers(Score): %d\n\nWrong Answer: %d\n\nYour Score is: %s",
            score, wrongAnswer, total
        )
        binding.tvResult.text = textResult
        val answers = ResultFragmentArgs.fromBundle(requireArguments()).answers
        binding.btnAgain.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_resultFragment_to_quizFragment)
        }
        binding.btnAnalysis.setOnClickListener {
            val action = ResultFragmentDirections.actionResultFragmentToAnalysisFragment(answers)
            Navigation.findNavController(requireView()).navigate(action)
        }
    }

}