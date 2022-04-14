package com.example.quiz

data class Question(
    val id : Int,
    val image : Int,
    val optionZero : String,
    val optionOne : String,
    val optionTwo : String,
    val optionThree : String,
    val correctAnswer : Int,
    val questionText : String = "What country does the flag belong to?"
)
