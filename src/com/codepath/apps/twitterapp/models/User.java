package com.codepath.apps.twitterapp.models;

import org.json.JSONObject;

public class User {
	
	String name;
    long id;
    String screen_name;
    String profile_image_url;
    String profile_background_image_url;
    int statuses_count;
    int followers_count;
    int friends_count;
	int following_count;
	String description;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getScreenName() {
        return screen_name;
    }

    public String getProfileImageUrl(){
        return this.profile_image_url;
    }
    
    public String getProfileBackgroundImageUrl() {
        return profile_background_image_url;
    }

    public int getNumTweets() {
        return statuses_count;
    }

    public int getFollowersCount() {
        return followers_count;
    }

    public int getFriendsCount() {
        return friends_count;
    }

    public String getDescription() {
    	return description;
    }
    
    public int getFollowingCount() {
    	return following_count;
    }
    
    public static User fromJson(JSONObject json) {
        User u = new User();

//        try {
//            u.jsonObject = json;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            u.name = json.getString("name");
            u.id = json.getLong("id");
            u.screen_name=json.getString("screen_name");
            u.profile_image_url=json.getString("profile_image_url");
            u.profile_background_image_url=json.getString("profile_background_image_url");
            u.statuses_count = json.getInt("statuses_count");
            u.followers_count = json.getInt("followers_count");
            u.friends_count = json.getInt("friends_count");
			u.following_count = json.getInt("friends_count");
			u.description = json.getString("description");
		}
        catch(Exception e) {
			e.printStackTrace();
		}
        return u;
    }


}
