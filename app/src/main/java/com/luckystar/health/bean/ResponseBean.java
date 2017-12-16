package com.luckystar.health.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/13.
 */

public class ResponseBean<T> implements Serializable {

    @SerializedName("status")
    public String status;

    @SerializedName("describe")
    public String describe;

    @SerializedName("obj")
    public T obj;

    @SerializedName("objlist")
    public List<T> objlist;

    @SerializedName("objcount")
    public String objcount;
}
