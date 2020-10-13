package com.nutrition.ui.exoplayer

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.text.CaptionStyleCompat
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import com.nutrition.R
import com.nutrition.ext.toast
import kotlinx.android.synthetic.main.custom_exo_styled_player_control_view.view.*
import kotlinx.android.synthetic.main.fragment_start.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ExoPlayerFragment : Fragment(R.layout.fragment_start) {

    private val viewModel: StartViewModel by viewModel()
    private var exoPlayer: SimpleExoPlayer? = null
    private var mediaItems: MutableList<MediaItem> = mutableListOf()
    private val videoUri =
        Uri.parse("https://html5demos.com/assets/dizzy.mp4")
    private val subUriEn =
        Uri.parse("https://mkvtoolnix.download/samples/vsshort-en.srt")
    private val subUriDe =
        Uri.parse("https://mkvtoolnix.download/samples/vsshort-de.srt")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textFromRepo = viewModel.getText()
        toast(textFromRepo)
    }

    private fun initPlayer() {
        val subtitles = listOf(
            MediaItem.Subtitle(subUriEn, MimeTypes.APPLICATION_SUBRIP, "en"),
            MediaItem.Subtitle(subUriDe, MimeTypes.APPLICATION_SUBRIP, "de")
        )

        val videoMediaItem = MediaItem.Builder()
            .setMediaId("video")
            .setUri(videoUri)
            .setSubtitles(subtitles)
            .build()
        mediaItems.add(videoMediaItem)

        val dataSourceFactory = DefaultDataSourceFactory(requireContext().applicationContext, "App")
        val mediaSourceFactory = DefaultMediaSourceFactory(dataSourceFactory)
        /**
         * Set preferred language to any (null is none)
         * */
        val trackSelectorParams = DefaultTrackSelector.ParametersBuilder(requireContext()).setPreferredTextLanguage("en").build()
        val trackSelector = DefaultTrackSelector(requireContext()).apply {
            parameters = trackSelectorParams
        }

        exoPlayer = SimpleExoPlayer.Builder(requireContext(), DefaultRenderersFactory(requireContext()))
            .setMediaSourceFactory(mediaSourceFactory)
            .setTrackSelector(trackSelector)
            .build()

        movie.player = exoPlayer

        /**
         * Turn on/off subtitles
         * */
        exoPlayer!!.addTextOutput {
            Timber.i("cokolwiek?: $it")
            if (true) movie.subtitleView?.setCues(it) else movie.subtitleView?.setCues(null)
        }

        /**
         * Customize subtitleView
         * */
        val captionStyleCompat = CaptionStyleCompat(Color.BLACK, Color.WHITE, Color.TRANSPARENT, CaptionStyleCompat.EDGE_TYPE_NONE, Color.WHITE, null)
        movie.subtitleView?.setStyle(captionStyleCompat)

        (exoPlayer as SimpleExoPlayer).apply {
            setMediaItems(mediaItems)
            playWhenReady = true
            prepare()
        }
    }

    private fun releasePlayer() {
       movie?.let {
           exoPlayer?.release()
           exoPlayer = null
           mediaItems.clear()
       }
    }

    override fun onStart() {
        super.onStart()

        if (Util.SDK_INT <= 23 || exoPlayer == null) {
            initPlayer()
            movie?.let {
                movie.onResume()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT > 23) {
            initPlayer()
            if (movie != null) {
                movie.onResume()
            }
        }
    }

    override fun onPause() {
        super.onPause()

        if (Util.SDK_INT > 23) {
            movie?.let {
                movie.onPause()
            }
            releasePlayer()
        }
    }


    override fun onStop() {
        super.onStop()

        if (Util.SDK_INT > 23) {
            movie?.let {
                movie.onPause()
            }
            releasePlayer()
        }
    }
}
