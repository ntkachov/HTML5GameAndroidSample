package com.ntkachov.html5;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.ntkachov.html5.jsinterface.LocalDBInterface;
import com.whereat.app.R;

public class HTML5Activity extends Activity {

	WebView mWebView; //This is all we need for this activity
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); //We wont need any more views because our entire navigation is in html and js.

		// Set up location updates
		//locationHelper = new LocationHelper(this);
		//locationHelper.registerForUpdates(this);
		
		//Setup WebView
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		
		//Add any JS interfaces that we need like the DB
		mWebView.addJavascriptInterface(new LocalDBInterface(this), "AndroidDB");
		
		//Load the URL from the assets folder.
		mWebView.loadUrl("file:///android_asset/index.html");
		
		//Set up Error Logging.
		mWebView.setWebChromeClient(new WebChromeClient(){
			public void onConsoleMessage(String message, int lineNumber, String sourceID){
				Log.d("HTMl5Game",message + " -- LINE: " + lineNumber + " : " + sourceID);
			}
			
		});
	}
	
	@Override
	public void onBackPressed(){
		this.finish();
	}

}
