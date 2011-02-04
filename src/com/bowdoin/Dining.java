
package com.bowdoin;

import android.app.TabActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;


public class Dining extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dining);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        setupTab(new TextView(this), "Breakfast");
        setupTab(new TextView(this), "Lunch");
        setupTab(new TextView(this), "Dinner");
    }

    private void setupTab(final View view, final String tag) {
        View tabview = createTabView(mTabHost.getContext(), tag);
        TabSpec setContent = mTabHost.newTabSpec(tag).
            setIndicator(tabview).
            setContent(
                       new TabContentFactory() {
                           public View createTabContent(String tag)
                           { return view; }
                       }
                       );
        mTabHost.addTab(setContent);
    }

    private static View createTabView(final Context context,
                                      final String text) {
        View view = LayoutInflater.from(context).
            inflate(R.layout.tabs_bg, null);
        TextView tv = (TextView) view.findViewById(R.id.tabsText);
        tv.setText(text);
        return view;
    }

    TabHost mTabHost;

    /** Called when the activity is first created. */
	// WebView mWebView;
	// @Override

    // public void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.dining);
    //     mWebView = (WebView) findViewById(R.id.webview);
    //     mWebView.getSettings().setJavaScriptEnabled(true);
    //     mWebView.getSettings().setBuiltInZoomControls(true);
    //     mWebView.getSettings().setTextSize(WebSettings.TextSize.LARGER);
 
    //     mHall = "48";
    //     mMeal = getCurrentMeal(mHall);
        
    //     mBreakfast = (Button) findViewById(R.id.breakfast);
    //     mBreakfast.setOnClickListener(new View.OnClickListener() {
    //         public void onClick(View v) {
    //         	mMeal = "Breakfast";
    //         	updateView();
    //         }
    //     });
        
    //     mLunch = (Button) findViewById(R.id.lunch);
    //     mLunch.setOnClickListener(new View.OnClickListener() {
    //         public void onClick(View v) {
    //         	mMeal = "Lunch";
    //         	updateView();
    //         }
    //     });
        
    //     mDinner = (Button) findViewById(R.id.dinner);
    //     mDinner.setOnClickListener(new View.OnClickListener() {
    //         public void onClick(View v) {
    //         	mMeal = "Dinner";
    //         	updateView();
    //         }
    //     });
        
    //     mBrunch = (Button) findViewById(R.id.brunch);
    //     mBrunch.setOnClickListener(new View.OnClickListener() {
    //         public void onClick(View v) {
    //         	mMeal = "Brunch";
    //         	updateView();
    //         }
    //     });
        
    //     mMoulton = (Button) findViewById(R.id.moulton);
    //     mMoulton.setOnClickListener(new View.OnClickListener() {
    //         public void onClick(View v) {
    //         	mHall = "48";
    //         	updateView();
    //         }
    //     });
        
    //     mThorn = (Button) findViewById(R.id.thorne);
    //     mThorn.setOnClickListener(new View.OnClickListener() {
    //         public void onClick(View v) {
    //         	mHall = "49";
    //         	updateView();
            	
    //         }
    //     });

    //     updateView();
        
    // }
	
	// // Roughly guesses which meal is currently going on
	// // easy enough to make this correct per-hall with some sort of
	// // scheduling object
	// private String getCurrentMeal(String hall) {
	// 	Calendar calendar = GregorianCalendar.getInstance(TimeZone.getDefault());
	// 	int hours = calendar.get(Calendar.HOUR_OF_DAY);
	// 	String meal;
		
	// 	if (hours > 14)
	// 		meal = "Dinner";
	// 	else if (hours > 10)
	// 		meal = "Lunch";
	// 	else
	// 		meal = "Breakfast";
	
	// 	return meal;
	// }
	
	// private void updateView() {
    //     loadUrl();
	// }

	// private void loadUrl() {
	// 	String Url = "http://www.bowdoin.edu/atreus/views?unit=" .concat(mHall).concat("&meal=").concat(mMeal);
	// 	mWebView.clearView();
	// 	mWebView.loadData("Loading...", "text/html", "");
	// 	mWebView.refreshDrawableState();
	// 	mWebView.loadUrl(Url);
	// }
	
	// protected void onSaveInstanceState(Bundle outState){
	// 	outState.putString("mMeal", mMeal);
	// 	outState.putString("mHall", mHall);
	// }
	
	// protected void onRestoreInstanceState(Bundle inState){
	// 	mMeal = inState.getString("mMeal");
	// 	mHall = inState.getString("mHall");
	// 	updateView();
	// }
	
	// private Button mBreakfast;
	// private Button mLunch;
	// private Button mDinner;
	// private Button mBrunch;
	// private Button mMoulton;
	// private Button mThorn;
	// private String mMeal;
	// private String mHall;
}