package com.bignerdranch.android.surveyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

const val YES_COUNT = "com.bignerdranch.android.surveyapp.YES_COUNT"
const val NO_COUNT = "com.bignerdranch.android.surveyapp.NO_COUNT"

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var yesOptionButton: Button
    private lateinit var noOptionButton: Button
    private lateinit var resultButton: Button

    private val surveyViewModel: SurveyViewModel by lazy {
        ViewModelProvider(this).get(SurveyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.question_text_view)
        yesOptionButton = findViewById(R.id.yes_option_button)
        noOptionButton = findViewById(R.id.no_option_button)
        resultButton = findViewById(R.id.reset_button)

        yesOptionButton.setOnClickListener {
            surveyViewModel.updateYesCount() // updates the number of yes count in viewModel
        }

        noOptionButton.setOnClickListener {
            surveyViewModel.updateNoCount()
        }

        resultButton.setOnClickListener {
            val surveyIntent = Intent(this, SurveyResultActivity::class.java) // creates intent
            val yesCount = surveyViewModel.getNumberOfYesCount()
            val noCount = surveyViewModel.getNumberOfNoCount()
            surveyIntent.putExtra(YES_COUNT, yesCount)
            surveyIntent.putExtra(NO_COUNT, noCount)
            startActivity(surveyIntent) // starts the SurveyResultActivity which carries with it the values YES_COUNT and NO_COUNT
        }

        questionTextView.setOnClickListener {
             // updates the question
            val nextQuestion = surveyViewModel.getNextQuestion()
            questionTextView.setText(nextQuestion)
        }
        updateCount() // initial set up
    }

    private fun resetCount() {
        /* This method resets the count of number of yeses and number of no's to zero */
        surveyViewModel.resetCount()
    }

    private fun updateCount() {
        /* This method is to store the data when device rotates or when activity is killed and restarted.
        * It saves the current question, and number of yeses and number of no's and updates the respective text views*/
        val question = surveyViewModel.getCurrentQuestion()
        questionTextView.setText(question)

        val updatedYesCount = intent.getIntExtra(UPDATED_YES_COUNT, 0) // gets value from SurveyResultActivity
        val updatedNoCount = intent.getIntExtra(UPDATED_NO_COUNT, 0)
        surveyViewModel.setNumberOfYesCount(updatedYesCount) // Performs value update of number of Yes count
        surveyViewModel.setNumberOfNoCount(updatedNoCount)
    }
}
