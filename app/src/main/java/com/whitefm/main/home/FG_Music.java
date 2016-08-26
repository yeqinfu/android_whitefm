package com.whitefm.main.home;

import com.whitefm.R;
import com.whitefm.base.FG_Base;
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
public class FG_Music extends FG_Base {
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
		mLayout.setOrientation(LinearLayout.VERTICAL);//设置为线性布局-上下排列
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
		setupEqualizeFxAndUi();//添加均衡器到界面
		setupPlayButton();//添加按钮到界面
		mVisualizer.setEnabled(true);//false 则不显示
		mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {}
		});

		mMediaPlayer.start();//开始播放
		mMediaPlayer.setLooping(true);//循环播放
	}

	@Override
	protected void afterViews() {

	}

	/**
	 * 通过mMediaPlayer返回的AudioSessionId创建一个优先级为0均衡器对象 并且通过频谱生成相应的UI和对应的事件
	 */
	private void setupEqualizeFxAndUi() {

		TextView kongge = new TextView(getActivity());
		kongge.setText("");
		kongge.setTextSize(10);
		mLayout.addView(kongge);

		mEqualizer = new Equalizer(0, mMediaPlayer.getAudioSessionId());
		mEqualizer.setEnabled(true);// 启用均衡器

		// 通过均衡器得到其支持的频谱引擎
		short bands = mEqualizer.getNumberOfBands();

		// getBandLevelRange 是一个数组，返回一组频谱等级数组，
		// 第一个下标为最低的限度范围
		// 第二个下标为最大的上限,依次取出
		final short minEqualizer = mEqualizer.getBandLevelRange()[0];
		final short maxEqualizer = mEqualizer.getBandLevelRange()[1];

		for (short i = 0; i < bands; i++) {
			final short band = i;

			TextView freqTextView = new TextView(getActivity());
			freqTextView.setTextColor(Color.WHITE);
			freqTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			freqTextView.setGravity(Gravity.CENTER_HORIZONTAL);

			// 取出中心频率
			freqTextView.setText((mEqualizer.getCenterFreq(band) / 1000) + "HZ");
			mLayout.addView(freqTextView);

			LinearLayout row = new LinearLayout(getActivity());
			row.setOrientation(LinearLayout.HORIZONTAL);

			TextView minDbTextView = new TextView(getActivity());
			minDbTextView.setTextColor(Color.WHITE);
			minDbTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			minDbTextView.setText((minEqualizer / 100) + " dB");

			TextView maxDbTextView = new TextView(getActivity());
			maxDbTextView.setTextColor(Color.WHITE);
			maxDbTextView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			maxDbTextView.setText((maxEqualizer / 100) + " dB");

			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 40);

			layoutParams.weight = 1;
			SeekBar seekbar = new SeekBar(getActivity());
			seekbar.setLayoutParams(layoutParams);
			seekbar.setPadding(15, 0, 15, 0);
			seekbar.setThumb(getResources().getDrawable(R.drawable.seek_bar_dian_selector));
			seekbar.setThumbOffset(20);
			seekbar.setMax(maxEqualizer - minEqualizer);
			seekbar.setProgress(mEqualizer.getBandLevel(band));

			seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					mEqualizer.setBandLevel(band, (short) (progress + minEqualizer));
				}
			});
			row.addView(minDbTextView);
			row.addView(seekbar);
			row.addView(maxDbTextView);

			mLayout.addView(row);

		}
		TextView eqTextView = new TextView(getActivity());
		eqTextView.setTextColor(Color.WHITE);
		eqTextView.setText("均衡器");
		eqTextView.setGravity(Gravity.CENTER_HORIZONTAL);
		eqTextView.setTextSize(20);

		mLayout.addView(eqTextView);

	}

	/**
	 * 生成一个VisualizerView对象，使音频频谱的波段能够反映到 VisualizerView上
	 */
	private void setupVisualizerFxAndUi() {
		mBaseVisualizerView = new VisualizerView(getActivity());

		mBaseVisualizerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, //宽度
				(int) (VISUALIZER_HEIGHT_DIP * getResources().getDisplayMetrics().density)//高度
		));
		mBaseVisualizerView.setBackgroundColor(Color.rgb(90,23,43));
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
		play.setBackgroundResource(R.drawable.new_main_activity_stop_up);
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
