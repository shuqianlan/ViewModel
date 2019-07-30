package com.ilifesmart.model;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
	private int clickCountA, clickCountB = 0;

	public int getInitialCountA() {
		return clickCountA;
	}

	public int getInitialCountB() {
		return clickCountB;
	}

	public int getCurrentCountA() {
		return ++clickCountA;
	}

	public int getCurrentCountB() {
		return ++clickCountB;
	}

	@Override
	protected void onCleared() {
		super.onCleared();

		// chance to clear the memories.
	}
}