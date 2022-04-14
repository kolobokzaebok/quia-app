package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEnter: Button = findViewById(R.id.btnEnter)
        val etName: EditText = findViewById(R.id.etName)

        btnEnter.setOnClickListener {
            if (etName.text.isEmpty())
                Toast.makeText(this, "Please, enter your name", Toast.LENGTH_SHORT).show()
            else {
                val intent: Intent = Intent(this, QuestionsActivity::class.java)
                intent.putExtra(Constants.USERNAME, etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}