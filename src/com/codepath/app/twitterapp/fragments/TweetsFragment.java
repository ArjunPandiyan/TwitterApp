package com.codepath.app.twitterapp.fragments;

import java.util.ArrayList;

import org.json.JSONArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.codepath.apps.twitterapp.R;
import com.codepath.apps.twitterapp.TweetAdapter;
import com.codepath.apps.twitterapp.TwitterClientApp;
import com.codepath.apps.twitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TweetsFragment extends Fragment {
		
	protected static final int RESULT_OK = 200;
	TweetAdapter adapter;
	ListView lvTweets;
	ArrayList<Tweet> tweets;
	Tweet lastVisibleTweet;
	
	@Override
	public View onCreateView(LayoutInflater inf, ViewGroup parent, Bundle savedInstanceState) {
		setViews();
//		getTweets();
		
		return inf.inflate(R.layout.fragment_tweets, parent, false);
	}
	
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//	}
	
	public void setViews() {
//		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		
//        lvTweets.setOnScrollListener(new EndlessScrollListener() {
//        	@Override
//        	public void onLoadMore(int page, int totalItemsCount) {
//        		if(lastVisibleTweet!=null) {
//        			Log.d("scroll","get more data from: " + String.valueOf(lastVisibleTweet.getId()));
//        			getOlderTweets(lastVisibleTweet.getId());
//        		}
//        		else
//        			Log.d("scroll","something went wrong");
//        	}
//        });
	}
	
	public void addTweets(JSONArray jsonTweets, boolean clearOld) {
		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		if(lvTweets==null)
			Log.d("ERROR","lvTweets NULL");
		tweets = Tweet.fromJson(jsonTweets);
		if(clearOld) {			
			adapter = new TweetAdapter(getActivity(), tweets);
			lvTweets.setAdapter(adapter);
		}
		else {
			adapter.addAll(tweets);
		}
		lastVisibleTweet = adapter.getItem(adapter.getCount()-1);
	}
	
	public void addTweet(Tweet tweet) {
		adapter.insert(tweet,0);
	}

	
	public void getOlderTweets(long fromTweet) {
		TwitterClientApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONArray JsonTweets) {
				addTweets(JsonTweets, false);
			}
		}, fromTweet);
	}
	

}
