package com.bowdoin;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Dining extends Activity {
	protected static final int HALL_THORNE = 0;
	protected static final int HALL_MOULTON = 1;

	protected static final int MEAL_BRUNCH = 0;
	protected static final int MEAL_BREAKFAST = 1;
	protected static final int MEAL_LUNCH = 2;
	protected static final int MEAL_DINNER = 3;

	/** Called when the activity is first created. */
	WebView mWebView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dining);
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.getSettings().setTextSize(WebSettings.TextSize.LARGER);

		mHall = Dining.HALL_THORNE;
		mMeal = getCurrentMeal(mHall);

		mBreakfast = (SegmentedControlButton) findViewById(R.id.breakfast);
		mBreakfast.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mMeal = Dining.MEAL_BREAKFAST;
				updateView();
			}
		});

		mLunch = (SegmentedControlButton) findViewById(R.id.lunch);
		mLunch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mMeal = Dining.MEAL_LUNCH;
				updateView();
			}
		});

		mDinner = (SegmentedControlButton) findViewById(R.id.dinner);
		mDinner.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mMeal = Dining.MEAL_DINNER;
				updateView();
			}
		});

		mBrunch = (SegmentedControlButton) findViewById(R.id.brunch);
		mBrunch.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mMeal = Dining.MEAL_BRUNCH;
				updateView();
			}
		});

		mMoulton = (SegmentedControlButton) findViewById(R.id.moulton);
		mMoulton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mHall = Dining.HALL_MOULTON;
				updateView();
			}
		});

		mThorn = (SegmentedControlButton) findViewById(R.id.thorne);
		mThorn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mHall = Dining.HALL_THORNE;
				updateView();

			}
		});

		updateView();

	}

	// Roughly guesses which meal is currently going on
	// easy enough to make this correct per-hall with some sort of
	// scheduling object
	private int getCurrentMeal(int hall) {
		Calendar calendar = GregorianCalendar
				.getInstance(TimeZone.getDefault());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		int meal;

		if (hours > 14)
			meal = Dining.MEAL_DINNER;
		else if (hours > 10)
			meal = Dining.MEAL_LUNCH;
		else
			meal = Dining.MEAL_BREAKFAST;

		return meal;
	}

	private void updateView() {
		updateTitle();
		loadUrl();
	}
	
	private void updateTitle() {
		setTitle(getHallString(mHall).concat(" - ").concat(getMealString(mMeal)));
	}

	private void loadUrl() {
		String Url = "http://www.bowdoin.edu/atreus/views?unit=".concat(
				getHallId(mHall)).concat("&meal=").concat(
				getMealString(mMeal));
		mWebView.clearView();
		mWebView.loadData("Loading...", "text/html", "");
		mWebView.refreshDrawableState();
		mWebView.loadUrl(Url);
	}

	protected String getHallString(int hall) {
		switch (hall) {
		case Dining.HALL_MOULTON:
			return "Moulton Union";
		case Dining.HALL_THORNE:
			return "Thorne Hall";
		}
		return null;
	}

	
	protected String getHallId(int hall) {
		switch (hall) {
		case Dining.HALL_MOULTON:
			return "48";
		case Dining.HALL_THORNE:
			return "49";
		}
		return null;
	}

	protected String getMealString(int meal) {
		switch(meal) {
		case Dining.MEAL_BREAKFAST:
			return "Breakfast";
		case Dining.MEAL_LUNCH:
			return "Lunch";
		case Dining.MEAL_DINNER:
			return "Dinner";
		case Dining.MEAL_BRUNCH:
			return "Brunch";
		}
		return null;
	}

	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("mMeal", mMeal);
		outState.putInt("mHall", mHall);
	}

	protected void onRestoreInstanceState(Bundle inState) {
		mMeal = inState.getInt("mMeal");
		mHall = inState.getInt("mHall");
		updateView();
	}

	private SegmentedControlButton mBreakfast;
	private SegmentedControlButton mLunch;
	private SegmentedControlButton mDinner;
	private SegmentedControlButton mBrunch;
	private SegmentedControlButton mMoulton;
	private SegmentedControlButton mThorn;
	private int mMeal;
	private int mHall;
}