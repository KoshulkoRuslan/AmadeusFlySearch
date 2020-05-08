
package com.example.myapplication.apimodels.flyOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Aircraft_ {

    @SerializedName("320")
    @Expose
    private String _320;
    @SerializedName("321")
    @Expose
    private String _321;
    @SerializedName("333")
    @Expose
    private String _333;

    public String get320() {
        return _320;
    }

    public void set320(String _320) {
        this._320 = _320;
    }

    public String get321() {
        return _321;
    }

    public void set321(String _321) {
        this._321 = _321;
    }

    public String get333() {
        return _333;
    }

    public void set333(String _333) {
        this._333 = _333;
    }

}
