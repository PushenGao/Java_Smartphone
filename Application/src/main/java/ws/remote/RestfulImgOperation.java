package ws.remote;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


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

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by Michael-Gao on 2015/4/23.
 */
public class RestfulImgOperation {
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/Jersey/rest/werun").build("");
    }

    public static void uploadOrDeleteImage(String senderId, String receiverId, String filePath, String action, String timeStamp){
//        ClientConfig config = new DefaultClientConfig();
//        Client client = Client.create(config);
//        WebResource resource = client.resource(getBaseURI()).path("upload/" + receiverId + "/" + action);
//        BodyPart part1=new BodyPart();
//        part1.setMediaType(MediaType.TEXT_PLAIN_TYPE);
//        part1.getHeaders().add("Content-SenderId","form-data; name=\"senderId\"");
//        part1.setEntity(senderId);
//        part1.getHeaders().add("Content-TimeStamp","form-data; name=\"timestamp\"");
//        part1.setEntity(timeStamp);
//
//        File fileToUpload = new File(filePath);
//        FormDataMultiPart multiPart = new FormDataMultiPart();
//        if (fileToUpload != null) {
//            multiPart.bodyPart(new FileDataBodyPart("file", fileToUpload,
//                    MediaType.APPLICATION_OCTET_STREAM_TYPE))
//                    .bodyPart(part1);
//        }
//
//        ClientResponse clientResp = resource.type(
//                MediaType.MULTIPART_FORM_DATA_TYPE).post(ClientResponse.class,
//                multiPart);
//        System.out.println("Response: " + clientResp.getClientResponseStatus());
//
//        client.destroy();
//
//    }
//
//    //the first string in the return list of string is the name of the picture, format imgName:TimeStamp.png
//    //the second string is the storing path of the img
//    //if there are no img in the server for this receiver, return null
//    public static List<String> getImgFromServer(String userId, String targetURL){
//        List<String> results = new ArrayList<String>();
//        try {
//
//            URL restServiceURL = new URL(targetURL);
//
//            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
//            httpConnection.setRequestMethod("GET");
//            //httpConnection.setRequestProperty("Accept", "application/json");
//
//            if (httpConnection.getResponseCode() != 200) {
//                System.out.println("No images");
//                return null;
//            }
//            String header = httpConnection.getHeaderField("imgFrom");
//            results.add(header);
//            //System.out.println(header);
//            InputStream is = httpConnection.getInputStream();
//
//            OutputStream os = null;
//            //TODO
//            // change the file path to the android sd card
//            String path = "/home/pushen/";
//            File fileDownloaded = new File(path + header);
//            results.add(path+header);
//            os = new FileOutputStream(fileDownloaded);
//            byte[] b = new byte[2048];
//            int length;
//            while ((length = is.read(b)) != -1) {
//                os.write(b, 0, length);
//            }
//
//            httpConnection.disconnect();
//        } catch (MalformedURLException e) {
//
//            e.printStackTrace();
//
//        } catch (IOException e) {
//
//            e.printStackTrace();
//
//        }
//        return results;

    }
}
