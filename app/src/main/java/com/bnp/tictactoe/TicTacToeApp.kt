package com.bnp.tictactoe

import android.app.Application
import com.bnp.tictactoe.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TicTacToeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TicTacToeApp)
            modules(appModule)

        }
    }
}