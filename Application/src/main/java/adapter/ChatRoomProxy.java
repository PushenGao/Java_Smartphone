package adapter;

import java.util.LinkedHashMap;

import model.ChatRoom;

/**
 * Created by JiateLi on 15/4/11.
 */
public abstract class ChatRoomProxy {
    private LinkedHashMap<Integer, ChatRoom> chatroomMap = new LinkedHashMap<Integer, ChatRoom>();

    public void addChatRoom(ChatRoom chatRoom){

    };

    public void deleteChatRoom(int chatRoomID){

    };

    public void updateChatRoom(ChatRoom chatRoom){

    };
}
