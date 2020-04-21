package vn.fpoly.fpolybookcarclient.library;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocoderAddress {
    public static String getAddress(Activity activity,double locationLatitude,double locationLongitude){
       Geocoder geocoder  = new Geocoder(activity, Locale.getDefault());
        String addresses = "";
            try {

                List<Address>  arrAddress = geocoder.getFromLocation(locationLatitude, locationLongitude, 1);
                addresses = arrAddress.get(0).getAddressLine(0);
                String address[] = addresses.split(",");
                addresses = address[0] + " Quận " + address[2];
            } catch (IOException e) {
                e.printStackTrace();
                addresses = "";
            }

            return  addresses;
        }

}
