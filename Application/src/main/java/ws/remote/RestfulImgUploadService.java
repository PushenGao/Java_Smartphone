package ws.remote;

import android.os.AsyncTask;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.multipart.BodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;
import com.sun.jersey.multipart.file.FileDataBodyPart;

import java.io.File;
import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by JiateLi on 15/4/27.
 */
public class RestfulImgUploadService extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... params) {
//String senderId, String receiverId, String filePath, String action, String timeStamp
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        WebResource resource = client.resource(getBaseURI()).path("upload/" + params[1] + "/" + params[3]);

        BodyPart part1 = new BodyPart();
        part1.setMediaType(MediaType.TEXT_PLAIN_TYPE);
        part1.getHeaders().add("Content-SenderId","form-data; name=\"senderId\"");
        part1.setEntity(params[0]);
        part1.getHeaders().add("Content-TimeStamp","form-data; name=\"timestamp\"");
        part1.setEntity(params[4]);

        File fileToUpload = new File(params[2]);
        FormDataMultiPart multiPart = new FormDataMultiPart();
        if (fileToUpload != null) {
            multiPart.bodyPart(new FileDataBodyPart("file", fileToUpload,
                    MediaType.APPLICATION_OCTET_STREAM_TYPE))
                    .bodyPart(part1);
        }

        ClientResponse clientResp = resource.type(
                MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class,
                multiPart);
        System.out.println("Response: " + clientResp.getClientResponseStatus());

        client.destroy();


        return "success";
    }


    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/Jersey/rest/werun").build("");
    }

}
