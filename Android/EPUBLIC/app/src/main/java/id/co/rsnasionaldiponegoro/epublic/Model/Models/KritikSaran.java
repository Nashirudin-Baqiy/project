package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 9/27/2018.
 */

public class KritikSaran {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("judulKeluhan")
    @Expose
    private String judulKeluhan;
    @SerializedName("deskripsi")
    @Expose
    private String deskripsi;
    @SerializedName("tgl")
    @Expose
    private String tgl;
    @SerializedName("gambarKeluhan")
    @Expose
    private String gambarKeluhan;
    @SerializedName("jawabanKeluhan")
    @Expose
    private String jawabanKeluhan;
    @SerializedName("gambarJawabanKeluhan")
    @Expose
    private String gambarJawabanKeluhan;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJudulKeluhan() {
        return judulKeluhan;
    }

    public void setJudulKeluhan(String judulKeluhan) {
        this.judulKeluhan = judulKeluhan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getGambarKeluhan() {
        return gambarKeluhan;
    }

    public void setGambarKeluhan(String gambarKeluhan) {
        this.gambarKeluhan = gambarKeluhan;
    }

    public String getJawabanKeluhan() {
        return jawabanKeluhan;
    }

    public void setJawabanKeluhan(String jawabanKeluhan) {
        this.jawabanKeluhan = jawabanKeluhan;
    }

    public String getGambarJawabanKeluhan() {
        return gambarJawabanKeluhan;
    }

    public void setGambarJawabanKeluhan(String gambarJawabanKeluhan) {
        this.gambarJawabanKeluhan = gambarJawabanKeluhan;
    }
}
