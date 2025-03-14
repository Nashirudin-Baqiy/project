package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranLogin;

/**
 * Created by xsanz on 12/27/2018.
 */

public class RestPendaftaranLogin {
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
    private PendaftaranLogin pendaftaranLogin;

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

    public PendaftaranLogin getPendaftaranLogin() {
        return pendaftaranLogin;
    }

    public void setPendaftaranLogin(PendaftaranLogin pendaftaranLogin) {
        this.pendaftaranLogin = pendaftaranLogin;
    }
}
