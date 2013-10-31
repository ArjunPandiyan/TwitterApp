package com.codepath.apps.twitterapp;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterapp.models.Tweet;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetAdapter extends ArrayAdapter<Tweet>{

	ImageView ivProfile;
	TextView tvName;
    TextView tvBody;
      
	public TweetAdapter(Context context, List<Tweet> objects) {
		super(context, 0, objects);
		// TODO Auto-generated constructor stub
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        
        if(view == null){
                LayoutInflater inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflator.inflate(R.layout.tweet_item,null);
                
        }
        
         Tweet tweet= getItem(position);

         ivProfile = (ImageView) view.findViewById(R.id.ivProfile);
         ivProfile.setTag(tweet.getUser().getScreenName());
         ivProfile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
            	 Intent i = new Intent(getContext(), ProfileActivity.class);
            	 i.putExtra("sname", v.getTag().toString() );
            	 getContext().startActivity(i);
             }
        });
         
         ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), ivProfile);

         tvName = (TextView) view.findViewById(R.id.tvName); 
         String formattedName = "<b>"+tweet.getUser().getName() +"</b>" + "<small><font color='#777777>@"+tweet.getUser().getScreenName()+"</font></small>";
         tvName.setText(Html.fromHtml(formattedName));
          
         tvBody = (TextView) view.findViewById(R.id.tvBody);
         tvBody.setText(Html.fromHtml(tweet.getBody()));
        
        return view;
	}
}
