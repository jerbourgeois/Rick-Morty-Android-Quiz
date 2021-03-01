package com.example.rickandmortyquiz.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyquiz.R

class GameViewModel : ViewModel() {

    private var questionIndex = 0
    private lateinit var questions: MutableList<Question>

    private val _scoreString = MutableLiveData<String>()
    val scoreString: LiveData<String>
        get() = _scoreString

    private val _question = MutableLiveData<Int>()
    val question: LiveData<Int>
        get() = _question

    private val _attempted = MutableLiveData<Boolean>()
    val attempted: LiveData<Boolean>
        get() = _attempted

    private val _isCorrect = MutableLiveData<Boolean>()
    val isCorrect: LiveData<Boolean>
        get() = _isCorrect

    private val _answer = MutableLiveData<Boolean>()
    val answer: LiveData<Boolean>
        get() = _answer

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    fun onGameFinish(){
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

    fun questionsAttempted() = questions.count { it.attempted }

    fun newGame() {
        resetQuestionList()
        questionIndex = 0
        _eventGameFinish.value = false
        updateQuestion()
    }

    private fun updateQuestion() {
        _question.value = questions[questionIndex].id
        _attempted.value = questions[questionIndex].attempted
        _isCorrect.value = questions[questionIndex].isCorrect
        _answer.value = questions[questionIndex].answer

        _scoreString.value = "Your Score: ${questions.count{ it.isCorrect }}/${questions.size}"

        if(questionsAttempted() == questions.size){
            onGameFinish()
        }
    }

    fun nextQuestion(){
        questionIndex++
        if (questionIndex == questions.size){
            questionIndex = 0
        }
        updateQuestion()
    }

    fun previousQuestion(){
        questionIndex--
        if(questionIndex == -1){
            questionIndex = questions.size - 1
        }
        updateQuestion()
    }

    fun answerQuestion(answer: Boolean ){
        questions[questionIndex].attempted = true
        questions[questionIndex].isCorrect = questions[questionIndex].answer == answer
        updateQuestion()
    }

    private fun resetQuestionList() {
        questions = mutableListOf(
                Question(R.string.question_1, false),
                Question(R.string.question_2, true),
                Question(R.string.question_3, true),
                Question(R.string.question_4, false),
                Question(R.string.question_5, false),
                Question(R.string.question_6, true),
                Question(R.string.question_7, false),
                Question(R.string.question_8, true),
                Question(R.string.question_9, false),
                Question(R.string.question_10, false),
                Question(R.string.question_11, false),
                Question(R.string.question_12, true),
                Question(R.string.question_13, false),
                Question(R.string.question_14, true),
                Question(R.string.question_15, false),
                Question(R.string.question_16, false),
                Question(R.string.question_17, true),
                Question(R.string.question_18, false),
                Question(R.string.question_19, false),
                Question(R.string.question_20, true)
        )
        //shuffle list
        questions.shuffle()
    }
}

data class Question(
        var id: Int,
        var answer: Boolean,
        var attempted: Boolean = false,
        var isCorrect: Boolean = false
)