package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 2/10/2020.
 */

public class RestOTPGen {
//    @SerializedName("success")
//    @Expose
//    private Boolean success;
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("pin")
//    @Expose
//    private Integer pin;
//    @SerializedName("future")
//    @Expose
//    private String future;
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

//    public Boolean getSuccess() {
//        return success;
//    }
//
//    public void setSuccess(Boolean success) {
//        this.success = success;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public Integer getPin() {
//        return pin;
//    }
//
//    public void setPin(Integer pin) {
//        this.pin = pin;
//    }
//
//    public String getFuture() {
//        return future;
//    }
//
//    public void setFuture(String future) {
//        this.future = future;
//    }
}
