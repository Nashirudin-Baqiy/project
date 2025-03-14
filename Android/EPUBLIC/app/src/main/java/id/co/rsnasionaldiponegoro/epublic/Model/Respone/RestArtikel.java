package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Artikel;

/**
 * Created by xsanz on 9/26/2018.
 */

public class RestArtikel {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private ArrayList<Artikel> data = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ArrayList<Artikel> getData() {
        return data;
    }

    public void setData(ArrayList<Artikel> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
