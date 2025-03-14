package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 24-Jun-2020.
 */

public class TopUpHistory {
    @SerializedName("NOHP")
    @Expose
    private String nOHP;
    @SerializedName("KETERANGAN")
    @Expose
    private String kETERANGAN;
    @SerializedName("CREATED")
    @Expose
    private String cREATED;
    @SerializedName("UPDATERS")
    @Expose
    private String uPDATERS;
    @SerializedName("LIMITS")
    @Expose
    private String lIMITS;
    @SerializedName("STATUS")
    @Expose
    private String sTATUS;
    @SerializedName("NOMINAL")
    @Expose
    private String nOMINAL;
    @SerializedName("NOTRANS")
    @Expose
    private String nOTRANS;
    @SerializedName("METODE_BAYAR")
    @Expose
    private String mETODEBAYAR;
    @SerializedName("NOMINAL_RAW")
    @Expose
    private String nOMINALRAW;

    public String getnOHP() {
        return nOHP;
    }

    public void setnOHP(String nOHP) {
        this.nOHP = nOHP;
    }

    public String getkETERANGAN() {
        return kETERANGAN;
    }

    public void setkETERANGAN(String kETERANGAN) {
        this.kETERANGAN = kETERANGAN;
    }

    public String getcREATED() {
        return cREATED;
    }

    public void setcREATED(String cREATED) {
        this.cREATED = cREATED;
    }

    public String getuPDATERS() {
        return uPDATERS;
    }

    public void setuPDATERS(String uPDATERS) {
        this.uPDATERS = uPDATERS;
    }

    public String getlIMITS() {
        return lIMITS;
    }

    public void setlIMITS(String lIMITS) {
        this.lIMITS = lIMITS;
    }

    public String getsTATUS() {
        return sTATUS;
    }

    public void setsTATUS(String sTATUS) {
        this.sTATUS = sTATUS;
    }

    public String getnOMINAL() {
        return nOMINAL;
    }

    public void setnOMINAL(String nOMINAL) {
        this.nOMINAL = nOMINAL;
    }

    public String getnOTRANS() {
        return nOTRANS;
    }

    public void setnOTRANS(String nOTRANS) {
        this.nOTRANS = nOTRANS;
    }

    public String getmETODEBAYAR() {
        return mETODEBAYAR;
    }

    public void setmETODEBAYAR(String mETODEBAYAR) {
        this.mETODEBAYAR = mETODEBAYAR;
    }

    public String getnOMINALRAW() {
        return nOMINALRAW;
    }

    public void setnOMINALRAW(String nOMINALRAW) {
        this.nOMINALRAW = nOMINALRAW;
    }
}
