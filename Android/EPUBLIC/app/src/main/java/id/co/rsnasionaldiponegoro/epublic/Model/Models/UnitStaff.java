package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 10/13/2018.
 */

public class UnitStaff {
    @SerializedName("unitStaff")
    @Expose
    private String unitStaff;
    @SerializedName("idStaff")
    @Expose
    private String idStaff;

    public String getUnitStaff() {
        return unitStaff;
    }

    public void setUnitStaff(String unitStaff) {
        this.unitStaff = unitStaff;
    }

    public String getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(String idStaff) {
        this.idStaff = idStaff;
    }
}
