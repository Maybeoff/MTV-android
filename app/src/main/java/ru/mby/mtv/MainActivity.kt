package ru.mby.mtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import ru.mby.mtv.ui.theme.MtvTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MtvTheme {
                VideoPlayer(
                    videoUrl = "https://tv.maybeyoou.tw1.su/hls/stream.m3u8",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}