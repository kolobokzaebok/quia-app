package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TrophyActivity : AppCompatActivity() {

    var tvUsername: TextView? = null
    var tvScore: TextView? = null
    var btFinish: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trophy)

        this.tvUsername = findViewById(R.id.tvUsername)
        this.tvScore = findViewById(R.id.tvScore)
        this.btFinish = findViewById(R.id.btnFinish)

        val username: String? = intent.getStringExtra(Constants.USERNAME)
        val results: String = "You answered correctly " +
                "${intent.getIntExtra(Constants.QUESTIONS_ANSWERED, 0)} " +
                "out of ${intent.getIntExtra(Constants.QUESTIONS_TOTAL, 0)}"

        this.tvUsername?.text = username
        this.tvScore?.text = results

        this.btFinish?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}