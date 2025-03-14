package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.AntrianPoli;

/**
 * Created by xsanz on 11/12/2018.
 */

public class RestAntrianPoli {
    @SerializedName("data")
    @Expose
    private List<AntrianPoli> antrianPoli= null;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public List<AntrianPoli> getAntrianPoli() {
        return antrianPoli;
    }

    public void setAntrianPoli(List<AntrianPoli> antrianPoli) {
        this.antrianPoli = antrianPoli;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}

