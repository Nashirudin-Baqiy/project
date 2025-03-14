package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Asuransi;

/**
 * Created by xsanz on 1/27/2020.
 */

public class RestPenjaminAsuransi {
    @SerializedName("data")
    @Expose
    private List<Asuransi> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Asuransi> getData() {
        return data;
    }

    public void setData(List<Asuransi> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
