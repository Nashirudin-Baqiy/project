package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Keluarga;

/**
 * Created by xsanz on 2/1/2020.
 */

public class RestListKeluarga {
    @SerializedName("keluarga")
    @Expose
    private List<Keluarga> keluarga = null;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("num_data")
    @Expose
    private Integer numData;

    public List<Keluarga> getKeluarga() {
        return keluarga;
    }

    public void setKeluarga(List<Keluarga> keluarga) {
        this.keluarga = keluarga;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getNumData() {
        return numData;
    }

    public void setNumData(Integer numData) {
        this.numData = numData;
    }
}
