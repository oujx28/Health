package com.luckystar.health.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/13.
 */

public class UserBean implements Serializable {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("headicon")
    public String headicon;

    @SerializedName("age")
    public String age;

    @SerializedName("sex")
    public String sex;

    @SerializedName("createtime")
    public long createtime;

    @SerializedName("createtimedate")
    public String createtimedate;

    @SerializedName("isbandmobile")
    public String isbandmobile;
}
