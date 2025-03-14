package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.TMDetailKonsulHistory;

/**
 * Created by xsanz on 29-Jun-2020.
 */

public class RestTMDetailKonsulHistory {

    @SerializedName("data")
    @Expose
    private List<TMDetailKonsulHistory> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;

    public List<TMDetailKonsulHistory> getData() {
        return data;
    }

    public void setData(List<TMDetailKonsulHistory> data) {
        this.data = data;
    }

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
}
