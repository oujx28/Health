package com.luckystar.health.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/19.
 */

public class Spo2hHisBean implements Serializable {
    @SerializedName("id")
    public int id;

    @SerializedName("dmuserid")
    public String userid;

    @SerializedName("dmusername")
    public String username;

    @SerializedName("spo")
    public String spo;

    @SerializedName("pulse")
    public String pulse;

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

    @SerializedName("bloodoxygenurl")
    public String bloodoxygenurl;

    @Override
    public String toString() {
        return "Spo2hHisBean{" +
                "id=" + id +
                ", dmuserid='" + userid + '\'' +
                ", dmusername='" + username + '\'' +
                ", spo='" + spo + '\'' +
                ", pulse=" + pulse +
                ", conclusionid=" + conclusionid +
                ", conclusion='" + conclusion + '\'' +
                ", hintid=" + hintid +
                ", hint='" + hint + '\'' +
                ", suggestid=" + suggestid +
                ", suggest='" + suggest + '\'' +
                ", createtime=" + createtime +
                ", createtimedate='" + createtimedate + '\'' +
                ", bloodoxygenurl='" + bloodoxygenurl + '\'' +
                '}';
    }
}
