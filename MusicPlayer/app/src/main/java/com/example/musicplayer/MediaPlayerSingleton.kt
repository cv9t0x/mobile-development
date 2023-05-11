import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.io.IOException

class MediaPlayerSingleton private constructor(context: Context) {

    companion object {
        @Volatile
        private var instance: MediaPlayerSingleton? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: MediaPlayerSingleton(context).also { instance = it }
            }
    }

    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private var _isPlaying = MutableLiveData<Boolean>()

    val isPlaying: LiveData<Boolean>
        get() = _isPlaying

    init {
        _isPlaying.value = false
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
                _isPlaying.postValue(false)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun play() {
        mediaPlayer.start()
        _isPlaying.value = true
    }

    fun pause() {
        mediaPlayer.pause()
        _isPlaying.value = false
    }

    fun getCurrentPosition() = mediaPlayer.currentPosition

    fun getDuration() = mediaPlayer.duration
}
