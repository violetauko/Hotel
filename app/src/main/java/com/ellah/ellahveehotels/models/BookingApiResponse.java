
package com.ellah.ellahveehotels.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BookingApiResponse {

    @SerializedName("hotels")
    @Expose
    private Integer hotels;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("dest_type")
    @Expose
    private String destType;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("city_ufi")
    @Expose
    private Integer cityUfi;
    @SerializedName("lc")
    @Expose
    private String lc;
    @SerializedName("dest_id")
    @Expose
    private String destId;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("landmark_type")
    @Expose
    private Integer landmarkType;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("cc1")
    @Expose
    private String cc1;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("rtl")
    @Expose
    private Integer rtl;
    @SerializedName("nr_hotels")
    @Expose
    private Integer nrHotels;
    @SerializedName("b_max_los_data")
    @Expose
    private BMaxLosData bMaxLosData;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BookingApiResponse() {
    }

    /**
     * 
     * @param country
     * @param timezone
     * @param landmarkType
     * @param latitude
     * @param label
     * @param rtl
     * @param type
     * @param destType
     * @param bMaxLosData
     * @param cc1
     * @param cityName
     * @param destId
     * @param nrHotels
     * @param hotels
     * @param imageUrl
     * @param lc
     * @param name
     * @param region
     * @param cityUfi
     * @param longitude
     */
    public BookingApiResponse(Integer hotels, String region, String destType, String timezone, String country, String name, String cityName, String imageUrl, Integer cityUfi, String lc, String destId, Double latitude, String label, Integer landmarkType, Double longitude, String cc1, String type, Integer rtl, Integer nrHotels, BMaxLosData bMaxLosData) {
        super();
        this.hotels = hotels;
        this.region = region;
        this.destType = destType;
        this.timezone = timezone;
        this.country = country;
        this.name = name;
        this.cityName = cityName;
        this.imageUrl = imageUrl;
        this.cityUfi = cityUfi;
        this.lc = lc;
        this.destId = destId;
        this.latitude = latitude;
        this.label = label;
        this.landmarkType = landmarkType;
        this.longitude = longitude;
        this.cc1 = cc1;
        this.type = type;
        this.rtl = rtl;
        this.nrHotels = nrHotels;
        this.bMaxLosData = bMaxLosData;
    }

    public Integer getHotels() {
        return hotels;
    }

    public void setHotels(Integer hotels) {
        this.hotels = hotels;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDestType() {
        return destType;
    }

    public void setDestType(String destType) {
        this.destType = destType;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCityUfi() {
        return cityUfi;
    }

    public void setCityUfi(Integer cityUfi) {
        this.cityUfi = cityUfi;
    }

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getLandmarkType() {
        return landmarkType;
    }

    public void setLandmarkType(Integer landmarkType) {
        this.landmarkType = landmarkType;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCc1() {
        return cc1;
    }

    public void setCc1(String cc1) {
        this.cc1 = cc1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRtl() {
        return rtl;
    }

    public void setRtl(Integer rtl) {
        this.rtl = rtl;
    }

    public Integer getNrHotels() {
        return nrHotels;
    }

    public void setNrHotels(Integer nrHotels) {
        this.nrHotels = nrHotels;
    }

    public BMaxLosData getbMaxLosData() {
        return bMaxLosData;
    }

    public void setbMaxLosData(BMaxLosData bMaxLosData) {
        this.bMaxLosData = bMaxLosData;
    }

}
