package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by xsanz on 9/26/2018.
 */

public class JadwalDokter implements Serializable {
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Poliklinik")
    @Expose
    private String poliklinik;
    @SerializedName("Dokter")
    @Expose
    private String dokter;
    @SerializedName("Hari")
    @Expose
    private String hari;
    @SerializedName("Jam")
    @Expose
    private String jam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoliklinik() {
        return poliklinik;
    }

    public void setPoliklinik(String poliklinik) {
        this.poliklinik = poliklinik;
    }

    public String getDokter() {
        return dokter;
    }

    public void setDokter(String dokter) {
        this.dokter = dokter;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }
}
