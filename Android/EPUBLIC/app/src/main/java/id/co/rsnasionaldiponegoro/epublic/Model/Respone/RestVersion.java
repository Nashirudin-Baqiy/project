package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 12/6/2018.
 */

public class RestVersion {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("versionCode")
    @Expose
    private String versionCode;
    @SerializedName("forceUpdate")
    @Expose
    private String forceUpdate;
    @SerializedName("versionName")
    @Expose
    private String versionName;
    @SerializedName("csWa")
    @Expose
    private String csWa;
    @SerializedName("csWAMedis")
    @Expose
    private String csWAMedis;
    @SerializedName("csWANonMedis")
    @Expose
    private String csWANonMedis;

    public String getCsWAMedis() {
        return csWAMedis;
    }

    public void setCsWAMedis(String csWAMedis) {
        this.csWAMedis = csWAMedis;
    }

    public String getCsWANonMedis() {
        return csWANonMedis;
    }

    public void setCsWANonMedis(String csWANonMedis) {
        this.csWANonMedis = csWANonMedis;
    }

    public String getCsWa() {
        return csWa;
    }

    public void setCsWa(String csWa) {
        this.csWa = csWa;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(String forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
