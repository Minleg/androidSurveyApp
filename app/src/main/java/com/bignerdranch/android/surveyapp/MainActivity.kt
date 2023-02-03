package com.bignerdranch.android.surveyapp

import android.os.Bundle
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

        questionTextView.setOnClickListener {
            val nextQuestion = surveyViewModel.getNextQuestion()
            questionTextView.setText(nextQuestion)
            resetCount()
        }
        updateCount() // initial set up
    }

    private fun resetCount() {
        /* This method resets the count of number of yeses and number of no's to zero */
        surveyViewModel.resetCount()
        yesCountTextView.text = surveyViewModel.yesCount.toString()
        noCountTextView.text = surveyViewModel.noCount.toString()
    }

    private fun updateCount() {
        /* This method is to store the data when device rotates or when activity is killed and restarted.
        * It saves the current question, and number of yeses and number of no's and updates the respective text views*/
        val question = surveyViewModel.getCurrentQuestion()
        val yesCount = surveyViewModel.getNumberOfYesCount()
        val noCount = surveyViewModel.getNumberOfNoCount()
        questionTextView.setText(question)
        yesCountTextView.text = yesCount.toString()
        noCountTextView.text = noCount.toString()
    }
}
