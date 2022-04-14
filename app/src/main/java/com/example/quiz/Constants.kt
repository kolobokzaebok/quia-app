package com.example.quiz

object Constants {

    const val USERNAME: String = "username"
    const val QUESTIONS_TOTAL: String = "questionsTotal"
    const val QUESTIONS_ANSWERED: String = "questionsAnswered"

    fun getQuestions(): ArrayList<Question> {
        val questions = ArrayList<Question>()

        // add first question
        questions.add(
            Question(
                0,
                R.drawable.ic_flag_of_argentina,
                "Argentina",
                "Mexico",
                "China",
                "Russia",
                0
            )
        )
        // add second question
        questions.add(
            Question(
                1,
                R.drawable.ic_flag_of_australia,
                "Poland",
                "USA",
                "Australia",
                "Japan",
                2
            )
        )
        // add third question
        questions.add(
            Question(
                2,
                R.drawable.ic_flag_of_belgium,
                "Germany",
                "Denmark",
                "Finland",
                "Belgium",
                3
            )
        )
        // add fourth question
        questions.add(
            Question(
                3,
                R.drawable.ic_flag_of_brazil,
                "Argentina",
                "Uruguay",
                "Brazil",
                "Canada",
                2
            )
        )
        // add fifth question
        questions.add(
            Question(
                4,
                R.drawable.ic_flag_of_denmark,
                "Denmark",
                "England",
                "Italy",
                "Slovenia",
                0
            )
        )
        // add sixth question
        questions.add(
            Question(
                5,
                R.drawable.ic_flag_of_fiji,
                "Fiji",
                "Jamaica",
                "Morocco",
                "Vietnam",
                0
            )
        )
        // add seventh question
        questions.add(
            Question(
                6,
                R.drawable.ic_flag_of_germany,
                "Austria",
                "Germany",
                "Belgium",
                "Portugal",
                1
            )
        )
        // add eighth question
        questions.add(
            Question(
                7,
                R.drawable.ic_flag_of_india,
                "Bangladesh",
                "India",
                "Spain",
                "Philippines",
                1
            )
        )
        // add ninth question
        questions.add(
            Question(
                8,
                R.drawable.ic_flag_of_kuwait,
                "Kuwait",
                "Lebanon",
                "Israel",
                "Palestine",
                0
            )
        )
        // add tenth question
        questions.add(
            Question(
                9,
                R.drawable.ic_flag_of_new_zealand,
                "Australia",
                "New Zealand",
                "England",
                "Czech Republic",
                1
            )
        )
        return questions
    }

}