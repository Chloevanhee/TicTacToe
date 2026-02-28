package com.bnp.tictactoe.di

import com.bnp.tictactoe.domain.usecases.PlayTurnUseCase
import com.bnp.tictactoe.domain.usecases.PlayTurnUseCaseInterface
import com.bnp.tictactoe.presentation.GameViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    single<PlayTurnUseCaseInterface> { PlayTurnUseCase() }
    viewModelOf(::GameViewModel)
}
