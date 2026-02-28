package com.bnp.tictactoe.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bnp.tictactoe.R
import com.bnp.tictactoe.domain.models.GameState
import com.bnp.tictactoe.domain.models.Player
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameScreen(
    modifier: Modifier,
    state: GameState,
    onAction: (GameUiAction) -> Unit
) {
    val boardCells = state.board.boardCells

    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.it_is_player_turn, state.currentPlayer))
        Column {
            boardCells.forEachIndexed { indewRow, row ->
                Row {
                    row.forEachIndexed { index, player ->
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .aspectRatio(1f)
                                .border(BorderStroke(1.dp, Color.DarkGray))
                                .clickable {
                                    onAction(
                                        GameUiAction.ClickOnCellBoard(
                                            indewRow,
                                            index
                                        )
                                    )
                                },
                            contentAlignment = Alignment.Center
                        )
                        {
                            val (text, color) = when (player) {
                                Player.X -> "X" to Color.Blue
                                Player.O -> "O" to Color.Red
                                null -> "" to Color.Transparent
                            }
                            Text(
                                text = text,
                                color = color
                            )
                        }
                    }
                }

            }


        }
        Button({ onAction(GameUiAction.RestartGame) }) {
            Text(stringResource(R.string.restart_game))
        }
        if (state.winner != null) {
            Text(text = stringResource(R.string.winner_is_player, state.winner))
        }
        if (state.isBoardFull && state.winner == null) {
            Text(text = stringResource(R.string.game_is_draw))
        }
    }


}


@Composable
fun GameScreenRoot(modifier: Modifier, viewModel: GameViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    GameScreen(modifier, state, viewModel::onAction)

}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    val state = GameState()
    GameScreen(modifier = Modifier.fillMaxSize(), state = state, onAction = {})
}
