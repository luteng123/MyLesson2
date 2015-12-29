package com.luteng.lesson2.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by John on 2015/12/29.
 */
public class Item {
    private long userID;
    private String userIcon;
    private String userName;
    private String content;
    private String image;

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public Item(){}
    public Item(JSONObject object) throws JSONException {
        if(!object.isNull("user")){
            userIcon = object.getJSONObject("user").getString("icon");
            userName = object.getJSONObject("user").getString("login");
            userID = object.getJSONObject("user").getLong("id");
        }

        if (!object.isNull("image")){
            image = object.getString("image");
        }
        content = object.getString("content");

    }
    public Item(String userIcon, String userName, String content, String image) {
        this.userIcon = userIcon;
        this.userName = userName;
        this.content = content;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Item{" +
                "userIcon='" + userIcon + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
