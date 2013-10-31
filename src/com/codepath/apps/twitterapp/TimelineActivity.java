package com.codepath.apps.twitterapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.codepath.app.twitterapp.fragments.HomelineTweetsFragment;
import com.codepath.app.twitterapp.fragments.MentionsTweetsFragment;
import com.codepath.apps.twitterapp.models.Tweet;


public class TimelineActivity extends FragmentActivity implements TabListener {
	
	FragmentTransaction transaction;
//	MentionsTweetsFragment tweetsFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		this.setTitle("Twitter Client");
		this.setTitleColor(Color.BLUE);
//		Dynamically load tabs
		ActionBar actionBar = getActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	    actionBar.setDisplayShowTitleEnabled(true);
	    ActionBar.Tab tabHome = actionBar.newTab().setText("HOME").setTag("HOME").setIcon(R.drawable.ic_menu_home).setTabListener(this);
	    ActionBar.Tab tabMentions = actionBar.newTab().setText("MENTIONS").setTag("MENTIONS").setIcon(R.drawable.ic_tab_mention).setTabListener(this);
	    actionBar.addTab(tabHome);
	    actionBar.addTab(tabMentions);
	    actionBar.selectTab(tabHome);
		

//		tweetsFragment = (MentionsTweetsFragment) getSupportFragmentManager().findFragmentById(R.id.tweets_fragment);
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_tweet, menu);
		return true;
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()) {
//		case R.id.refresh:
//		  getMentionsTweets();
//		  break;
		case R.id.addtweet:
		  Intent iCreate = new Intent(this, CreateTweetActivity.class);
		  startActivityForResult(iCreate,200);
		  break; 
		case R.id.profile:
			Intent iProfile = new Intent(this, ProfileActivity.class);
			startActivityForResult(iProfile,200);
			break; 
	}
		return true;
	}


	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
//		Nothing
	}


	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
//		Dynamically load fragment
		Log.d("DEBUG",tab.getTag().toString());
		if(tab.getTag().toString().equals("HOME")) {
			transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.fragment_box, new HomelineTweetsFragment());
			transaction.commit();
		}
		else if(tab.getTag().toString().equals("MENTIONS")) {
			transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.fragment_box, new MentionsTweetsFragment());
			transaction.commit();
		}
		
	}


	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
//		 Nothing
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {		
		if (resultCode == RESULT_OK && requestCode == 200) {		
			String tweetResponse = data.getExtras().getString("result");
			try {
				JSONObject jsonTweet = new JSONObject(tweetResponse);
					//Tweet newTweet = Tweet.fromJson(jsonTweet);
					
					//adapter.insert(newTweet, 0);
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
