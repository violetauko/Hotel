
package com.ellah.ellahveehotels.models;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HotelSearchResponse {

    @SerializedName("businesses")
    @Expose
    private List<Business> businesses = null;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("region")
    @Expose
    private Region region;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HotelSearchResponse() {
    }

    /**
     * 
     * @param total
     * @param region
     * @param businesses
     */
    public HotelSearchResponse(List<Business> businesses, Integer total, Region region) {
        super();
        this.businesses = businesses;
        this.total = total;
        this.region = region;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
