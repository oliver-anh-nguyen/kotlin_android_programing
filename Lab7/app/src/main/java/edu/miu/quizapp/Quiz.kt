package edu.miu.quizapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Quiz (
        @PrimaryKey
        val id: Int,
        var question: String,
        var answer: String,
        val answerA: String,
        val answerB: String,
        val answerC: String,
        val answerD: String
        ) : Serializable