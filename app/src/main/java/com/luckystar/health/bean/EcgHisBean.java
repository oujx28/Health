package com.luckystar.health.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/19.
 */

public class EcgHisBean implements Serializable {
    @SerializedName("id")
    public int id;

    @SerializedName("dmuserid")
    public String userid;

    @SerializedName("dmusername")
    public String username;

    @SerializedName("heartrateurl")
    public String heartrateurl;

    @SerializedName("heartrate")
    public String heartrate;

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

    @SerializedName("heartEndata")
    public String heartEndata;

    @SerializedName("heartCndata")
    public String heartCndata;

    @Override
    public String toString() {
        return "EcgHisBean{" +
                "id=" + id +
                ", dmuserid='" + userid + '\'' +
                ", dmusername='" + username + '\'' +
                ", heartrateurl='" + heartrateurl + '\'' +
                ", heartrate=" + heartrate +
                ", conclusionid=" + conclusionid +
                ", conclusion='" + conclusion + '\'' +
                ", hintid=" + hintid +
                ", hint='" + hint + '\'' +
                ", suggestid=" + suggestid +
                ", suggest='" + suggest + '\'' +
                ", createtime=" + createtime +
                ", createtimedate='" + createtimedate + '\'' +
                ", heartEndata='" + heartEndata + '\'' +
                ", heartCndata='" + heartCndata + '\'' +
                '}';
    }
}
