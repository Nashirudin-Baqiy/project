package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xsanz on 1/9/2019.
 */

public class RestPendaftaranTanggal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<String> tanggal = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<String> getTanggal() {
        return tanggal;
    }

    public void setTanggal(List<String> tanggal) {
        this.tanggal = tanggal;
    }
}
