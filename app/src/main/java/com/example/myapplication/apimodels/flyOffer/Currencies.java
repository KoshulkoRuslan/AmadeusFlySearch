
package com.example.myapplication.apimodels.flyOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currencies {

    @SerializedName("EUR")
    @Expose
    private String eUR;

    public String getEUR() {
        return eUR;
    }

    public void setEUR(String eUR) {
        this.eUR = eUR;
    }

}
