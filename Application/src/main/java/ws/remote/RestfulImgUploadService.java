package ws.remote;

import android.os.AsyncTask;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by JiateLi on 15/4/27.
 */
public class RestfulImgUploadService extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
        DefaultClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        WebResource resource = client.resource(getBaseURI()).path("uploadStream/" + params[0] + "/" + params[1] + "/" + params[3]);
        InputStream fileInStream = null;
        try {
            fileInStream = new FileInputStream(params[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        ClientResponse response = resource.type(MediaType.APPLICATION_OCTET_STREAM)
                .post(ClientResponse.class, fileInStream);
        return "Response: " + response.getClientResponseStatus();
    }


    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://10.0.22.230:8080/Jersey/rest/werun").build("");
    }

}
