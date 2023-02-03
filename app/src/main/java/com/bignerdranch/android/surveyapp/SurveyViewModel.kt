package com.bignerdranch.android.surveyapp

import androidx.lifecycle.ViewModel

class SurveyViewModel : ViewModel() {

    var yesCount = 0
    var noCount = 0
    var index = 0

    private val questions = mutableMapOf<Int, List<Int>>(
        R.string.question_one to listOf(yesCount, noCount),
        R.string.question_two to listOf(yesCount, noCount),
        R.string.question_three to listOf(yesCount, noCount),
        R.string.question_four to listOf(yesCount, noCount)
    )

    fun getNextQuestion(): Int {
        index = (index + 1) % questions.size
        val question = questions.keys.elementAt(index)
        return question
    }

    fun getCurrentQuestion(): Int {
        val currentQuestion = questions.keys.elementAt(index)
        return currentQuestion
    }

    fun updateYesCount(): Int {
        yesCount += 1
        return yesCount
    }

    fun updateNoCount(): Int {
        noCount += 1
        return noCount
    }

    fun resetCount() {
        yesCount = 0
        noCount = 0
    }

    fun getNumberOfYesCount(): Int {
        return yesCount
    }

    fun getNumberOfNoCount(): Int {
        return noCount
    }

    fun getAllQuestions(): MutableSet<Int> {
        return questions.keys
    }
}
