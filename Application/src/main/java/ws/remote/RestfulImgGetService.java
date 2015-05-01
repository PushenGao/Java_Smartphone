package ws.remote;

import android.content.Context;
import android.content.ContextWrapper;
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

import ui.ChatWindow;

/**
 * Created by JiateLi on 15/4/27.
 */
public class RestfulImgGetService extends AsyncTask<String,Void,Object> {
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://10.0.22.230:8080/Jersey/rest/werun").build("");
    }

    @Override
    protected Object doInBackground(String... params) {
        //String userId, String targetURL
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
            //header = "myimg";
            results.add(header);
            //System.out.println(header);
            InputStream is = httpConnection.getInputStream();

            OutputStream os = null;
            //TODO
            // change the file path to the android sd card
            ContextWrapper cw = new ContextWrapper(ChatWindow.appContext);
            // path to /data/data/yourapp/app_data/imageDir
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            // Create imageDir
            File mypath=new File(directory,header);

            os = new FileOutputStream(mypath);
            byte[] b = new byte[2048];
            int length;
            int i = 0;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
                i = i + 2048;
            }
            System.out.println("fucker " + i);

            httpConnection.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return results;

    }
}
