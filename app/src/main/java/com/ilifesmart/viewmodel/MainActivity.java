package com.ilifesmart.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ilifesmart.model.LiveDataTimerViewModel;
import com.ilifesmart.model.MainViewModel;

public class MainActivity extends AppCompatActivity {

	public static final String TAG = "MainActivity";

	private TextView scoreA;
	private TextView scoreB;

	private MainViewModel mMainViewModel;
	private LiveDataTimerViewModel mLiveDataModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(TAG, "onCreate: CanonicalName: " + MainViewModel.class.getCanonicalName());
		Log.d(TAG, "onCreate: isAssignableFrom: " + AndroidViewModel.class.isAssignableFrom(MainViewModel.class));

		/*
		* 此处通过追踪源码发现是在Activity.onDestroy中调用onCleared来清除内存
		*
		* */
		mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
		mLiveDataModel = ViewModelProviders.of(this).get(LiveDataTimerViewModel.class);

		initView();
	}

	private void initView() {
		scoreA = findViewById(R.id.score_A);
		scoreB = findViewById(R.id.score_B);

		setScore(scoreA, mMainViewModel.getInitialCountA());
		setScore(scoreB, mMainViewModel.getInitialCountB());

		findViewById(R.id.player_A).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setScore(scoreA, mMainViewModel.getCurrentCountA());
			}
		});

		findViewById(R.id.player_B).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				setScore(scoreB, mMainViewModel.getCurrentCountB());
			}
		});

		final Observer<Long> observer = new Observer<Long>() {
			@Override
			public void onChanged(Long aLong) {
				Log.d(TAG, "onChanged: update " + aLong);
			}
		};

		mLiveDataModel.getElapsedTime().observe(this, observer);
	}

	private void setScore(TextView view, int score) {
		view.setText(String.valueOf(score));
	}

}



