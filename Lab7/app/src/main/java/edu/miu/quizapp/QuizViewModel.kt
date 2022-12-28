package edu.miu.quizapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel: ViewModel() {
    private var score = 0
    private var scoreLiveData = MutableLiveData<Int>()

    fun initScore(): MutableLiveData<Int> {
        scoreLiveData.value = score
        return scoreLiveData
    }

    fun curScore() {
        score += 1
        scoreLiveData.value = score
    }

    fun totalScore(): MutableLiveData<Int> {
        return  scoreLiveData
    }

}