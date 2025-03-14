package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.AntrianCS;

/**
 * Created by xsanz on 11/12/2018.
 */

public class RestAntrianCS {
    @SerializedName("data")
    @Expose
    private List<AntrianCS> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<AntrianCS> getData() {
        return data;
    }

    public void setData(List<AntrianCS> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
