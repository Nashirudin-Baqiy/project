package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Staff;

/**
 * Created by xsanz on 9/27/2018.
 */

public class RestDokter {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("data")
    @Expose
    private List<Staff> data = null;

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

    public List<Staff> getData() {
        return data;
    }

    public void setData(List<Staff> data) {
        this.data = data;
    }
}
