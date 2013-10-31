package com.codepath.apps.twitterapp;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;
import android.util.Log;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "RbYqYbdHe2RtlKggu38bg";       // Change this
    public static final String REST_CONSUMER_SECRET = "gg9L1g7aM2crYQrkMoxHPr60GLJ95NvxfBvFGkYiM"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://twitterapp"; // Change this (here and in manifest)
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    //Get twitter home timeline
    public void getHomeTimeline(AsyncHttpResponseHandler handler){
    	String url = getApiUrl("statuses/home_timeline.json?count=25");
    	client.get(url, null, handler);
    }
    //gets twitter home timeline with max count
    public void getHomeTimeline(AsyncHttpResponseHandler handler, long fromTweet){
    	String url = getApiUrl("statuses/home_timeline.json?count=25&max_id=" + String.valueOf(fromTweet));
    	client.get(url, null, handler);
    }
    
    //gets mentions
    public void getMentionsTimeline(AsyncHttpResponseHandler handler){
    	String url = getApiUrl("statuses/mentions_timeline.json?count=25");
    	client.get(url, null, handler);
    }
    
  //gets user tweets
    public void getUserTimeline(AsyncHttpResponseHandler handler){
    	String url = getApiUrl("statuses/user_timeline.json?count=25");
    	client.get(url, null, handler);
    }
    
  //gets user tweets
    public void getUserTimeline(AsyncHttpResponseHandler handler, String name){
    	String url;
    	if(name.length()==0) 
    		url = getApiUrl("statuses/user_timeline.json?count=25");
    	else 
    		url = getApiUrl("statuses/user_timeline.json?count=25&screen_name=" + name);
    	Log.d("url",url);
    	client.get(url, null, handler);
    	
    }
    
    //API to post a tweet
    public void postTweet(AsyncHttpResponseHandler handler, String status){
        String url = getApiUrl("statuses/update.json");
        RequestParams rParams = new RequestParams();
        rParams.put("status",status );
        client.post(url, rParams, handler);
    }
    
    
    /* 1. Define the endpoint URL with getApiUrl and pass a relative path to the endpoint
     * 	  i.e getApiUrl("statuses/home_timeline.json");
     * 2. Define the parameters to pass to the request (query or body)
     *    i.e RequestParams params = new RequestParams("foo", "bar");
     * 3. Define the request method and make a call to the client
     *    i.e client.get(apiUrl, params, handler);
     *    i.e client.post(apiUrl, params, handler);
     */
}