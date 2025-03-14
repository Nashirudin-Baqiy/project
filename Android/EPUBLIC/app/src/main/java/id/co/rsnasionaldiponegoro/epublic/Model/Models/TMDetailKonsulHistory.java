package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 29-Jun-2020.
 */

public class TMDetailKonsulHistory {
    @SerializedName("FRPNOTRANSAKSIKJ")
    @Expose
    private String fRPNOTRANSAKSIKJ;
    @SerializedName("FAPANTRIDOKTER")
    @Expose
    private String fAPANTRIDOKTER;
    @SerializedName("FAPKELUHAN")
    @Expose
    private String fAPKELUHAN;
    @SerializedName("FAPKELUHANKET")
    @Expose
    private String fAPKELUHANKET;
    @SerializedName("FAPSTATUSPROSES")
    @Expose
    private String fAPSTATUSPROSES;
    @SerializedName("FAPPASIEN_ID")
    @Expose
    private String fAPPASIENID;
    @SerializedName("FAPNOTRANSAKSI")
    @Expose
    private String fAPNOTRANSAKSI;
    @SerializedName("MRPKD_PENYAKIT")
    @Expose
    private String mRPKDPENYAKIT;
    @SerializedName("PENYAKIT")
    @Expose
    private String pENYAKIT;

    @SerializedName("MRDSOAPIKET")
    @Expose
    private String mRDSOAPIKET;

    @SerializedName("FAP_DATE_CREATED")
    @Expose
    private String FAP_DATE_CREATED;

    public String getfRPNOTRANSAKSIKJ() {
        return fRPNOTRANSAKSIKJ;
    }

    public void setfRPNOTRANSAKSIKJ(String fRPNOTRANSAKSIKJ) {
        this.fRPNOTRANSAKSIKJ = fRPNOTRANSAKSIKJ;
    }

    public String getfAPANTRIDOKTER() {
        return fAPANTRIDOKTER;
    }

    public void setfAPANTRIDOKTER(String fAPANTRIDOKTER) {
        this.fAPANTRIDOKTER = fAPANTRIDOKTER;
    }

    public String getfAPKELUHAN() {
        return fAPKELUHAN;
    }

    public void setfAPKELUHAN(String fAPKELUHAN) {
        this.fAPKELUHAN = fAPKELUHAN;
    }

    public String getfAPKELUHANKET() {
        return fAPKELUHANKET;
    }

    public void setfAPKELUHANKET(String fAPKELUHANKET) {
        this.fAPKELUHANKET = fAPKELUHANKET;
    }

    public String getfAPSTATUSPROSES() {
        return fAPSTATUSPROSES;
    }

    public void setfAPSTATUSPROSES(String fAPSTATUSPROSES) {
        this.fAPSTATUSPROSES = fAPSTATUSPROSES;
    }

    public String getfAPPASIENID() {
        return fAPPASIENID;
    }

    public void setfAPPASIENID(String fAPPASIENID) {
        this.fAPPASIENID = fAPPASIENID;
    }

    public String getfAPNOTRANSAKSI() {
        return fAPNOTRANSAKSI;
    }

    public void setfAPNOTRANSAKSI(String fAPNOTRANSAKSI) {
        this.fAPNOTRANSAKSI = fAPNOTRANSAKSI;
    }

    public String getmRPKDPENYAKIT() {
        return mRPKDPENYAKIT;
    }

    public void setmRPKDPENYAKIT(String mRPKDPENYAKIT) {
        this.mRPKDPENYAKIT = mRPKDPENYAKIT;
    }

    public String getpENYAKIT() {
        return pENYAKIT;
    }

    public void setpENYAKIT(String pENYAKIT) {
        this.pENYAKIT = pENYAKIT;
    }

    public String getmRDSOAPIKET() {
        return mRDSOAPIKET;
    }

    public void setmRDSOAPIKET(String mRDSOAPIKET) {
        this.mRDSOAPIKET = mRDSOAPIKET;
    }

    public String getFAP_DATE_CREATED() {
        return FAP_DATE_CREATED;
    }

    public void setFAP_DATE_CREATED(String FAP_DATE_CREATED) {
        this.FAP_DATE_CREATED = FAP_DATE_CREATED;
    }
}
