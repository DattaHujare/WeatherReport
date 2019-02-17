package com.weather.metoffice.utils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeocodeResponse {
    @SerializedName("results")
    @Expose
    public List<Results> results = null;
    @SerializedName("status")
    @Expose
    public String status;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "GeocodeResponse{" +
                "results=" + results +
                ", status='" + status + '\'' +
                '}';
    }

    public class Results {

        @SerializedName("address_components")
        @Expose
        public List<AddressComponent> addressComponents = null;
        @SerializedName("formatted_address")
        @Expose
        public String formattedAddress;
        @SerializedName("geometry")
        @Expose
        public Geometry geometry;
        @SerializedName("place_id")
        @Expose
        public String placeId;
        @SerializedName("types")
        @Expose
        public List<String> types = null;

        public List<AddressComponent> getAddressComponents() {
            return addressComponents;
        }

        public void setAddressComponents(List<AddressComponent> addressComponents) {
            this.addressComponents = addressComponents;
        }

        public String getFormattedAddress() {
            return formattedAddress;
        }

        public void setFormattedAddress(String formattedAddress) {
            this.formattedAddress = formattedAddress;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        @Override
        public String toString() {
            return "Results{" +
                    "addressComponents=" + addressComponents +
                    ", formattedAddress='" + formattedAddress + '\'' +
                    ", geometry=" + geometry +
                    ", placeId='" + placeId + '\'' +
                    ", types=" + types +
                    '}';
        }
    }

    public class AddressComponent {
        @SerializedName("long_name")
        @Expose
        public String longName;
        @SerializedName("short_name")
        @Expose
        public String shortName;
        @SerializedName("types")
        @Expose
        public List<String> types = null;

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        @Override
        public String toString() {
            return "AddressComponent{" +
                    "longName='" + longName + '\'' +
                    ", shortName='" + shortName + '\'' +
                    ", types=" + types +
                    '}';
        }
    }

    public class Geometry {

        @SerializedName("location")
        @Expose
        public Locations location;
        @SerializedName("location_type")
        @Expose
        public String locationType;

        public Locations getLocation() {
            return location;
        }

        public void setLocation(Locations location) {
            this.location = location;
        }

        public String getLocationType() {
            return locationType;
        }

        public void setLocationType(String locationType) {
            this.locationType = locationType;
        }

        @Override
        public String toString() {
            return "Geometry{" +
                    "location=" + location +
                    ", locationType='" + locationType + '\'' +
                    '}';
        }
    }

    public class Locations {

        @SerializedName("lat")
        @Expose
        public Double lat;
        @SerializedName("lng")
        @Expose
        public Double lng;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        @Override
        public String toString() {
            return "Locations{" +
                    "lat=" + lat +
                    ", lng=" + lng +
                    '}';
        }
    }
}
