package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.DiaryBundaRI;

/**
 * Created by xsanz on 10/1/2018.
 */

public class RestDiaryBundaRI {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("data")
    @Expose
    private List<DiaryBundaRI> data = null;

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

    public List<DiaryBundaRI> getData() {
        return data;
    }

    public void setData(List<DiaryBundaRI> data) {
        this.data = data;
    }
}
