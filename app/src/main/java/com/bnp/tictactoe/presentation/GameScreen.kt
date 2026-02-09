package com.bnp.tictactoe.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bnp.tictactoe.R
import com.bnp.tictactoe.domain.usecases.PlayTurnUseCase
import com.bnp.tictactoe.presentation.mappers.toBoardUi

@Composable
fun GameScreen(
    modifier: Modifier,
    state: GameUiState,
    onAction: (GameUiAction) -> Unit
) {
    val boardListItem = state.board.toBoardUi().boardListItem

    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.it_is_player_turn, state.currentPlayer.character))
        LazyVerticalGrid(
            columns = GridCells.Fixed(state.board.numberOfColumns),
            modifier = modifier
                .size(300.dp)
                .padding(5.dp),
            userScrollEnabled = false
        ) {
            itemsIndexed(
                boardListItem,
                key = { _, item -> item.id },
            ) { index, value ->
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .border(BorderStroke(1.dp, Color.DarkGray))
                        .clickable { onAction(GameUiAction.ClickOnCellBoard(index)) },
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = if (value.playerCharacter != null) value.playerCharacter.toString() else " ",
                        color = if (value.playerCharacter == 'X') Color.Blue else Color.Red
                    )
                }

            }
        }
        Button({ onAction(GameUiAction.RestartGame) }) {
            Text(stringResource(R.string.restart_game))
        }
        if (state.winner != null) {
            Text(text = stringResource(R.string.winner_is_player, state.winner.character))
        }
        if (state.isBoardFull && state.winner == null) {
            Text(text = stringResource(R.string.game_is_draw))
        }

    }

}

@Suppress("UNCHECKED_CAST")
@Composable
fun GameScreenRoot(modifier: Modifier) {
    val viewModel: GameViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return GameViewModel(PlayTurnUseCase()) as T
            }
        }
    )
    val state by viewModel.state.collectAsStateWithLifecycle()
    GameScreen(modifier, state, viewModel::onAction)

}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreenRoot(Modifier.fillMaxSize())
}
