package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 9/25/2018.
 */

public class Headline {

    @SerializedName("ID")
    @Expose
    private String ID;
    @SerializedName("ISI")
    @Expose
    private String ISI;
    @SerializedName("END_DATE")
    @Expose
    private String ENDDATE;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getISI() {
        return ISI;
    }

    public void setISI(String ISI) {
        this.ISI = ISI;
    }

    public String getENDDATE() {
        return ENDDATE;
    }

    public void setENDDATE(String ENDDATE) {
        this.ENDDATE = ENDDATE;
    }
}
