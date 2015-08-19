package se.transport.transport.Utils;

/**
 * Created by brode on 2015-08-19.
 */
public class AvdMarkers {
    private String mAvdLabel;

    private String mAddress;
    private String mPostnr;
    private String mOpenHours;
    private String mAvdRegion;



    private Double mLatitude;
    private Double mLongitude;

    public AvdMarkers(String label, String address, String postnr, String openhours, String region, Double latitude, Double longitude) {
        this.mAvdLabel = label;
        this.mAvdRegion = region;

        this.mAddress = address;
        this.mPostnr = postnr;
        this.mOpenHours = openhours;
        this.mLatitude = latitude;
        this.mLongitude = longitude;

    }

    public String getAvdRegion() {
        return mAvdRegion;
    }

    public void setAvdRegion(String avdRegion) {
       this.mAvdRegion = avdRegion;
    }
    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        this.mAddress = address;
    }

    public String getPostnr() {
        return mPostnr;
    }

    public void setPostnr(String mPostnr) {
        this.mPostnr = mPostnr;
    }

    public String getOpenHours() {
        return mOpenHours;
    }

    public void setOpenHours(String mOpenHours) {
        this.mOpenHours = mOpenHours;
    }



    public String getAvdLabel() {
        return mAvdLabel;
    }

    public void setAvdLabel(String mAvdLabel) {
        this.mAvdLabel = mAvdLabel;
    }



    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }
}
