
package com.example.myapplication.apimodels.flyOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Carriers {

    @SerializedName("PR")
    @Expose
    private String pR;

    public String getPR() {
        return pR;
    }

    public void setPR(String pR) {
        this.pR = pR;
    }

}
