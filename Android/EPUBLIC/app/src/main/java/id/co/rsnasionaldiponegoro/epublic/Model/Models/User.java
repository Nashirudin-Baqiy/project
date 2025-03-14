package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by xsanz on 9/18/2018.
 */

public class User implements Serializable {

    @SerializedName("ID_USERS")
    @Expose
    private String IDUSERS;
    @SerializedName("NAMA")
    @Expose
    private String NAMA;
    @SerializedName("NOTELP")
    @Expose
    private String NOTELP;
    @SerializedName("EMAIL")
    @Expose
    private String EMAIL;
    @SerializedName("NORM")
    @Expose
    private String NORM;
    @SerializedName("TGLLAHIR")
    @Expose
    private String TGLLAHIR;
    @SerializedName("CREATE_DATE")
    @Expose
    private String CREATEDATE;
    @SerializedName("CHANGE_DATE")
    @Expose
    private String CHANGEDATE;
    @SerializedName("PHOTO")
    @Expose
    private String PHOTO;

    public String getIDUSERS() {
        return IDUSERS;
    }

    public void setIDUSERS(String IDUSERS) {
        this.IDUSERS = IDUSERS;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getNOTELP() {
        return NOTELP;
    }

    public void setNOTELP(String NOTELP) {
        this.NOTELP = NOTELP;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getNORM() {
        return NORM;
    }

    public void setNORM(String NORM) {
        this.NORM = NORM;
    }

    public String getTGLLAHIR() {
        return TGLLAHIR;
    }

    public void setTGLLAHIR(String TGLLAHIR) {
        this.TGLLAHIR = TGLLAHIR;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getCHANGEDATE() {
        return CHANGEDATE;
    }

    public void setCHANGEDATE(String CHANGEDATE) {
        this.CHANGEDATE = CHANGEDATE;
    }

    public String getPHOTO() {
        return PHOTO;
    }

    public void setPHOTO(String PHOTO) {
        this.PHOTO = PHOTO;
    }
}
