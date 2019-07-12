package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    var countNegativeAnswers = 0

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        val (isValid, errorMessage) = question.validate(answer)
        return if (!isValid) {
            errorMessage to status.color
        } else {
            if (question == Question.IDLE || question.answer.contains(answer.toLowerCase())) {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            } else {
                ++this.countNegativeAnswers
                status = status.nextStatus()
                if (this.countNegativeAnswers == 3) {
                    this.countNegativeAnswers = 0
                    this.status = Status.NORMAL
                    this.question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                } else {
                    "Это не правильный ответ!\n${question.question}" to status.color
                }
            }
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0)); //красный

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answer: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION

            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer[0].toString() in "A".."Z" || answer[0].toString() in "А".."Я")
                    return (true to "")
                else
                    return (false to "Имя должно начинаться с заглавной буквы")
            }
        },
        PROFESSION("Назови мою профессию", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL

            override fun validate(answer: String): Pair<Boolean, String> {
                if (answer[0].toString() in "a".."z" || answer[0].toString() in "а".."я")
                    return (true to "")
                else
                    return (false to "Профессия должна начинаться со строчной буквы")
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY

            override fun validate(answer: String): Pair<Boolean, String> {
                val regex = Regex(pattern = "(.+\\d.*)")
                if (!answer.matches(regex))
                    return (true to "")
                else
                    return (false to "Материал не должен содержать цифр")
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL

            override fun validate(answer: String): Pair<Boolean, String> {
                val regex = Regex(pattern = "\\d+")
                if (answer.matches(regex))
                    return (true to "")
                else
                    return (false to "Год моего рождения должен содержать только цифры")
            }
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE

            override fun validate(answer: String): Pair<Boolean, String> {
                val regex = Regex(pattern = "[-+]?\\d+")
                if (answer.matches(regex) && answer.length == 7)
                    return (true to "")
                else
                    return (false to "Серийный номер содержит только цифры, и их 7")
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE

            override fun validate(answer: String): Pair<Boolean, String> {
                return (true to "")
            }
        };

        abstract fun nextQuestion(): Question
        abstract fun validate(answer: String): Pair<Boolean, String>
    }
}