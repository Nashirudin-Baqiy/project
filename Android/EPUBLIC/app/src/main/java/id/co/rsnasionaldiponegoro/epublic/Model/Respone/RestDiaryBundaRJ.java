package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.DiaryBundaRJ;

/**
 * Created by xsanz on 10/1/2018.
 */

public class RestDiaryBundaRJ {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("data")
    @Expose
    private List<DiaryBundaRJ> data = null;

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

    public List<DiaryBundaRJ> getData() {
        return data;
    }

    public void setData(List<DiaryBundaRJ> data) {
        this.data = data;
    }
}
