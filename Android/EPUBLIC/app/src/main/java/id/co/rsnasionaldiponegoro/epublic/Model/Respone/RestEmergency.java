package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Emergency;

/**
 * Created by xsanz on 9/25/2018.
 */

public class RestEmergency {
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
    private List<Emergency> data = null;

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

    public List<Emergency> getData() {
        return data;
    }

    public void setData(List<Emergency> data) {
        this.data = data;
    }
}
