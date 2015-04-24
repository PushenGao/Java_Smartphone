package ws.remote;

/**
 * Created by Michael-Gao on 2015/4/23.
 */
public interface UploadImage {
    public void uploadOrDeleteImage(String senderId, String receiverId, String path, String action, String timeStamp);
}
