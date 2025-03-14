package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 06-Oct-2020.
 */

public class SuaraKonsumen {

    @SerializedName("JUDUL")
    @Expose
    private String jUDUL;
    @SerializedName("URL")
    @Expose
    private String uRL;

    public String getjUDUL() {
        return jUDUL;
    }

    public void setjUDUL(String jUDUL) {
        this.jUDUL = jUDUL;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }
}
