package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 1/31/2020.
 */

public class Keluarga {
    @SerializedName("nocm")
    @Expose
    private String nocm;
    @SerializedName("idKeluarga")
    @Expose
    private String idKeluarga;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("idUserPrimer")
    @Expose
    private String idUserPrimer;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("namaKeluarga")
    @Expose
    private String namaKeluarga;

    @SerializedName("tgllhr")
    @Expose
    private String tgllhr;

    public String getTgllhr() {
        return tgllhr;
    }

    public void setTgllhr(String tgllhr) {
        this.tgllhr = tgllhr;
    }

    public String getNocm() {
        return nocm;
    }

    public void setNocm(String nocm) {
        this.nocm = nocm;
    }

    public String getIdKeluarga() {
        return idKeluarga;
    }

    public void setIdKeluarga(String idKeluarga) {
        this.idKeluarga = idKeluarga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdUserPrimer() {
        return idUserPrimer;
    }

    public void setIdUserPrimer(String idUserPrimer) {
        this.idUserPrimer = idUserPrimer;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNamaKeluarga() {
        return namaKeluarga;
    }

    public void setNamaKeluarga(String namaKeluarga) {
        this.namaKeluarga = namaKeluarga;
    }
}
