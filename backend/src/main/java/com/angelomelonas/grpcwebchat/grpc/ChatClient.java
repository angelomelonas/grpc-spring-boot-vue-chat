package com.angelomelonas.grpcwebchat.grpc;

import com.angelomelonas.grpcwebchat.ChatGrpc;
import com.angelomelonas.grpcwebchat.ChatOuterClass.GetMessagesRequest;
import com.angelomelonas.grpcwebchat.ChatOuterClass.Message;
import com.angelomelonas.grpcwebchat.ChatOuterClass.MessageRequest;
import com.angelomelonas.grpcwebchat.ChatOuterClass.SendMessageResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;

@Component
public class ChatClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatClient.class);

    private ChatGrpc.ChatBlockingStub chatBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
        chatBlockingStub = ChatGrpc.newBlockingStub(managedChannel);
    }

    public String sendMessage(String username, String message) {
        MessageRequest newMessage = MessageRequest.newBuilder().setUsername(username).setMessage(message).build();

        SendMessageResponse response = chatBlockingStub.sendMessage(newMessage);

        // TODO:
        return "OK";
    }

    public ArrayList<String> getMessages() {
        GetMessagesRequest getMessagesRequest = GetMessagesRequest.newBuilder().build();

        Iterator<Message> messages = chatBlockingStub.getMessages(getMessagesRequest);

        ArrayList<String> messagesList = new ArrayList<>();

        messages.forEachRemaining(message -> messagesList.add(message.getMessage()));

        return messagesList;
    }

}
