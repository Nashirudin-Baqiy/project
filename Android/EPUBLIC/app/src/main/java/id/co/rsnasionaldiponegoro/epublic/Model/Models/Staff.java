package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 9/27/2018.
 */

public class Staff {
    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("ID_KTGR_STAFF")
    @Expose
    private String IDKTGRSTAFF;
    @SerializedName("NAMA")
    @Expose
    private String NAMA;
    @SerializedName("DESKRIPSI")
    @Expose
    private String DESKRIPSI;
    @SerializedName("GAMBAR")
    @Expose
    private String GAMBAR;
    @SerializedName("CAPTION")
    @Expose
    private String CAPTION;
    @SerializedName("MAPPING_DOKTER")
    @Expose
    private String MAPPINGDOKTER;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDKTGRSTAFF() {
        return IDKTGRSTAFF;
    }

    public void setIDKTGRSTAFF(String IDKTGRSTAFF) {
        this.IDKTGRSTAFF = IDKTGRSTAFF;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getDESKRIPSI() {
        return DESKRIPSI;
    }

    public void setDESKRIPSI(String DESKRIPSI) {
        this.DESKRIPSI = DESKRIPSI;
    }

    public String getGAMBAR() {
        return GAMBAR;
    }

    public void setGAMBAR(String GAMBAR) {
        this.GAMBAR = GAMBAR;
    }

    public String getCAPTION() {
        return CAPTION;
    }

    public void setCAPTION(String CAPTION) {
        this.CAPTION = CAPTION;
    }

    public String getMAPPINGDOKTER() {
        return MAPPINGDOKTER;
    }

    public void setMAPPINGDOKTER(String MAPPINGDOKTER) {
        this.MAPPINGDOKTER = MAPPINGDOKTER;
    }
}
