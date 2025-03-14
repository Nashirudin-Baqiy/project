package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 10/1/2018.
 */

public class DiaryBundaRI {
    @SerializedName("FMSPESIALISASIN")
    @Expose
    private String fMSPESIALISASIN;
    @SerializedName("FMKKAMARN")
    @Expose
    private String fMKKAMARN;
    @SerializedName("FMKNAMA_KAMAR")
    @Expose
    private String fMKNAMAKAMAR;
    @SerializedName("PRWITGL_MASUK")
    @Expose
    private String pRWITGLMASUK;
    @SerializedName("FMDDOKTERN")
    @Expose
    private String fMDDOKTERN;
    @SerializedName("PRWIKPJAM_MASUK")
    @Expose
    private String pRWIKPJAMMASUK;
    @SerializedName("PRWITGL_KELUAR")
    @Expose
    private String pRWITGLKELUAR;
    @SerializedName("PRWIJAM_KELUAR")
    @Expose
    private String pRWIJAMKELUAR;
    @SerializedName("PRWINO_TRANSAKSI")
    @Expose
    private String pRWINOTRANSAKSI;
    @SerializedName("PRWINO_URUT")
    @Expose
    private String pRWINOURUT;

    public String getfMSPESIALISASIN() {
        return fMSPESIALISASIN;
    }

    public void setfMSPESIALISASIN(String fMSPESIALISASIN) {
        this.fMSPESIALISASIN = fMSPESIALISASIN;
    }

    public String getfMKKAMARN() {
        return fMKKAMARN;
    }

    public void setfMKKAMARN(String fMKKAMARN) {
        this.fMKKAMARN = fMKKAMARN;
    }

    public String getfMKNAMAKAMAR() {
        return fMKNAMAKAMAR;
    }

    public void setfMKNAMAKAMAR(String fMKNAMAKAMAR) {
        this.fMKNAMAKAMAR = fMKNAMAKAMAR;
    }

    public String getpRWITGLMASUK() {
        return pRWITGLMASUK;
    }

    public void setpRWITGLMASUK(String pRWITGLMASUK) {
        this.pRWITGLMASUK = pRWITGLMASUK;
    }

    public String getfMDDOKTERN() {
        return fMDDOKTERN;
    }

    public void setfMDDOKTERN(String fMDDOKTERN) {
        this.fMDDOKTERN = fMDDOKTERN;
    }

    public String getpRWIKPJAMMASUK() {
        return pRWIKPJAMMASUK;
    }

    public void setpRWIKPJAMMASUK(String pRWIKPJAMMASUK) {
        this.pRWIKPJAMMASUK = pRWIKPJAMMASUK;
    }

    public String getpRWITGLKELUAR() {
        return pRWITGLKELUAR;
    }

    public void setpRWITGLKELUAR(String pRWITGLKELUAR) {
        this.pRWITGLKELUAR = pRWITGLKELUAR;
    }

    public String getpRWIJAMKELUAR() {
        return pRWIJAMKELUAR;
    }

    public void setpRWIJAMKELUAR(String pRWIJAMKELUAR) {
        this.pRWIJAMKELUAR = pRWIJAMKELUAR;
    }

    public String getpRWINOTRANSAKSI() {
        return pRWINOTRANSAKSI;
    }

    public void setpRWINOTRANSAKSI(String pRWINOTRANSAKSI) {
        this.pRWINOTRANSAKSI = pRWINOTRANSAKSI;
    }

    public String getpRWINOURUT() {
        return pRWINOURUT;
    }

    public void setpRWINOURUT(String pRWINOURUT) {
        this.pRWINOURUT = pRWINOURUT;
    }
}
