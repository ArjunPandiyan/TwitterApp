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

public class MentionsTweetsFragment extends TweetsFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			getMentionsTweets();
	}

	public void getMentionsTweets() {
		TwitterClientApp.getRestClient().getMentionsTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray JsonTweets) {
				addTweets(JsonTweets, true);
			}
		});
	}
	
//	public void MenuClicked(MenuItem item) {
//		switch(item.getItemId()) {
//			case R.id.refresh:
//				getMentionsTweets();
//			  break;
//			case R.id.addtweet:
////			  Intent iCreate = new Intent(getActivity(), CreateTweetActivity.class);
////			  startActivityForResult(iCreate,200);
//			  break; 
//		}
//	}
	  

	
}
