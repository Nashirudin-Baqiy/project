package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 9/26/2018.
 */

public class Artikel {
    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("ID_KATEGORI")
    @Expose
    private String IDKATEGORI;
    @SerializedName("PARENT")
    @Expose
    private String PARENT;
    @SerializedName("JUDUL")
    @Expose
    private String JUDUL;
    @SerializedName("DESKRIPSI")
    @Expose
    private String DESKRIPSI;
    @SerializedName("GAMBAR")
    @Expose
    private String GAMBAR;
    @SerializedName("STATUS")
    @Expose
    private String STATUS;
    @SerializedName("ID_USER")
    @Expose
    private String IDUSER;
    @SerializedName("UPDATED_AT")
    @Expose
    private String UPDATED_AT;
    @SerializedName("ISPARENT")
    @Expose
    private Boolean ISPARENT;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDKATEGORI() {
        return IDKATEGORI;
    }

    public void setIDKATEGORI(String IDKATEGORI) {
        this.IDKATEGORI = IDKATEGORI;
    }

    public String getPARENT() {
        return PARENT;
    }

    public void setPARENT(String PARENT) {
        this.PARENT = PARENT;
    }

    public String getJUDUL() {
        return JUDUL;
    }

    public void setJUDUL(String JUDUL) {
        this.JUDUL = JUDUL;
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

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getIDUSER() {
        return IDUSER;
    }

    public void setIDUSER(String IDUSER) {
        this.IDUSER = IDUSER;
    }

    public Boolean getISPARENT() {
        return ISPARENT;
    }

    public void setISPARENT(Boolean ISPARENT) {
        this.ISPARENT = ISPARENT;
    }

    public String getUPDATED_AT() {
        return UPDATED_AT;
    }

    public void setUPDATED_AT(String UPDATED_AT) {
        this.UPDATED_AT = UPDATED_AT;
    }
}
