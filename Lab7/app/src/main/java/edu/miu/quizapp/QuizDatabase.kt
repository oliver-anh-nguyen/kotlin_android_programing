package edu.miu.quizapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Quiz::class],
    version = 1
)

abstract class QuizDatabase(): RoomDatabase() {
    abstract fun getQuizDao() : QuizDao

    companion object {
        @Volatile private  var instance: QuizDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            QuizDatabase::class.java,
            "quizdatabase"
        ).build()
    }
}