package com.codepath.apps.twitterapp;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.codepath.apps.twitterapp.models.Tweet;
import com.codepath.apps.twitterapp.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends Activity {

	ImageView iv_pic;
    TextView tv_firstname;
    TextView tv_status;
    TextView tv_follower;
    TextView tv_following;
    ListView lv_status;
    public long max_id=0;
    public ProfileAdapter adapter;
    String sname="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		this.setTitle("Profile");
		
		iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_firstname = (TextView) findViewById(R.id.tv_firstname);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_follower = (TextView) findViewById(R.id.tv_followers);
        tv_following= (TextView) findViewById(R.id.tv_following);
        lv_status = (ListView) findViewById(R.id.lv_status);
        
        if(getIntent().hasExtra("sname")){
            sname = getIntent().getStringExtra("sname");
            Log.d("sname",sname);
         }
        TwitterClientApp.getRestClient().getUserTimeline(new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(JSONArray jsonTweets){
                ArrayList<Tweet> tweets = Tweet.fromJson(jsonTweets);
                try {
	                User user = User.fromJson(jsonTweets.getJSONObject(0).getJSONObject("user"));
	                ImageLoader.getInstance().displayImage(user.getProfileImageUrl(), iv_pic);
	                tv_status.setText((user.getDescription()));
	                tv_firstname.setText(user.getName());
	                tv_following.setText(Integer.toString(user.getFollowingCount()).concat(" Following"));
	                tv_follower.setText(Integer.toString(user.getFollowersCount()).concat(" Followers"));
	                ActionBar actionBar = getActionBar();
	                actionBar.setTitle("@".concat(user.getScreenName()));

                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                adapter = new ProfileAdapter(getBaseContext(), tweets);
                lv_status.setAdapter(adapter);
            }
        }, sname);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

}
