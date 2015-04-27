package ws.remote;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JiateLi on 15/4/25.
 */
public class RestfulGETService extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
        if (params.length < 0)
            return null;
        String output = "";
        String rst = "";

        try {
            URL restServiceURL = new URL(params[0]);

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));


            System.out.println("Output from Server:  \n");

            while ((output = responseBuffer.readLine()) != null) {
                System.out.println(output);
                rst = output;
            }

            httpConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return rst;

    }

}

