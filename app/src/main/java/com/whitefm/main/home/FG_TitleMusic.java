package com.whitefm.main.home;

import com.whitefm.R;
import com.whitefm.base.FG_Base;
import com.whitefm.basefm.FG_BaseFM;
import com.whitefm.utils.toast.ToastUtil;
import com.whitefm.wiget.VisualizerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.media.audiofx.Visualizer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by yeqinfu on 8/25/16.
 */
public class FG_TitleMusic extends FG_BaseFM {
	private static final float	VISUALIZER_HEIGHT_DIP	= 150f;	//频谱View高度

	private MediaPlayer			mMediaPlayer;					//音频
	private Visualizer			mVisualizer;					//频谱器
	private Equalizer			mEqualizer;						//均衡器

	private LinearLayout		mLayout;						//代码布局
	VisualizerView				mBaseVisualizerView;
	ImageButton					play;

	@Override
	protected int getTitleRes() {
		return 0;
	}

	@Override
	protected int getFgRes() {
		return 0;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//    setVolumeControlStream(AudioManager.STREAM_MUSIC);//设置音频流 - STREAM_MUSIC：音乐回放即媒体音量

		mLayout = new LinearLayout(getActivity());//代码创建布局
		mLayout.setOrientation(LinearLayout.HORIZONTAL);//设置为线性布局-左右排列
		mLayout.setGravity(Gravity.CENTER);
		mMediaPlayer = MediaPlayer.create(getActivity(), R.raw.aaaass);//实例化 MediaPlayer 并添加音频

		return mLayout;
	}

	private boolean isRecordAudioGranted() {
		if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
			return false;
		}
		return true;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (isRecordAudioGranted()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				requestPermissions(new String[] { Manifest.permission.RECORD_AUDIO }, 1);
				if (!isRecordAudioGranted()){
					ToastUtil.toast(getActivity(), "请允许程序的录音权限");
					return;
				}
			}
			else {
				ToastUtil.toast(getActivity(), "请允许程序的录音权限");
				return;
			}
		}
		else {
			ToastUtil.toast(getActivity(), "请允许程序的录音权限");
			return;
		}
		setupVisualizerFxAndUi();//添加频谱到界面
		setupPlayButton();//添加按钮到界面
		mVisualizer.setEnabled(true);//false 则不显示
		mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {}
		});

		//mMediaPlayer.start();//开始播放
		//mMediaPlayer.setLooping(true);//循环播放
	}

	@Override
	protected void afterViews() {

	}
	/**
	 * 生成一个VisualizerView对象，使音频频谱的波段能够反映到 VisualizerView上
	 */
	private void setupVisualizerFxAndUi() {
		mBaseVisualizerView = new VisualizerView(getActivity());

		ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(400, //宽度
				(int) (VISUALIZER_HEIGHT_DIP * getResources().getDisplayMetrics().density)//高度
		);
		mBaseVisualizerView.setLayoutParams(params);
		mBaseVisualizerView.setNeedShadows(false);
		//将频谱View添加到布局
		mLayout.addView(mBaseVisualizerView);
		//实例化Visualizer，参数SessionId可以通过MediaPlayer的对象获得
		mVisualizer = new Visualizer(mMediaPlayer.getAudioSessionId());
		//采样 - 参数内必须是2的位数 - 如64,128,256,512,1024
		mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
		//设置允许波形表示，并且捕获它
		mBaseVisualizerView.setVisualizer(mVisualizer);
	}

	//播放按钮
	private void setupPlayButton() {
		play = new ImageButton(getActivity());
		play.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		play.setBackgroundResource(R.drawable.new_main_activity_play_up);
		play.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mMediaPlayer.isPlaying()) {
					play.setBackgroundResource(R.drawable.new_main_activity_play_up);
					mMediaPlayer.pause();
				}
				else {
					play.setBackgroundResource(R.drawable.new_main_activity_stop_up);
					mMediaPlayer.start();
				}
			}
		});
		mLayout.addView(play);
	}

	@Override
	public void onPause() {
		super.onPause();
		if (getActivity().isFinishing() && mMediaPlayer != null) {
			mVisualizer.release();
			mMediaPlayer.release();
			mEqualizer.release();
			mMediaPlayer = null;
		}
	}

}
