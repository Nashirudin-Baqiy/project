package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 1/16/2019.
 */

public class Rest {
    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("TIC_ID")
    @Expose
    private String TIC_ID;

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

    public String getTIC_ID() {
        return TIC_ID;
    }

    public void setTIC_ID(String TIC_ID) {
        this.TIC_ID = TIC_ID;
    }
}
