package com.example.assignment3;

public class base_item {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public base_item(int imageResouce, String text1, String text2){
        mImageResource = imageResouce;
        mText1 = text1;
        mText2 = text2;
    }

    public int getmImageResource(){
        return mImageResource;
    }
    public String getmText1(){
        return mText1;
    }

    public String getmText2(){
        return mText2;
    }
}