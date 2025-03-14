package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 12/27/2018.
 */

public class PendaftaranLogin {
    @SerializedName("NAMAPASIEN")
    @Expose
    private String nAMAPASIEN;
    @SerializedName("KD_PASIEN")
    @Expose
    private String kDPASIEN;
    @SerializedName("TGL_LAHIR")
    @Expose
    private String tGLLAHIR;
    @SerializedName("ALAMAT")
    @Expose
    private String aLAMAT;
    @SerializedName("TELEPON")
    @Expose
    private String tELEPON;

    public String getnAMAPASIEN() {
        return nAMAPASIEN;
    }

    public void setnAMAPASIEN(String nAMAPASIEN) {
        this.nAMAPASIEN = nAMAPASIEN;
    }

    public String getkDPASIEN() {
        return kDPASIEN;
    }

    public void setkDPASIEN(String kDPASIEN) {
        this.kDPASIEN = kDPASIEN;
    }

    public String gettGLLAHIR() {
        return tGLLAHIR;
    }

    public void settGLLAHIR(String tGLLAHIR) {
        this.tGLLAHIR = tGLLAHIR;
    }

    public String getaLAMAT() {
        return aLAMAT;
    }

    public void setaLAMAT(String aLAMAT) {
        this.aLAMAT = aLAMAT;
    }

    public String gettELEPON() {
        return tELEPON;
    }

    public void settELEPON(String tELEPON) {
        this.tELEPON = tELEPON;
    }
}
