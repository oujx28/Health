package com.luckystar.health.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/19.
 */

public class BpHisBean implements Serializable {
    @SerializedName("id")
    public int id;

    @SerializedName("dmuserid")
    public String userid;

    @SerializedName("dmusername")
    public String username;

    @SerializedName("dbp")
    public String dbp;

    @SerializedName("sbp")
    public String sbp;

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

    @SerializedName("typeid")
    public String typeid;

    @Override
    public String toString() {
        return "BpHisBean{" +
                "id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", dbp='" + dbp + '\'' +
                ", sbp='" + sbp + '\'' +
                ", conclusionid='" + conclusionid + '\'' +
                ", conclusion='" + conclusion + '\'' +
                ", hint='" + hint + '\'' +
                ", suggest='" + suggest + '\'' +
                ", createtimedate='" + createtimedate + '\'' +
                '}';
    }

}
