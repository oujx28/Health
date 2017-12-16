package com.luckystar.health.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/19.
 */

public class TempHisBean implements Serializable {
    @SerializedName("id")
    public int id;

    @SerializedName("dmuserid")
    public String userid;

    @SerializedName("dmusername")
    public String username;

    @SerializedName("temperature")
    public String temperature;

    @SerializedName("conclusionid")
    public int conclusionid;

    @SerializedName("conclusion")
    public String conclusion;

    @SerializedName("hintid")
    public int hintid;

    @SerializedName("hint")
    public String hint;

    @SerializedName("suggestid")
    public int suggestid;

    @SerializedName("suggest")
    public String suggest;

    @SerializedName("createtime")
    public String createtime;

    @SerializedName("createtimedate")
    public String createtimedate;

    @Override
    public String toString() {
        return "TempHisBean{" +
                "id=" + id +
                ", dmuserid='" + userid + '\'' +
                ", dmusername='" + username + '\'' +
                ", temperature='" + temperature + '\'' +
                ", conclusionid=" + conclusionid +
                ", conclusion='" + conclusion + '\'' +
                ", hintid=" + hintid +
                ", hint='" + hint + '\'' +
                ", suggestid=" + suggestid +
                ", suggest='" + suggest + '\'' +
                ", createtime=" + createtime +
                ", createtimedate='" + createtimedate + '\'' +
                '}';
    }
}
