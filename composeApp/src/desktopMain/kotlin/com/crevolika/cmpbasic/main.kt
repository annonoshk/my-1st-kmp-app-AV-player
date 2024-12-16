package com.crevolika.cmpbasic

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.privateeye
import org.jetbrains.compose.resources.painterResource


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CrevoLika",
        icon = painterResource(Res.drawable.privateeye)
    ){
        App()
    }
}