package com.codepath.apps.twitterapp;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.twitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {
	TweetAdapter adapter;
	ListView lvTweets;
	ArrayList<Tweet> tweets;
	Tweet lastVisibleTweet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		this.setTitle("Twitter Client");
		this.setTitleColor(Color.BLUE);
		setViews();
		getTweets();
	}
	
	public void setViews() {
		lvTweets = (ListView) findViewById(R.id.lvTweets);
        lvTweets.setOnScrollListener(new EndlessScrollListener() {
        	@Override
        	public void onLoadMore(int page, int totalItemsCount) {
        		if(lastVisibleTweet!=null) {
        			Log.d("scroll","get more data from: " + String.valueOf(lastVisibleTweet.getId()));
        			getOlderTweets(lastVisibleTweet.getId());
        		}
        		else
        			Log.d("scroll","something went wrong");
        	}
        });
	}

	public void getTweets() {
		TwitterClientApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray JsonTweets) {
				tweets = Tweet.fromJson(JsonTweets);
				adapter = new TweetAdapter(getBaseContext(), tweets);
				lvTweets.setAdapter(adapter);
				lastVisibleTweet = adapter.getItem(adapter.getCount()-1);
			}
		});
	}
	
	public void getOlderTweets(long fromTweet) {
		TwitterClientApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray JsonTweets) {
				tweets = Tweet.fromJson(JsonTweets);
				adapter.addAll(tweets);
				lastVisibleTweet = adapter.getItem(adapter.getCount()-1);
			}
		}, fromTweet);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_tweet, menu);
		return true;
	}
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()) {
			case R.id.refresh:
			  getTweets();
			  break;
			case R.id.addtweet:
			  Intent iCreate = new Intent(getApplicationContext(), CreateTweetActivity.class);
			  startActivityForResult(iCreate,200);
			  break; 
		}
		return true;
		
	}
	  
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == 200) {		
			String tweetResponse = data.getExtras().getString("result");
			try {
				JSONObject jsonTweet = new JSONObject(tweetResponse);
					Tweet newTweet = Tweet.fromJson(jsonTweet);
					adapter.insert(newTweet, 0);
					Toast.makeText(this,"Tweet Added", Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Toast.makeText(this,"something went wrong", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			}
			else {
				Toast.makeText(this,"cancelled tweet", Toast.LENGTH_SHORT).show();
			}
	} 
}
