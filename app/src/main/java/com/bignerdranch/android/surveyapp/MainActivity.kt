package com.bignerdranch.android.surveyapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var yesOptionButton: Button
    private lateinit var noOptionButton: Button
    private lateinit var yesCountTextView: TextView
    private lateinit var noCountTextView: TextView
    private lateinit var resetButton: Button

    private val surveyViewModel: SurveyViewModel by lazy {
        ViewModelProvider(this).get(SurveyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.question_text_view)
        yesOptionButton = findViewById(R.id.yes_option_button)
        noOptionButton = findViewById(R.id.no_option_button)
        yesCountTextView = findViewById(R.id.number_of_yes_text_view)
        noCountTextView = findViewById(R.id.number_of_no_text_view)
        resetButton = findViewById(R.id.reset_button)

        yesOptionButton.setOnClickListener {
            val yesCount = surveyViewModel.updateYesCount()
            yesCountTextView.text = yesCount.toString()
        }

        noOptionButton.setOnClickListener {
            val noCount = surveyViewModel.updateNoCount()
            noCountTextView.text = noCount.toString()
        }

        resetButton.setOnClickListener {
            resetCount()
        }

        val questions = surveyViewModel.getAllQuestions()
        val question = questions.first()
        var index = 0
        questionTextView.setText(question)
        updateCount()

        questionTextView.setOnClickListener {
            index = (index + 1) % questions.size
            val next_question = questions[index]
            questionTextView.setText(next_question)
            resetCount()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun resetCount() {
        surveyViewModel.resetCount()
        yesCountTextView.text = surveyViewModel.yesCount.toString()
        noCountTextView.text = surveyViewModel.noCount.toString()
    }

    private fun updateCount() {
        val yesCount = surveyViewModel.getNumberOfYesCount()
        val noCount = surveyViewModel.getNumberOfNoCount()
        yesCountTextView.text = yesCount.toString()
        noCountTextView.text = noCount.toString()
    }

}