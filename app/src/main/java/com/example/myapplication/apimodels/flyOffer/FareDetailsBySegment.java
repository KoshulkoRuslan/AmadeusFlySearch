
package com.example.myapplication.apimodels.flyOffer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FareDetailsBySegment {

    @SerializedName("segmentId")
    @Expose
    private String segmentId;
    @SerializedName("cabin")
    @Expose
    private String cabin;
    @SerializedName("fareBasis")
    @Expose
    private String fareBasis;
    @SerializedName("class")
    @Expose
    private String _class;
    @SerializedName("includedCheckedBags")
    @Expose
    private IncludedCheckedBags includedCheckedBags;

    public String getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(String segmentId) {
        this.segmentId = segmentId;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

    public IncludedCheckedBags getIncludedCheckedBags() {
        return includedCheckedBags;
    }

    public void setIncludedCheckedBags(IncludedCheckedBags includedCheckedBags) {
        this.includedCheckedBags = includedCheckedBags;
    }

}
