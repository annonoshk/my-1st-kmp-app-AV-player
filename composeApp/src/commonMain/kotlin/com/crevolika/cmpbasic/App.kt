package com.crevolika.cmpbasic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import chaintech.videoplayer.ui.video.VideoPlayerComposable
import kotlinproject.composeapp.generated.resources.*
import kotlinproject.composeapp.generated.resources.Res
import kotlinx.coroutines.delay
import kotlinx.datetime.*
import kotlinx.datetime.format.byUnicodePattern
import org.jetbrains.compose.resources.DrawableResource

data class Country(val name: String, val zone: TimeZone, val image: DrawableResource)

fun currentTimeAt(location: String, zone: TimeZone): String {
    fun LocalTime.formatted()= "$hour:$minute:$second"


    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time

    return "The time in $location is ${localTime.formatted()}"
}

val defaultCountries = listOf(
    Country("Korea", TimeZone.of("Asia/Seoul"), Res.drawable.kr),
    Country("France", TimeZone.of("Europe/Paris"), Res.drawable.fr),
    Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mx),
    Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.id),
    Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.eg)
)

@Composable
@Preview
fun App(countries: List<Country> = defaultCountries) {
    MaterialTheme {
        var showCountries by remember { mutableStateOf(false) }
        var timeAtLocation by remember { mutableStateOf("No location selected") }
        var selectedZone by remember { mutableStateOf<TimeZone?>(null) }
        var selectedCountryName by remember { mutableStateOf("No location selected") }

        LaunchedEffect(selectedZone) {
            while (true) { // Time goes up every second
                if (selectedZone != null) {
                    timeAtLocation = currentTimeAt(selectedCountryName, selectedZone?: TimeZone.currentSystemDefault())
                }
                delay(1000L)
            }
        }
        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                timeAtLocation,
                style = TextStyle(fontSize = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier.padding(start = 20.dp, top = 10.dp)) {
                DropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    countries.forEach { (name, zone, image) ->
                        DropdownMenuItem(
                            onClick = {
                                selectedZone = zone
                                selectedCountryName = name
                                timeAtLocation = currentTimeAt(name, zone)
                                showCountries = false
                            }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painterResource(image),
                                    modifier = Modifier.size(50.dp).padding(end = 10.dp),
                                    contentDescription = "$name flag"
                                )
                                Text(name)
                            }
                        }
                    }
                }
            }

            Button(modifier = Modifier.padding(start = 20.dp, top = 10.dp),
                   onClick = { showCountries = !showCountries }) {
                Text("Select Location")
            }
            VideoPlayerComposable(
                modifier = Modifier.fillMaxSize(),
                url = "https://www.youtube.com/shorts/FDPiAIcy8rc"
            )

        }
    }
}



/*
@Composable
@Preview
fun App_OLD() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Today's date is ${todayDate()}",
                modifier = Modifier.padding(20.dp),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }

            Divider(color = Color.Gray, thickness = 1.dp)
            Text("This is Crevolika Inc. preparation app")
            Divider(color = Color.Gray, thickness = 1.dp)
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(Res.drawable.logo), contentDescription = null)
                    Text("Private Eye to follow Deep Fake: $greeting")
                }
            }

            Image(painter = painterResource(Res.drawable.privateeye), contentDescription = null)
            Divider(color = Color.DarkGray, thickness = 1.dp)
        }
    }
}*/



