package com.example.ch4_num5;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    SurfaceView mSurfaceView;
    Button playBtn,stopBtn,pauseBtn;
    String path;
    SurfaceHolder sh;
    int res_file = R.raw.sample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSurfaceView = (SurfaceView)findViewById(R.id.surfaceView1);
        playBtn=(Button)findViewById(R.id.play1);
        stopBtn=(Button)findViewById(R.id.stop);
        pauseBtn=(Button)findViewById(R.id.pause);
//        path = "/sdcard/sample.3gp";
        mMediaPlayer = MediaPlayer.create(MainActivity.this,res_file);
        playBtn.setOnClickListener(new mClick());
        stopBtn.setOnClickListener(new mStopClick());
        pauseBtn.setOnClickListener(new mPauseClick());
    }

    /* 停止按钮事件  */
    class mStopClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            /* 是否正在播放 */
            if (mMediaPlayer.isPlaying()) {
                //重置MediaPlayer到初始状态
                mMediaPlayer.reset();
                mMediaPlayer.release();
            }
        }
    }
    /* 暂停按钮事件  */
    class mPauseClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (mMediaPlayer.isPlaying()) {
                /* 暂停 */
                mMediaPlayer.pause();
            } else {
                /* 开始播放 */
                mMediaPlayer.start();
            }
        }
    }

    class mClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            try {
                mMediaPlayer.reset();
//                mMediaPlayer = MediaPlayer.create(MainActivity.this,res_file);
                //为播放器对象设置用于显示视频内容、代表屏幕描绘的控制器
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//                mMediaPlayer.setDataSource(path);//设置数据源
                AssetFileDescriptor assetFileDescriptor =
                        getResources().openRawResourceFd(res_file);
                mMediaPlayer.setDataSource(
                        assetFileDescriptor.getFileDescriptor(),
                        assetFileDescriptor.getStartOffset(),
                        assetFileDescriptor.getLength());
                sh = mSurfaceView.getHolder();
                mMediaPlayer.setDisplay(sh);
                mMediaPlayer.prepare();
                mMediaPlayer.start();
            } catch (Exception e) {
                Log.i("MediaPlay err", "MediaPlay err");
            }
        }
    }


}
