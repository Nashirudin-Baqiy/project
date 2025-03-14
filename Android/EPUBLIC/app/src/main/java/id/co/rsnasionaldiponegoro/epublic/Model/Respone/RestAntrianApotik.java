package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.AntrianApotik;

/**
 * Created by xsanz on 11/25/2018.
 */

public class RestAntrianApotik {

    @SerializedName("data")
    @Expose
    private List<AntrianApotik> data = null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<AntrianApotik> getData() {
        return data;
    }

    public void setData(List<AntrianApotik> data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
