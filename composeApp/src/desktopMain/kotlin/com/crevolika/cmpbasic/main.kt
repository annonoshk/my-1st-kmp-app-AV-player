package com.crevolika.cmpbasic

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.privateeye
import org.jetbrains.compose.resources.painterResource


fun main() = application {
    val state = rememberWindowState(
        size = DpSize(400.dp,500.dp),
        position = WindowPosition(300.dp,300.dp)
    )
    Window(
        onCloseRequest = ::exitApplication, state = state,
        title = "World timer App",
        icon = painterResource(Res.drawable.privateeye)
    ){
        App()
        VideoPlayer(url = "https://path.to/your/video.mp4")
    }
}

