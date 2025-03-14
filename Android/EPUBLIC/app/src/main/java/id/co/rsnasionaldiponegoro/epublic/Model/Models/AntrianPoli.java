package id.co.rsnasionaldiponegoro.epublic.Model.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by xsanz on 11/12/2018.
 */

public class AntrianPoli {
    @SerializedName("no")
    @Expose
    private String no;
    @SerializedName("dokterN")
    @Expose
    private String dokterN;
    @SerializedName("jmAntrian")
    @Expose
    private String jmAntrian;
    @SerializedName("jmLayani")
    @Expose
    private String jmLayani;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDokterN() {
        return dokterN;
    }

    public void setDokterN(String dokterN) {
        this.dokterN = dokterN;
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
