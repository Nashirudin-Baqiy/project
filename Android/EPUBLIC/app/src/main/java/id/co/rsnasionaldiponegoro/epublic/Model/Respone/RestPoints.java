package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 24-Jun-2020.
 */

public class RestPoints {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("POINTS")
    @Expose
    private String pOINTS;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getpOINTS() {
        return pOINTS;
    }

    public void setpOINTS(String pOINTS) {
        this.pOINTS = pOINTS;
    }
}
