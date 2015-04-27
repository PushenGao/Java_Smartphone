package ws.remote;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriBuilder;

/**
 * Created by JiateLi on 15/4/27.
 */
public class RestfulImgGetService extends AsyncTask<String,Void,Object> {
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/Jersey/rest/werun").build("");
    }

    @Override
    protected Object doInBackground(String... params) {
        List<String> results = new ArrayList<String>();
        try {

            URL restServiceURL = new URL(params[1]);

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            //httpConnection.setRequestProperty("Accept", "application/json");

            if (httpConnection.getResponseCode() != 200) {
                System.out.println("No images");
                return null;
            }
            String header = httpConnection.getHeaderField("imgFrom");
            results.add(header);
            //System.out.println(header);
            InputStream is = httpConnection.getInputStream();

            OutputStream os = null;
            //TODO
            // change the file path to the android sd card
            String path = "/home/" + params[0] + "/";
            File fileDownloaded = new File(path + header);
            results.add(path+header);
            os = new FileOutputStream(fileDownloaded);
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            httpConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return results;

    }
}
