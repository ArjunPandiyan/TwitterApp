package com.codepath.apps.twitterapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterapp.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileAdapter extends ArrayAdapter<Tweet>{
	 ImageView iv_item_pic;
	    TextView tv_item_firstname;
	    TextView tv_item_tweet;


	    public ProfileAdapter(Context context, ArrayList<Tweet> tweets) {
	        super(context, 0, tweets);
	    }

	    public View getView(int position, View convertView, ViewGroup parent){
	        View view = convertView;
	        if(view == null){
	            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            view = inflater.inflate(R.layout.profile_item, null);
	        }

	        Tweet tweet = getItem(position);

	        tv_item_tweet = (TextView) view.findViewById(R.id.tv_item_tweet);
	        tv_item_tweet.setText(tweet.getBody());

	        iv_item_pic = (ImageView) view.findViewById(R.id.iv_item_pic);
	        ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), iv_item_pic);

	        tv_item_firstname = (TextView) view.findViewById(R.id.tv_item_firstname);
	        tv_item_firstname.setText(tweet.getUser().getName());



	        return view;
	    }
}
