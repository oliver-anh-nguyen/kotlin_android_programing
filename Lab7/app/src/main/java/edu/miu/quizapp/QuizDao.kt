package edu.miu.quizapp

import androidx.room.*

@Dao
interface QuizDao {
    @Insert
    suspend fun addMultipleQuizzes(vararg quiz: Quiz)

    @Query("SELECT * FROM quiz ORDER BY id")
    suspend fun getAllQuizzes(): List<Quiz>
}