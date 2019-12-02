package vn.fpoly.fpolybookcarclient.server;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ParserPolyline {
    public static List<LatLng> getListLocation(String dataJSON) {
        List<LatLng> latLngs = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray routes = jsonObject.getJSONArray("routes");
            for (int i = 0; i < routes.length(); i++) {
                JSONObject jsonObjectAll = routes.getJSONObject(i);
                JSONObject overviewPolyline = jsonObjectAll.getJSONObject("overview_polyline");
                String point = overviewPolyline.getString("points");
                latLngs = PolyUtil.decode(point);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return latLngs;
    }

    public static Integer getDistance(String dataJson) {
        int distanceKM = 0;
        try {
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray routes = jsonObject.getJSONArray("routes");
            for (int i = 0; i < routes.length(); i++) {
                JSONObject jsonObjectAll = routes.getJSONObject(i);
                JSONArray legs = jsonObjectAll.getJSONArray("legs");
                for (int j = 0; j < legs.length(); j++) {
                    JSONObject legsObject = legs.getJSONObject(i);
                    JSONObject distance = legsObject.getJSONObject("distance");
                    distanceKM = distance.getInt("value");

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return distanceKM;
    }
    public static Integer getTime(String dataJson) {
        int distanceKM = 0;
        try {
            JSONObject jsonObject = new JSONObject(dataJson);
            JSONArray routes = jsonObject.getJSONArray("routes");
            for (int i = 0; i < routes.length(); i++) {
                JSONObject jsonObjectAll = routes.getJSONObject(i);
                JSONArray legs = jsonObjectAll.getJSONArray("legs");
                for (int j = 0; j < legs.length(); j++) {
                    JSONObject legsObject = legs.getJSONObject(i);
                    JSONObject distance = legsObject.getJSONObject("duration");
                    distanceKM = distance.getInt("value");

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return distanceKM;
    }


}


