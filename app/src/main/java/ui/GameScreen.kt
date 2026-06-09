package com.example.unscrambleapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unscrambleapp.R

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel() // Menginisialisasi ViewModel
) {
    // UI mendengarkan perubahan dari UiState secara aman (UDF & StateFlow)
    val gameUiState by gameViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Indikator Kata ke-berapa
        Text(text = stringResource(R.string.word_count, gameUiState.currentWordCount))
        Spacer(modifier = Modifier.height(16.dp))

        // Kata yang Diacak
        Text(text = gameUiState.currentScrambledWord, fontSize = 45.sp)
        Spacer(modifier = Modifier.height(32.dp))

        // Kolom Input Jawaban
        OutlinedTextField(
            value = gameViewModel.userGuess,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { gameViewModel.updateUserGuess(it) },
            label = { Text(stringResource(R.string.enter_your_word)) },
            isError = gameUiState.isGuessedWordWrong,
            supportingText = {
                if (gameUiState.isGuessedWordWrong) {
                    Text(stringResource(R.string.wrong_guess))
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Aksi (Lewati & Kirim)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(onClick = { gameViewModel.skipWord() }) {
                Text(stringResource(R.string.skip))
            }
            Button(onClick = { gameViewModel.checkUserGuess() }) {
                Text(stringResource(R.string.submit))
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Skor Saat Ini
        Text(text = stringResource(R.string.score, gameUiState.score), fontSize = 20.sp)
    }

    // Dialog Muncul Jika Game Over
    if (gameUiState.isGameOver) {
        AlertDialog(
            onDismissRequest = { /* Cegah tutup sembarangan */ },
            title = { Text(stringResource(R.string.game_over_title)) },
            text = { Text(stringResource(R.string.game_over_message, gameUiState.score)) },
            confirmButton = {
                TextButton(onClick = { gameViewModel.resetGame() }) {
                    Text(stringResource(R.string.play_again))
                }
            }
        )
    }
}