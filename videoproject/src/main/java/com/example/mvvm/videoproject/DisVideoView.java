package com.example.mvvm.videoproject;

/**
 * author : 90589
 * date   : 2020/6/19
 * desc   : 描述
 */

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;

import java.io.IOException;

/**
 * 作者：张风捷特烈<br/>
 * 时间：2019/3/8/008:12:43<br/>
 * 邮箱：1981462002@qq.com<br/>
 * 说明：视频播放：MediaPlayer + SurfaceView + MediaController
 */
public class DisVideoView extends SurfaceView implements MediaController.MediaPlayerControl {
    private SurfaceHolder mSurfaceHolder;//SurfaceHolder
    private MediaPlayer mMediaPlayer;//媒体播放器
    private MediaController mMediaController;//媒体控制器

    private int mVideoHeight;//视频宽高
    private int mVideoWidth;//视频高
    private int mSurfaceHeight;//SurfaceView高
    private int mSurfaceWidth;//SurfaceView宽

    private boolean isPrepared;//是否已准备好
    private Uri mUri;//播放的地址
    private int mCurrentPos;//当前进度
    private int mDuration = -1;//当前播放视频时长
    private int mCurrentBufferPer;//当前缓冲进度--网络

    public DisVideoView(Context context) {
        this(context, null);
    }
    public DisVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mSurfaceHolder = holder;
                openVideo();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                mSurfaceHeight = height;
                mSurfaceWidth = width;
                if (mMediaPlayer != null && isPrepared) {
                    initPosition();
                    mMediaPlayer.start();//开始播放
                    showCtrl();
                }
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mSurfaceHolder = null;
                hideController();
                releasePlayer();
            }
        });
    }
    /**
     * 显示控制器
     */
    private void showCtrl() {
        if (mMediaController != null) {
            mMediaController.show();
        }
    }
    /**
     * 隐藏控制器
     */
    private void hideController() {
        if (mMediaController != null) {
            mMediaController.hide();
        }
    }
    /**
     * 初始化最初位置
     */
    private void initPosition() {
        if (mCurrentPos != 0) {
            mMediaPlayer.seekTo(mCurrentPos);
            mCurrentPos = 0;
        }
    }
    private void openVideo() {
        if (mUri == null || mSurfaceHolder == null) {
            return;
        }
        isPrepared = false;//没有准备完成
        releasePlayer();
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(getContext(), mUri);
            mMediaPlayer.setDisplay(mSurfaceHolder);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setScreenOnWhilePlaying(true);//播放时屏幕一直亮着
            mMediaPlayer.prepareAsync();//异步准备
            attach2Ctrl();//绑定媒体控制器
        } catch (IOException e) {
            e.printStackTrace();
        }
        //准备监听
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isPrepared = true;
                if (mMediaController != null) {//控制器可用
                    mMediaController.setEnabled(true);
                }
                if (mOnPreparedListener != null) {//补偿回调
                    mOnPreparedListener.onPrepared(mp);
                }
                mVideoWidth = mp.getVideoWidth();
                mVideoHeight = mp.getVideoHeight();
                if (mVideoWidth != 0 && mVideoHeight != 0) {
                    getHolder().setFixedSize(mVideoWidth, mVideoHeight);
                    //开始初始化
                    initPosition();
                    if (mSurfaceWidth == mVideoWidth && mSurfaceHeight == mVideoHeight) {
                        if (!isPlaying() && mCurrentPos != 0 || getCurrentPosition() > 0) {
                            if (mMediaController != null) {
                                mMediaController.show(0);
                            }
                        }
                    }
                }
            }
        });
        //尺寸改变监听
        mMediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                mVideoWidth = mp.getVideoWidth();
                mVideoHeight = mp.getVideoHeight();
                if (mOnSizeChanged != null) {
                    mOnSizeChanged.onSizeChange();
                }
                if (mVideoWidth != 0 && mVideoHeight != 0) {
                    getHolder().setFixedSize(mVideoWidth, mVideoHeight);
                }
            }
        });
        //完成监听
        mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                hideController();
                start();
                if (mOnCompletionListener != null) {
                    mOnCompletionListener.onCompletion(mp);
                }
            }
        });
        //错误监听
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                hideController();
                if (mOnErrorListener != null) {
                    mOnErrorListener.onError(mp, what, extra);
                }
                return true;
            }
        });
        mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                mCurrentBufferPer = percent;
            }
        });
    }
    /**
     * 释放播放器
     */
    private void releasePlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private void attach2Ctrl() {
        if (mMediaPlayer != null && mMediaController != null) {
            mMediaController.setMediaPlayer(this);
            View anchor = this.getParent() instanceof View ? (View) this.getParent() : this;
            mMediaController.setAnchorView(anchor);
            mMediaController.setEnabled(true);
        }
    }

    public void setVideoPath(String path) {
        mUri = Uri.parse(path);
        setVideoURI(mUri);
    }
    public void setVideoURI(Uri uri) {
        mUri = uri;
        mCurrentPos = 0;
        openVideo();//打开视频
        requestLayout();//更新界面
        invalidate();
    }

    public void setMediaController(MediaController mediaController) {
        hideController();
        mMediaController = mediaController;
        attach2Ctrl();
    }

    public void stopPlay() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
    private void toggle() {
        if (mMediaController.isShowing()) {
            mMediaController.hide();
        } else {
            mMediaController.show();
        }
    }
    private boolean canPlay() {
        return mMediaPlayer != null && isPrepared;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isPrepared && mMediaController != null && mMediaPlayer != null) {
            toggle();
        }
        return false;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = adjustSize(mVideoWidth, widthMeasureSpec);
        int h = adjustSize(mVideoHeight, heightMeasureSpec);
        setMeasuredDimension(w, h);
    }
    public int adjustSize(int size, int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int len = MeasureSpec.getMode(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                result = size;
                break;
            case MeasureSpec.AT_MOST:
                result = Math.min(size, len);
                break;
            case MeasureSpec.EXACTLY:
                result = len;
                break;
        }
        return result;
    }
    //----------------------------------------------------------------
    //------------MediaPlayerControl接口函数---------------------------
    //----------------------------------------------------------------
    @Override
    public void start() {
        if (canPlay()) {
            mMediaPlayer.start();
        }
    }
    @Override
    public void pause() {
        if (canPlay() && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }
    @Override
    public int getDuration() {
        if (canPlay()) {
            if (mDuration > 0) {
                return mDuration;
            }
            mDuration = mMediaPlayer.getDuration();
            return mDuration;
        }
        mDuration = -1;
        return mDuration;
    }
    @Override
    public int getCurrentPosition() {
        if (canPlay()) {
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }
    @Override
    public void seekTo(int pos) {
        if (canPlay()) {
            mMediaPlayer.seekTo(pos);
        } else {
            mCurrentPos = pos;
        }
    }
    @Override
    public boolean isPlaying() {
        if (canPlay()) {
            return mMediaPlayer.isPlaying();
        }
        return false;
    }
    @Override
    public int getBufferPercentage() {
        if (canPlay()) {
            return mCurrentBufferPer;
        }
        return 0;
    }
    @Override
    public boolean canPause() {
        return true;
    }
    @Override
    public boolean canSeekBackward() {
        return true;
    }
    @Override
    public boolean canSeekForward() {
        return true;
    }
    @Override
    public int getAudioSessionId() {
        return 0;
    }
    //----------------------------------------------------------------
    //------------补偿回调---------------------------
    //----------------------------------------------------------------
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        mOnPreparedListener = onPreparedListener;
    }
    public void setOnCompletionListener(MediaPlayer.OnCompletionListener onCompletionListener) {
        mOnCompletionListener = onCompletionListener;
    }
    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        mOnErrorListener = onErrorListener;
    }
    public interface OnSizeChanged {
        void onSizeChange();
    }
    private OnSizeChanged mOnSizeChanged;
    public void setOnSizeChanged(OnSizeChanged onSizeChanged) {
        mOnSizeChanged = onSizeChanged;
    }
}
