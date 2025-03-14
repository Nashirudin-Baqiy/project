package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 11/25/2018.
 */

public class AntrianApotik {
    @SerializedName("loket")
    @Expose
    private String loket;
    @SerializedName("antrian")
    @Expose
    private String antrian;
    @SerializedName("jmAntrian")
    @Expose
    private String jmAntrian;
    @SerializedName("jmLayani")
    @Expose
    private String jmLayani;

    public String getLoket() {
        return loket;
    }

    public void setLoket(String loket) {
        this.loket = loket;
    }

    public String getAntrian() {
        return antrian;
    }

    public void setAntrian(String antrian) {
        this.antrian = antrian;
    }

    public String getJmAntrian() {
        return jmAntrian;
    }

    public void setJmAntrian(String jmAntrian) {
        this.jmAntrian = jmAntrian;
    }

    public String getJmLayani() {
        return jmLayani;
    }

    public void setJmLayani(String jmLayani) {
        this.jmLayani = jmLayani;
    }
}
