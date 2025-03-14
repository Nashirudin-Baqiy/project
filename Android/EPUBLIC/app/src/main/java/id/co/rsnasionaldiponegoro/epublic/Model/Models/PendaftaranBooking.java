package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 12/27/2018.
 */

public class PendaftaranBooking {


    @SerializedName("FAPPASIEN_ID")
    @Expose
    private String FAPPASIEN_ID;

    @SerializedName("FAPNOTRANSAKSI")
    @Expose
    private String fAPNOTRANSAKSI;
    @SerializedName("FAPDOKTER_ID")
    @Expose
    private String fAPDOKTERID;
    @SerializedName("FMPKLINIKN")
    @Expose
    private String fMPKLINIKN;
    @SerializedName("FAPTGL_APPOITMENT")
    @Expose
    private String fAPTGLAPPOITMENT;

    @SerializedName("FAPSTATUSPROSES")
    @Expose
    private String FAPSTATUSPROSES;

    @SerializedName("FAPKELUHAN")
    @Expose
    private String FAPKELUHAN;

    @SerializedName("FAPANTRIDOKTER")
    @Expose
    private String FAPANTRIDOKTER;

    public String getFAPPASIEN_ID() {
        return FAPPASIEN_ID;
    }

    public void setFAPPASIEN_ID(String FAPPASIEN_ID) {
        this.FAPPASIEN_ID = FAPPASIEN_ID;
    }

    public String getfAPNOTRANSAKSI() {
        return fAPNOTRANSAKSI;
    }

    public void setfAPNOTRANSAKSI(String fAPNOTRANSAKSI) {
        this.fAPNOTRANSAKSI = fAPNOTRANSAKSI;
    }

    public String getfAPDOKTERID() {
        return fAPDOKTERID;
    }

    public void setfAPDOKTERID(String fAPDOKTERID) {
        this.fAPDOKTERID = fAPDOKTERID;
    }

    public String getfMPKLINIKN() {
        return fMPKLINIKN;
    }

    public void setfMPKLINIKN(String fMPKLINIKN) {
        this.fMPKLINIKN = fMPKLINIKN;
    }

    public String getfAPTGLAPPOITMENT() {
        return fAPTGLAPPOITMENT;
    }

    public void setfAPTGLAPPOITMENT(String fAPTGLAPPOITMENT) {
        this.fAPTGLAPPOITMENT = fAPTGLAPPOITMENT;
    }

    public String getFAPSTATUSPROSES() {
        return FAPSTATUSPROSES;
    }

    public void setFAPSTATUSPROSES(String FAPSTATUSPROSES) {
        this.FAPSTATUSPROSES = FAPSTATUSPROSES;
    }

    public String getFAPKELUHAN() {
        return FAPKELUHAN;
    }

    public void setFAPKELUHAN(String FAPKELUHAN) {
        this.FAPKELUHAN = FAPKELUHAN;
    }

    public String getFAPANTRIDOKTER() {
        return FAPANTRIDOKTER;
    }

    public void setFAPANTRIDOKTER(String FAPANTRIDOKTER) {
        this.FAPANTRIDOKTER = FAPANTRIDOKTER;
    }
}
