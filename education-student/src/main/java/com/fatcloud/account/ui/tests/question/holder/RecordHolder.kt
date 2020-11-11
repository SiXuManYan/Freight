package com.fatcloud.account.ui.tests.question.holder

import android.Manifest.permission.*
import android.media.AudioFormat
import android.media.MediaPlayer
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.bumptech.glide.Glide
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.question.QuestionChild
import com.fatcloud.account.ui.app.CloudAccountApplication
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.minlukj.mediaplaylib.MediaPlayFunctionListener
import com.minlukj.mediaplaylib.MediaPlayInfoListener
import com.minlukj.mediaplaylib.MediaPlayerUtils
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.util.PermissionUtils
import com.zlw.main.recorderlib.RecordManager
import com.zlw.main.recorderlib.recorder.RecordConfig
import com.zlw.main.recorderlib.recorder.listener.RecordDataListener
import com.zlw.main.recorderlib.recorder.listener.RecordResultListener
import com.zlw.main.recorderlib.recorder.listener.RecordSoundSizeListener
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_question_record.*
import java.io.File


/**
 * Created by Wangsw on 2020/10/26 0026 15:45.
 * </br>
 *
 */
class RecordHolder(parent: ViewGroup?) : BaseItemViewHolder<QuestionChild>(parent, R.layout.item_question_record), LayoutContainer {

    override val containerView: View? get() = itemView

    var path: String = ""

    var mediaUtils: MediaPlayerUtils? = null

    init {
        val instance = RecordManager.getInstance()
        instance.apply {

            init(Utils.getApp(), false)

            // 修改录音格式(默认:WAV)
            changeFormat(RecordConfig.RecordFormat.MP3)

            // 音频采样率
            changeRecordConfig(instance.recordConfig.setSampleRate(44100));

            // 音频位宽
            changeRecordConfig(instance.recordConfig.setEncodingConfig(AudioFormat.ENCODING_PCM_16BIT));


            // 录音结果监听
            setRecordResultListener(object : RecordResultListener {
                override fun onResult(result: File?) {
                    if (result == null) {
                        return
                    }

                    val absolutePath = result.absolutePath
                    path = absolutePath

                    val ownerAdapter = getOwnerAdapter<RecyclerArrayAdapter<QuestionChild>>()
                    val item = ownerAdapter?.getItem(dataPosition)
                    item?.nativeAnswerRecordPath = absolutePath

                    //  上传文件のoss
                    val app = Utils.getApp() as CloudAccountApplication
                    app.uploadRecord(path,layoutPosition)

                }
            })

            // 声音大小监听
            setRecordSoundSizeListener(object : RecordSoundSizeListener {
                override fun onSoundSize(soundSize: Int) {

                }
            })
            // 音频数据监听
            setRecordDataListener(object : RecordDataListener {
                override fun onData(data: ByteArray?) {

                }
            })

        }

        mediaUtils = MediaPlayerUtils().apply {

            val s = "播放状态："
            val t = "当前信息："

            // 播放状态回调
            setMediaPlayFunctionListener(object : MediaPlayFunctionListener {
                override fun start() {
                    Log.d(s, "开始播放")
                    seek_bar.max = duration
                }

                override fun stop() {
                    Log.d(s, "停止播放")

                }

                override fun prepared() {
                    Log.d(s, "资源准备完成")
                }

                override fun pause() {
                    Log.d(s, "暂停")
                }

                override fun reset() {
                    Log.d(s, "资源重置")
                    seek_bar.progress = 0
                    play_switcher.displayedChild = 0
                }
            })

            setMediaPlayInfoListener(object : MediaPlayInfoListener{

                override fun onSeekComplete(mediaPlayer: MediaPlayer?) {
                    Log.d(t, "进度调整完成")
                }

                override fun onBufferingUpdate(mediaPlayer: MediaPlayer?, i: Int) = Unit

                override fun onCompletion(mediaPlayer: MediaPlayer?) {
                    Log.d(t, "播放完成监听")
                    seek_bar.progress = 100
                }

                override fun onSeekBarProgress(progress: Int) {
                    Log.d(t, "时实播放进度：progress = $progress")
                    seek_bar.progress = progress
                }

                override fun onError(mp: MediaPlayer?, what: Int, extra: Int) {
                    Log.d(t, "播放错误 ")
                }

            })


        }


    }


    override fun setData(data: QuestionChild?) {
        if (data == null) {
            return
        }
        Glide.with(context).load(data.itemImg).into(content_iv)
        record_iv.setOnClickListener {
            PermissionUtils.permissionAny(context, PermissionUtils.OnPermissionCallBack {
                if (it) {
                    RecordManager.getInstance().start()
                    record_iv.visibility = View.GONE
                } else {
                    ToastUtils.showShort("请授权相应权限")
                }

            }, RECORD_AUDIO, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
        }

        stop_iv.setOnClickListener {
            RecordManager.getInstance().stop()
            stop_iv.visibility = View.GONE
            play_ll.visibility = View.VISIBLE
        }

        media_play_iv.setOnClickListener {
            if (path.isBlank()) {
                return@setOnClickListener
            }
            play_switcher.displayedChild = 1
            mediaUtils?.setFilePlay(File(path))
            if (!mediaUtils?.isPlaying!!){
                mediaUtils?.start()
            }
        }


        media_stop_iv.setOnClickListener {
            play_switcher.displayedChild = 0
            mediaUtils?.pause()
        }
    }


}