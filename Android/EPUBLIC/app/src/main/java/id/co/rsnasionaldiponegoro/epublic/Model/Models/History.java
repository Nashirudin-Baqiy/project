package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 9/25/2018.
 */

public class History {
    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("JUDUL")
    @Expose
    private String jUDUL;
    @SerializedName("DESKRIPSI")
    @Expose
    private String dESKRIPSI;
    @SerializedName("VISI")
    @Expose
    private String vISI;
    @SerializedName("MISI")
    @Expose
    private String mISI;
    @SerializedName("MOTO")
    @Expose
    private String mOTO;

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getjUDUL() {
        return jUDUL;
    }

    public void setjUDUL(String jUDUL) {
        this.jUDUL = jUDUL;
    }

    public String getdESKRIPSI() {
        return dESKRIPSI;
    }

    public void setdESKRIPSI(String dESKRIPSI) {
        this.dESKRIPSI = dESKRIPSI;
    }

    public String getvISI() {
        return vISI;
    }

    public void setvISI(String vISI) {
        this.vISI = vISI;
    }

    public String getmISI() {
        return mISI;
    }

    public void setmISI(String mISI) {
        this.mISI = mISI;
    }

    public String getmOTO() {
        return mOTO;
    }

    public void setmOTO(String mOTO) {
        this.mOTO = mOTO;
    }
}
