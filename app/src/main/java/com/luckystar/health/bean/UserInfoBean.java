package com.luckystar.health.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/20.
 */

public class UserInfoBean implements Serializable {

    @SerializedName("id")
    public String id;

    @SerializedName("deviceuserid")
    public String userid;

    @SerializedName("name")
    public String name;

    @SerializedName("headicon")
    public String headicon;

    @SerializedName("age")
    public String age;

    @SerializedName("sex")
    public String sex;

    @SerializedName("deviceid")
    public String deviceid;

    @SerializedName("height")
    public String height;

    @SerializedName("weight")
    public String weight;

    @SerializedName("cityid")
    public String cityid;

    @SerializedName("cityname")
    public String cityname;

    @SerializedName("phone")
    public String phone;

    @SerializedName("bloodtypeid")
    public String bloodtypeid;

    @SerializedName("bloodtypename")
    public String bloodtypename;

    @SerializedName("allergyid")
    public String allergyid;

    @SerializedName("allergyname")
    public String allergyname;

    @SerializedName("medicalid")
    public String medicalid;

    @SerializedName("medicalname")
    public String medicalname;

    @SerializedName("geneticid")
    public String geneticid;

    @SerializedName("geneticname")
    public String geneticname;

    @SerializedName("usergeneticname")
    public String usergeneticname;

    @SerializedName("descr")
    public String descr;

    @SerializedName("createtime")
    public long createtime;

    @SerializedName("createtimedate")
    public String createtimedate;
}
