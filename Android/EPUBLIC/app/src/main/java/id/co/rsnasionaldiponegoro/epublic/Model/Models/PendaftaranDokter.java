package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 12/27/2018.
 */

public class PendaftaranDokter {
    @SerializedName("FMDDOKTERN")
    @Expose
    private String fMDDOKTERN;
    @SerializedName("FMDDOKTER_ID")
    @Expose
    private String fMDDOKTERID;
    @SerializedName("FMJHARI")
    @Expose
    private String fMJHARI;
    @SerializedName("Shift")
    @Expose
    private String shift;
    @SerializedName("FMKDLIMIT")
    @Expose
    private String fMKDLIMIT;
    @SerializedName("terpakai")
    @Expose
    private String terpakai;
    @SerializedName("libur")
    @Expose
    private Boolean libur;

    public Boolean getLibur() {
        return libur;
    }

    public void setLibur(Boolean libur) {
        this.libur = libur;
    }

    public String getfMDDOKTERN() {
        return fMDDOKTERN;
    }

    public void setfMDDOKTERN(String fMDDOKTERN) {
        this.fMDDOKTERN = fMDDOKTERN;
    }

    public String getfMDDOKTERID() {
        return fMDDOKTERID;
    }

    public void setfMDDOKTERID(String fMDDOKTERID) {
        this.fMDDOKTERID = fMDDOKTERID;
    }

    public String getfMJHARI() {
        return fMJHARI;
    }

    public void setfMJHARI(String fMJHARI) {
        this.fMJHARI = fMJHARI;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getfMKDLIMIT() {
        return fMKDLIMIT;
    }

    public void setfMKDLIMIT(String fMKDLIMIT) {
        this.fMKDLIMIT = fMKDLIMIT;
    }

    public String getTerpakai() {
        return terpakai;
    }

    public void setTerpakai(String terpakai) {
        this.terpakai = terpakai;
    }
}
