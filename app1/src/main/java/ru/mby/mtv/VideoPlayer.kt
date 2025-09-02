package ru.mby.mtv

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView

@Composable
fun VideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        // Создаем DataSource.Factory с поддержкой HLS
        val dataSourceFactory = DefaultDataSource.Factory(context)
        
        // Создаем MediaSourceFactory с поддержкой HLS
        val mediaSourceFactory = DefaultMediaSourceFactory(dataSourceFactory)
        
        ExoPlayer.Builder(context)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()
            .apply {
                val mediaItem = MediaItem.fromUri(videoUrl)
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
                repeatMode = Player.REPEAT_MODE_ALL
                
                // Добавляем слушатель ошибок для отладки
                addListener(object : Player.Listener {
                    override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
                        super.onPlayerError(error)
                        android.util.Log.e("VideoPlayer", "Player error: ${error.message}", error)
                    }
                    
                    override fun onPlaybackStateChanged(playbackState: Int) {
                        super.onPlaybackStateChanged(playbackState)
                        when (playbackState) {
                            Player.STATE_READY -> android.util.Log.d("VideoPlayer", "Player ready")
                            Player.STATE_BUFFERING -> android.util.Log.d("VideoPlayer", "Player buffering")
                            Player.STATE_ENDED -> android.util.Log.d("VideoPlayer", "Player ended")
                            Player.STATE_IDLE -> android.util.Log.d("VideoPlayer", "Player idle")
                        }
                    }
                })
            }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                useController = true
                setShowNextButton(false)
                setShowPreviousButton(false)
            }
        },
        modifier = modifier.fillMaxSize()
    )
}
