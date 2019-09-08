package vn.mrlongg71.demogooglemap;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Binh on 5/17/17.
 */

public class DownloadPolyLine extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... params) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);


            String line = "";

            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    @Override
    protected void onPostExecute(String dataJSON) {
        super.onPostExecute(dataJSON);
        Log.d("xxx", dataJSON);
    }


}
