
package com.ellah.ellahveehotels.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BMaxLosData {

    @SerializedName("has_extended_los")
    @Expose
    private Integer hasExtendedLos;
    @SerializedName("max_allowed_los")
    @Expose
    private Integer maxAllowedLos;
    @SerializedName("is_fullon")
    @Expose
    private Integer isFullon;
    @SerializedName("experiment")
    @Expose
    private String experiment;
    @SerializedName("extended_los")
    @Expose
    private Integer extendedLos;
    @SerializedName("default_los")
    @Expose
    private Integer defaultLos;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BMaxLosData() {
    }

    /**
     * 
     * @param experiment
     * @param isFullon
     * @param extendedLos
     * @param hasExtendedLos
     * @param defaultLos
     * @param maxAllowedLos
     */
    public BMaxLosData(Integer hasExtendedLos, Integer maxAllowedLos, Integer isFullon, String experiment, Integer extendedLos, Integer defaultLos) {
        super();
        this.hasExtendedLos = hasExtendedLos;
        this.maxAllowedLos = maxAllowedLos;
        this.isFullon = isFullon;
        this.experiment = experiment;
        this.extendedLos = extendedLos;
        this.defaultLos = defaultLos;
    }

    public Integer getHasExtendedLos() {
        return hasExtendedLos;
    }

    public void setHasExtendedLos(Integer hasExtendedLos) {
        this.hasExtendedLos = hasExtendedLos;
    }

    public Integer getMaxAllowedLos() {
        return maxAllowedLos;
    }

    public void setMaxAllowedLos(Integer maxAllowedLos) {
        this.maxAllowedLos = maxAllowedLos;
    }

    public Integer getIsFullon() {
        return isFullon;
    }

    public void setIsFullon(Integer isFullon) {
        this.isFullon = isFullon;
    }

    public String getExperiment() {
        return experiment;
    }

    public void setExperiment(String experiment) {
        this.experiment = experiment;
    }

    public Integer getExtendedLos() {
        return extendedLos;
    }

    public void setExtendedLos(Integer extendedLos) {
        this.extendedLos = extendedLos;
    }

    public Integer getDefaultLos() {
        return defaultLos;
    }

    public void setDefaultLos(Integer defaultLos) {
        this.defaultLos = defaultLos;
    }

}
