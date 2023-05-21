package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    //создаём резервное поле (backing property) для сохранения счета
    private var _score = 0
    val score: Int
        get() = _score

    private var currentWordCount = 0

    //переменная инициализируется в начале запуска программы по методу getnextword()
    private lateinit var _currentScrambledWord: String
    //защищаем данное поле от изменений извне
    //всё ещё можно изменять данное поле внутри viewmodel
    val currentScrambledWord: String
        get() = _currentScrambledWord

    //создаем лист из слов которые мы будем использовать в игре
    private var wordsList: MutableList<String> = mutableListOf()
    //создаем переменную для хранения следующего слова
    private lateinit var currentWord: String

    init {
        getNextWord()
    }

    private fun getNextWord() {
        //получаем случайное слово из листа "все слова"
        currentWord = allWordsList.random()
        //переводим слово в массив символов и шаффлим
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()
        //происходит проверка на то, что зашаффленное слово не то же самое
        while(String(tempWord).equals(currentWord,false)){
            tempWord.shuffle()
        }
        //обновляем и добавляем слово в переменную текущего зашафленого слова
        if(wordsList.contains(currentWord)){
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++currentWordCount
            wordsList.add(currentWord)
        }
    }

    //вспомогательный метод чтобы проверять список слов
    //и создавать новые
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }
}