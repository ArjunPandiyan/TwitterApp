package com.codepath.app.twitterapp.fragments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.codepath.apps.twitterapp.CreateTweetActivity;
import com.codepath.apps.twitterapp.R;
import com.codepath.apps.twitterapp.TwitterClientApp;
import com.codepath.apps.twitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class HomelineTweetsFragment extends TweetsFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			getTweets();
	}

	public void getTweets() {
		TwitterClientApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray JsonTweets) {
				addTweets(JsonTweets, true);
			}
		});
	}
	
//	public void MenuClicked(MenuItem item) {
//		switch(item.getItemId()) {
//			case R.id.refresh:
//			  getTweets();
//			  break;
//			case R.id.addtweet:
//			  Intent iCreate = new Intent(getActivity(), CreateTweetActivity.class);
//			  startActivityForResult(iCreate,200);
//			  break; 
//		}
//	}
	  
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == RESULT_OK && requestCode == 200) {		
			String tweetResponse = data.getExtras().getString("result");
			try {
				JSONObject jsonTweet = new JSONObject(tweetResponse);
					Tweet newTweet = Tweet.fromJson(jsonTweet);
					adapter.insert(newTweet, 0);
					Toast.makeText(getActivity(),"Tweet Added", Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getActivity(),"something went wrong", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
			}
			else {
				Toast.makeText(getActivity(),"cancelled tweet", Toast.LENGTH_SHORT).show();
			}
	} 
}
