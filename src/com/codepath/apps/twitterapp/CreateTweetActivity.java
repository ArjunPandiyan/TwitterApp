package com.codepath.apps.twitterapp;

import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateTweetActivity extends Activity {

	Button bnOk;
	Button bnCancel;
	TextView tvLength;
	EditText etTweet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_tweet);
		this.setTitle("Create Tweet");
		bnOk = (Button) findViewById(R.id.bnOk);
		bnCancel = (Button) findViewById(R.id.bnCancel);
		tvLength = (TextView) findViewById(R.id.tvLength);
		etTweet = (EditText) findViewById(R.id.etTweet);
		
		etTweet.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				if(s.length()>140) 
					etTweet.setError("140 characters max limit");
				else
					tvLength.setText(String.valueOf(s.length()) + "/140");
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_tweet, menu);
		return true;
	}
	
	//Create and send tweet
	public void onTweetOk(View v) {
		String tweetString = etTweet.getText().toString();
        if(tweetString != null){
                TwitterClientApp.getRestClient().postTweet(new JsonHttpResponseHandler(){
                        @Override
                        public void onSuccess(JSONObject jsonResult){
                                Intent successIntent = getIntent();
                                successIntent.putExtra("result", jsonResult.toString());
                                setResult(RESULT_OK, successIntent);
                                finish();
                        }
                }, tweetString);
                
        }
	}

	//Cancel compose tweet
	public void onTweetCancel(View v) {
		 Intent cancelIntent = getIntent();
		 cancelIntent.putExtra("result", "cancelled");
         setResult(RESULT_CANCELED, cancelIntent);
         finish();
	}
}
