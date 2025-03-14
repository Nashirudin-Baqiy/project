package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 9/25/2018.
 */

public class Contact {
    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("TLPN")
    @Expose
    private String TLPN;
    @SerializedName("EMAIL")
    @Expose
    private String EMAIL;
    @SerializedName("ALAMAT")
    @Expose
    private String ALAMAT;
    @SerializedName("IG")
    @Expose
    private String IG;
    @SerializedName("WEB")
    @Expose
    private String WEB;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTLPN() {
        return TLPN;
    }

    public void setTLPN(String TLPN) {
        this.TLPN = TLPN;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public String getIG() {
        return IG;
    }

    public void setIG(String IG) {
        this.IG = IG;
    }

    public String getWEB() {
        return WEB;
    }

    public void setWEB(String WEB) {
        this.WEB = WEB;
    }
}
