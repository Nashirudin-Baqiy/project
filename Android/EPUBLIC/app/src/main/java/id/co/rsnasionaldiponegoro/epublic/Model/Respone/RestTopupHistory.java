package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.TopUpHistory;

/**
 * Created by xsanz on 24-Jun-2020.
 */

public class RestTopupHistory {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<TopUpHistory> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<TopUpHistory> getData() {
        return data;
    }

    public void setData(List<TopUpHistory> data) {
        this.data = data;
    }
}
