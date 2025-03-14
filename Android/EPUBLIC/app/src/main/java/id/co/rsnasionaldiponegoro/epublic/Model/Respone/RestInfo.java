package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Info;

/**
 * Created by xsanz on 9/26/2018.
 */

public class RestInfo {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("data")
    @Expose
    private ArrayList<Info> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ArrayList<Info> getData() {
        return data;
    }

    public void setData(ArrayList<Info> data) {
        this.data = data;
    }
}
