package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 12/27/2018.
 */

public class PendaftaranPoli {
    @SerializedName("FMPKLINIKN")
    @Expose
    private String fMPKLINIKN;
    @SerializedName("FMJKD_KLINIK")
    @Expose
    private String fMJKDKLINIK;

    public String getfMPKLINIKN() {
        return fMPKLINIKN;
    }

    public void setfMPKLINIKN(String fMPKLINIKN) {
        this.fMPKLINIKN = fMPKLINIKN;
    }

    public String getfMJKDKLINIK() {
        return fMJKDKLINIK;
    }

    public void setfMJKDKLINIK(String fMJKDKLINIK) {
        this.fMJKDKLINIK = fMJKDKLINIK;
    }
}
