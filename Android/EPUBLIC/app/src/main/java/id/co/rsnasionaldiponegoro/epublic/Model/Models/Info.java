package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 9/26/2018.
 */

public class Info {
    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("NAMA")
    @Expose
    private String NAMA;
    @SerializedName("DESKRIPSI")
    @Expose
    private String DESKRIPSI;
    @SerializedName("THUMB_GAMBAR")
    @Expose
    private String THUMB_GAMBAR;
    @SerializedName("FULL_GAMBAR")
    @Expose
    private String FULL_GAMBAR;
    @SerializedName("TYPE_PROMO")
    @Expose
    private String TYPE_PROMO;
    @SerializedName("END_DATE")
    @Expose
    private String END_DATE;
    @SerializedName("IS_PUBLISH")
    @Expose
    private String IS_PUBLISH;
    @SerializedName("CREATED_AT")
    @Expose
    private String CREATED_AT;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getTHUMB_GAMBAR() {
        return THUMB_GAMBAR;
    }

    public void setTHUMB_GAMBAR(String THUMB_GAMBAR) {
        this.THUMB_GAMBAR = THUMB_GAMBAR;
    }

    public String getFULL_GAMBAR() {
        return FULL_GAMBAR;
    }

    public void setFULL_GAMBAR(String FULL_GAMBAR) {
        this.FULL_GAMBAR = FULL_GAMBAR;
    }

    public String getTYPE_PROMO() {
        return TYPE_PROMO;
    }

    public void setTYPE_PROMO(String TYPE_PROMO) {
        this.TYPE_PROMO = TYPE_PROMO;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getIS_PUBLISH() {
        return IS_PUBLISH;
    }

    public void setIS_PUBLISH(String IS_PUBLISH) {
        this.IS_PUBLISH = IS_PUBLISH;
    }

    public String getCREATED_AT() {
        return CREATED_AT;
    }

    public void setCREATED_AT(String CREATED_AT) {
        this.CREATED_AT = CREATED_AT;
    }
}
