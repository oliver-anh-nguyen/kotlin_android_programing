package edu.miu.quizapp.database

import androidx.room.*

@Dao
interface QuizDao {
    @Insert
    fun addMultipleQuizzes(vararg quiz: Quiz)

    @Query("SELECT * FROM quiz ORDER BY id")
    fun getAllQuizzes(): List<Quiz>

    @Query("DELETE FROM quiz WHERE 1=1")
    fun deleteAllQuiz()
}