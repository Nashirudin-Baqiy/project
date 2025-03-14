package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 1/27/2020.
 */

public class Asuransi {
    @SerializedName("NAME")
    @Expose
    private String nAME;
    @SerializedName("CUSID")
    @Expose
    private String cUSID;

    public String getnAME() {
        return nAME;
    }

    public void setnAME(String nAME) {
        this.nAME = nAME;
    }

    public String getcUSID() {
        return cUSID;
    }

    public void setcUSID(String cUSID) {
        this.cUSID = cUSID;
    }
}
