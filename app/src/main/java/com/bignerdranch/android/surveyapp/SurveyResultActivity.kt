package com.bignerdranch.android.surveyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

const val UPDATED_YES_COUNT = "com.bignerdranch.android.surveyapp.UPDATED_YES_COUNT"
const val UPDATED_NO_COUNT = "com.bignerdranch.android.surveyapp.UPDATED_NO_COUNT"

class SurveyResultActivity : AppCompatActivity() {

    lateinit var numberOfYeses: TextView
    lateinit var numberOfNos: TextView
    lateinit var resetButton: Button
    lateinit var continueButton: Button

    private val surveyViewModel: SurveyViewModel by lazy {
        ViewModelProvider(this).get(SurveyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_result)

        numberOfYeses = findViewById(R.id.number_of_yes_text_view)
        numberOfNos = findViewById(R.id.number_of_no_text_view)
        resetButton = findViewById(R.id.reset_button)
        continueButton = findViewById(R.id.continue_button)

        val yesCount = intent.getIntExtra(YES_COUNT, 100)
        surveyViewModel.setNumberOfYesCount(yesCount)
        numberOfYeses.text = yesCount.toString()

        val noCount = intent.getIntExtra(NO_COUNT, 100)
        surveyViewModel.setNumberOfNoCount(noCount)
        numberOfNos.text = noCount.toString()

        resetButton.setOnClickListener {
            surveyViewModel.resetCount()
            numberOfYeses.text = surveyViewModel.getNumberOfYesCount().toString()
            numberOfNos.text = surveyViewModel.getNumberOfNoCount().toString()
        }

        continueButton.setOnClickListener {
            // surveyViewModel.setNumberOfYesCount(yesCount)
            // surveyViewModel.setNumberOfNoCount(noCount)
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putExtra(UPDATED_YES_COUNT, surveyViewModel.getNumberOfYesCount())
            mainIntent.putExtra(UPDATED_NO_COUNT, surveyViewModel.getNumberOfNoCount())
            startActivity(mainIntent)
        }
    }
}
