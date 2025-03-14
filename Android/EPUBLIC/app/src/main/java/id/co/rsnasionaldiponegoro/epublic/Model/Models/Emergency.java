package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 9/25/2018.
 */

public class Emergency {
    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("TLPN_BUNDA")
    @Expose
    private String TLPNBUNDA;
    @SerializedName("TLPN_IGD")
    @Expose
    private String TLPNIGD;
    @SerializedName("LATITUDE")
    @Expose
    private String LATITUDE;
    @SerializedName("LONGITUDE")
    @Expose
    private String LONGITUDE;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTLPNBUNDA() {
        return TLPNBUNDA;
    }

    public void setTLPNBUNDA(String TLPNBUNDA) {
        this.TLPNBUNDA = TLPNBUNDA;
    }

    public String getTLPNIGD() {
        return TLPNIGD;
    }

    public void setTLPNIGD(String TLPNIGD) {
        this.TLPNIGD = TLPNIGD;
    }

    public String getLATITUDE() {
        return LATITUDE;
    }

    public void setLATITUDE(String LATITUDE) {
        this.LATITUDE = LATITUDE;
    }

    public String getLONGITUDE() {
        return LONGITUDE;
    }

    public void setLONGITUDE(String LONGITUDE) {
        this.LONGITUDE = LONGITUDE;
    }
}
