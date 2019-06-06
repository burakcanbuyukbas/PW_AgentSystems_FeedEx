package Models;

public class LatLng {
    public final double lat;
    public final double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public LatLng(String lat, String lng) {
        this.lat = Double.parseDouble(lat);
        this.lng = Double.parseDouble(lng);
    }

}