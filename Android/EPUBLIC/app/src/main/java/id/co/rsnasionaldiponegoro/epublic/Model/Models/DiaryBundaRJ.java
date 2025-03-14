package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 10/1/2018.
 */

public class DiaryBundaRJ {
    @SerializedName("KPKD_POLY")
    @Expose
    private String kPKDPOLY;
    @SerializedName("FMPKLINIKN")
    @Expose
    private String fMPKLINIKN;
    @SerializedName("KPTGL_PERIKSA")
    @Expose
    private String kPTGLPERIKSA;
    @SerializedName("KPKD_DOKTER")
    @Expose
    private String kPKDDOKTER;
    @SerializedName("FMDDOKTERN")
    @Expose
    private String fMDDOKTERN;
    @SerializedName("KPJAM_MASUK")
    @Expose
    private String kPJAMMASUK;
    @SerializedName("KPTGL_KELUAR")
    @Expose
    private String kPTGLKELUAR;
    @SerializedName("KPJAM_KELUAR")
    @Expose
    private String kPJAMKELUAR;
    @SerializedName("KPDIAGNOSA")
    @Expose
    private String kPDIAGNOSA;
    @SerializedName("KPNO_TRANSAKSI")
    @Expose
    private String kPNOTRANSAKSI;

    public String getkPKDPOLY() {
        return kPKDPOLY;
    }

    public void setkPKDPOLY(String kPKDPOLY) {
        this.kPKDPOLY = kPKDPOLY;
    }

    public String getfMPKLINIKN() {
        return fMPKLINIKN;
    }

    public void setfMPKLINIKN(String fMPKLINIKN) {
        this.fMPKLINIKN = fMPKLINIKN;
    }

    public String getkPTGLPERIKSA() {
        return kPTGLPERIKSA;
    }

    public void setkPTGLPERIKSA(String kPTGLPERIKSA) {
        this.kPTGLPERIKSA = kPTGLPERIKSA;
    }

    public String getkPKDDOKTER() {
        return kPKDDOKTER;
    }

    public void setkPKDDOKTER(String kPKDDOKTER) {
        this.kPKDDOKTER = kPKDDOKTER;
    }

    public String getfMDDOKTERN() {
        return fMDDOKTERN;
    }

    public void setfMDDOKTERN(String fMDDOKTERN) {
        this.fMDDOKTERN = fMDDOKTERN;
    }

    public String getkPJAMMASUK() {
        return kPJAMMASUK;
    }

    public void setkPJAMMASUK(String kPJAMMASUK) {
        this.kPJAMMASUK = kPJAMMASUK;
    }

    public String getkPTGLKELUAR() {
        return kPTGLKELUAR;
    }

    public void setkPTGLKELUAR(String kPTGLKELUAR) {
        this.kPTGLKELUAR = kPTGLKELUAR;
    }

    public String getkPJAMKELUAR() {
        return kPJAMKELUAR;
    }

    public void setkPJAMKELUAR(String kPJAMKELUAR) {
        this.kPJAMKELUAR = kPJAMKELUAR;
    }

    public String getkPDIAGNOSA() {
        return kPDIAGNOSA;
    }

    public void setkPDIAGNOSA(String kPDIAGNOSA) {
        this.kPDIAGNOSA = kPDIAGNOSA;
    }

    public String getkPNOTRANSAKSI() {
        return kPNOTRANSAKSI;
    }

    public void setkPNOTRANSAKSI(String kPNOTRANSAKSI) {
        this.kPNOTRANSAKSI = kPNOTRANSAKSI;
    }
}
