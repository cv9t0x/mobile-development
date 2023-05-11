import android.content.Context
import android.media.MediaPlayer
import com.example.musicplayer.MediaPlayerObserver
import java.io.IOException

class MediaPlayerSingleton private constructor(context: Context) {

    companion object {
        @Volatile
        private var instance: MediaPlayerSingleton? = null
        private val observers = mutableListOf<MediaPlayerObserver>()

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: MediaPlayerSingleton(context).also { instance = it }
            }

        fun addObserver(observer: MediaPlayerObserver) {
            observers.add(observer)
        }

        fun removeObserver(observer: MediaPlayerObserver) {
            observers.remove(observer)
        }
    }

    private val mediaPlayer: MediaPlayer = MediaPlayer()

    init {
        try {
            val assetFileDescriptor = context.assets.openFd("file.mp3")
            mediaPlayer.setDataSource(
                assetFileDescriptor.fileDescriptor,
                assetFileDescriptor.startOffset,
                assetFileDescriptor.length
            )
            assetFileDescriptor.close()
            mediaPlayer.prepare()
            mediaPlayer.setOnCompletionListener {
                notifyObservers()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun play() {
        mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
    }
    fun isPlaying() = mediaPlayer.isPlaying

    fun getCurrentPosition() = mediaPlayer.currentPosition

    fun getDuration() = mediaPlayer.duration

    private fun notifyObservers() {
        for (observer in observers) {
            observer.onPlaybackComplete()
        }
    }
}
