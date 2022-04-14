package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private val SUBMIT: String = "Submit"
    private val NEXT: String = "Next"
    private val FINISH: String = "Finish"

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var submitButton: Button? = null
    private var ivFlag: ImageView? = null
    private var optionZero: TextView? = null
    private var optionOne: TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree: TextView? = null
    private var tvQuestion: TextView? = null

    private var currentOption: Int = 0
    private var currentQuestion: Int = 0
    private var questionsAnswered: Int = 0

    private var optionSelected: Boolean = false
    private var resultShown: Boolean = false
    private var pauseSelection: Boolean = false

    private var questions: ArrayList<Question>? = null

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        this.progressBar = findViewById(R.id.progressBar)
        this.tvProgress = findViewById(R.id.tvProgress)
        this.submitButton = findViewById(R.id.submitButton)
        this.ivFlag = findViewById(R.id.ivFlag)
        this.optionZero = findViewById(R.id.optionZero)
        this.optionOne = findViewById(R.id.optionOne)
        this.optionTwo = findViewById(R.id.optionTwo)
        this.optionThree = findViewById(R.id.optionThree)
        this.tvQuestion = findViewById(R.id.tvQuestion)

        this.optionZero?.setOnClickListener(this)
        this.optionOne?.setOnClickListener(this)
        this.optionTwo?.setOnClickListener(this)
        this.optionThree?.setOnClickListener(this)
        this.submitButton?.setOnClickListener(this)

        this.questions = Constants.getQuestions()

        this.username = intent.getStringExtra(Constants.USERNAME)

        setQuestionView()
    }

    private fun setQuestionView() {
        val question: Question = this.questions!![this.currentQuestion]

        this.ivFlag?.setImageResource(question.image)
        this.tvQuestion?.text = question.questionText
        this.optionZero?.text = question.optionZero
        this.optionOne?.text = question.optionOne
        this.optionTwo?.text = question.optionTwo
        this.optionThree?.text = question.optionThree

        val progress: String = "${this.currentQuestion + 1}/${progressBar?.max}"
        this.tvProgress?.text = progress
        this.progressBar?.progress = this.currentQuestion + 1
    }

    private fun setDefaultOptionView() {
        this.optionSelected = false
        this.resultShown = false
        arrayOf(this.optionZero, this.optionOne, this.optionTwo, this.optionThree).forEach {
            it?.setTextColor(Color.parseColor("#7A8089"))
            it?.typeface = Typeface.DEFAULT
            it?.background = ContextCompat.getDrawable(
                this@QuestionsActivity,
                R.drawable.option_default_drawable
            )
        }
        this.submitButton?.text = SUBMIT
    }

    private fun setSelectedOptionView(optionView: TextView, optionPosition: Int) {
        if (!this.pauseSelection) {
            this.currentOption = optionPosition
            setDefaultOptionView()
            optionView.setTextColor(Color.parseColor("#363A43"))
            optionView.setTypeface(optionView.typeface, Typeface.BOLD)
            optionView.background = ContextCompat.getDrawable(
                this@QuestionsActivity,
                R.drawable.option_selected_drawable
            )
            this.optionSelected = true
        }
    }

    private fun showResults() {
        if (this.currentQuestion == this.questions!!.size - 1)
            this.submitButton?.text = FINISH
        else
            this.submitButton?.text = NEXT
        this.resultShown = true
        val correctAnswerOption: Int = this.questions!![this.currentQuestion].correctAnswer
        if (this.currentOption == correctAnswerOption) {
            this.questionsAnswered += 1
            setValidatedOptionView(this.currentOption, R.drawable.option_correct_drawable)
        } else {
            setValidatedOptionView(this.currentOption, R.drawable.option_wrong_drawable)
            setValidatedOptionView(correctAnswerOption, R.drawable.option_correct_drawable)
        }
    }

    private fun setValidatedOptionView(optionNumber: Int, drawableInt: Int) {
        when (optionNumber) {
            0 -> {
                this.optionZero?.background = ContextCompat.getDrawable(
                    this@QuestionsActivity,
                    drawableInt
                )
            }
            1 -> {
                this.optionOne?.background = ContextCompat.getDrawable(
                    this@QuestionsActivity,
                    drawableInt
                )
            }
            2 -> {
                this.optionTwo?.background = ContextCompat.getDrawable(
                    this@QuestionsActivity,
                    drawableInt
                )
            }
            3 -> {
                this.optionThree?.background = ContextCompat.getDrawable(
                    this@QuestionsActivity,
                    drawableInt
                )
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.optionZero -> {
                this.optionZero?.let { setSelectedOptionView(it, 0) }
            }
            R.id.optionOne -> {
                this.optionOne?.let { setSelectedOptionView(it, 1) }
            }
            R.id.optionTwo -> {
                this.optionTwo?.let { setSelectedOptionView(it, 2) }
            }
            R.id.optionThree -> {
                this.optionThree?.let { setSelectedOptionView(it, 3) }
            }
            R.id.submitButton -> {
                if (!this.optionSelected) {
                    Toast.makeText(this, "Please, select an option first", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    if (resultShown) {
                        if (this.currentQuestion == this.questions!!.size - 1) {
                            val intent: Intent = Intent(this, TrophyActivity::class.java)
                            intent.putExtra(Constants.USERNAME, this.username)
                            intent.putExtra(Constants.QUESTIONS_TOTAL, this.questions!!.size)
                            intent.putExtra(Constants.QUESTIONS_ANSWERED, this.questionsAnswered)
                            startActivity(intent)
                            finish()
                        } else {
                            this.currentQuestion += 1
                            setQuestionView()
                            setDefaultOptionView()
                            this.pauseSelection = false
                        }
                    } else {
                        showResults()
                        this.pauseSelection = true
                    }
                }
            }
        }
    }
}