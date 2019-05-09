package com.angelomelonas.grpcwebchat;

import com.angelomelonas.grpcwebchat.ChatOuterClass.Message;
import com.angelomelonas.grpcwebchat.ChatOuterClass.MessageRequest;
import com.angelomelonas.grpcwebchat.ChatOuterClass.MessagesRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;

// This class is only for testing purposes.
@Component
public class ChatClient {


    private ChatGrpc.ChatBlockingStub chatBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
        chatBlockingStub = ChatGrpc.newBlockingStub(managedChannel);
    }

    public Message sendMessage(String username, String message) {
        MessageRequest newMessage = MessageRequest.newBuilder().setUsername(username).setMessage(message).build();
        Message response = chatBlockingStub.sendMessage(newMessage);

        return response;
    }

    public ArrayList<String> getMessages() {
        ArrayList<String> messagesList = new ArrayList<>();

        Iterator<Message> messages = chatBlockingStub.getMessages(MessagesRequest.newBuilder().build());
        messages.forEachRemaining(message -> messagesList.add(message.getMessage()));

        return messagesList;
    }
}
