package com.weather.metoffice.utils;

import android.location.Address;
import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AllClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MyGeocoder {

    private static final String TAG = "MyGeocoder";
    private static String admin_level2 = "";
    private static String admin_level1 = "";

    public static List<Address> getFromLocation(double lat, double lng, int maxResult) {
        admin_level1 = "";
        admin_level2 = "";
        String address = String.format(Locale.ENGLISH, "http://maps.googleapis.com/maps/api/geocode/json?latlng=%1$f,%2$f&sensor=false&language=en", lat, lng);
        HttpGet httpGet = new HttpGet(address);
        HttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(AllClientPNames.USER_AGENT, "Mozilla/5.0 (Java) Gecko/20081007 java-geocoder");
        client.getParams().setIntParameter(AllClientPNames.CONNECTION_TIMEOUT, 5 * 1000);
        client.getParams().setIntParameter(AllClientPNames.SO_TIMEOUT, 25 * 1000);
        HttpResponse response;

        List<Address> retList = null;

        try {
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity, "UTF-8");

            GeocodeResponse geocodeResponse = new Gson().fromJson(json, GeocodeResponse.class);
            JSONObject jsonObject = new JSONObject(json);
            retList = new ArrayList<Address>();
            if ("OK".equalsIgnoreCase(jsonObject.getString("status"))) {
                List<GeocodeResponse.Results> results = geocodeResponse.getResults();
                if (results.size() > 0) {
                    Address addr = new Address(Locale.ENGLISH);
                    for (GeocodeResponse.AddressComponent a :
                            results.get(1).getAddressComponents()) {
                        String type = a.getTypes().toArray()[0].toString();

                        if (type.equalsIgnoreCase("country")) {
                            addr.setCountryName(a.getLongName());
                        }
                        if (type.equalsIgnoreCase("administrative_area_level_2")) {
                            addr.setAddressLine(0, a.getLongName());
                            admin_level2 = a.getLongName();
                            //addr.setAdminArea(a.getLongName());
                        }
                        if (!type.equalsIgnoreCase("administrative_area_level_2")) {
                            if (type.equalsIgnoreCase("administrative_area_level_1")) {
                                addr.setAddressLine(0, a.getLongName());
                                admin_level1 = a.getLongName();
                                //  addr.setAdminArea(a.getLongName());
                            }
                        }

                        if (type.equalsIgnoreCase("postal_code")) {
                            addr.setPostalCode(a.getLongName());
                        }
                    }

                    if (admin_level2.equalsIgnoreCase("")) {
                        addr.setAdminArea(admin_level1);
                    } else {
                        addr.setAdminArea(admin_level2);
                    }
                    for (GeocodeResponse.AddressComponent a :
                            results.get(1).getAddressComponents()) {
                        String type = a.getTypes().toArray()[0].toString();

                        if (type.equalsIgnoreCase("locality")) {
                            addr.setLocality(a.getLongName());
                            break;
                        }
                    }

                    addr.setLatitude(results.get(0).getGeometry().getLocation().getLat());
                    addr.setLongitude(results.get(0).getGeometry().getLocation().getLng());
                    retList.add(addr);
                }
            }
        } catch (ClientProtocolException e) {
            Log.e(MyGeocoder.class.getName(), "Error calling Google geocode webservice.", e);
        } catch (IOException e) {
            Log.e(MyGeocoder.class.getName(), "Error calling Google geocode webservice.", e);
        } catch (JSONException e) {
            Log.e(MyGeocoder.class.getName(), "Error parsing Google geocode webservice response.", e);
        }

        return retList;
    }
}

