package com.luckystar.health.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/23.
 */

public class HealthTabBean implements Serializable {
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("seq")
    public int seq;

    @Override
    public String toString() {
        return "HealthTabBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", seq='" + seq + '\'' +
                '}';
    }

}
