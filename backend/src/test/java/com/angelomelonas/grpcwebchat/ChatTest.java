package com.angelomelonas.grpcwebchat;

import com.angelomelonas.grpcwebchat.ChatOuterClass.MessageRequest;
import com.angelomelonas.grpcwebchat.grpc.ChatClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatTest {

    @Autowired
    private ChatClient chatClient;

    @Test
    public void testSendMessage() {
        assertThat(chatClient.sendMessage("John", "Hello, world!")).isEqualTo("OK");
    }

    @Test
    public void getMessages() {
        ArrayList<String> messages = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            messages.add(generateRandomMessageRequest().getMessage());
            chatClient.sendMessage(generateRandomMessageRequest().getUsername(), generateRandomMessageRequest().getMessage());
        }

        assertThat(chatClient.getMessages().containsAll(messages));
    }

    private MessageRequest generateRandomMessageRequest() {
        String username = UUID.randomUUID().toString();
        String message = UUID.randomUUID().toString();
        return MessageRequest.newBuilder().setUsername(username).setMessage(message).build();
    }

}
