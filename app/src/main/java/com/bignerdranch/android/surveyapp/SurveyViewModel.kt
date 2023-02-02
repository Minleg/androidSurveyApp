package com.bignerdranch.android.surveyapp

import androidx.lifecycle.ViewModel

class SurveyViewModel : ViewModel() {

    val questions = mutableListOf(
        R.string.question_one,
        R.string.question_two,
        R.string.question_three,
        R.string.question_four
    )

    var yesCount = 0
    var noCount = 0

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

    fun getAllQuestions(): MutableList<Int> {
        return questions
    }
}
