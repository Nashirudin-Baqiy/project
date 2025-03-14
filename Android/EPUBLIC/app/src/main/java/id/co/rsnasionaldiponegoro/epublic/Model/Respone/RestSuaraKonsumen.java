package id.co.rsnasionaldiponegoro.epublic.Model.Respone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.SuaraKonsumen;

/**
 * Created by xsanz on 06-Oct-2020.
 */

public class RestSuaraKonsumen {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<SuaraKonsumen> data = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<SuaraKonsumen> getData() {
        return data;
    }

    public void setData(List<SuaraKonsumen> data) {
        this.data = data;
    }
}
