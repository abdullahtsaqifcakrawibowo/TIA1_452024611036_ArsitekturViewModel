package com.example.unscrambleapp.ui

// Ini adalah kotak penyimpanan status UI kita (Unidirectional Data Flow)
data class GameUiState(
    val currentScrambledWord: String = "",
    val currentWordCount: Int = 1,
    val score: Int = 0,
    val isGuessedWordWrong: Boolean = false,
    val isGameOver: Boolean = false
)