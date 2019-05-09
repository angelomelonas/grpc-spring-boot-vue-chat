package com.angelomelonas.grpcwebchat.grpc;

import com.angelomelonas.grpcwebchat.ChatGrpc;
import com.angelomelonas.grpcwebchat.ChatOuterClass.Message;
import com.angelomelonas.grpcwebchat.ChatOuterClass.SendMessageResponse;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@GRpcService
public class ChatImpl extends ChatGrpc.ChatImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatImpl.class);
    private Map<String, Message> messages;

    public ChatImpl() {
        messages = new HashMap<String, Message>();
    }

    @Override
    public void sendMessage(com.angelomelonas.grpcwebchat.ChatOuterClass.MessageRequest request, io.grpc.stub.StreamObserver<com.angelomelonas.grpcwebchat.ChatOuterClass.SendMessageResponse> responseObserver) {
        LOGGER.info("Received client message {}", request);

        String receivedMessage = request.getMessage();
        String username = request.getUsername();
        Long timestamp = Instant.now().toEpochMilli();

        Message message = Message.newBuilder().setMessage(receivedMessage).setUsername(username).setTimestamp(timestamp).build();

        String messageId = UUID.randomUUID().toString();

        messages.put(messageId, message);

        // TODO: Reply that the message was sent successfully.
        responseObserver.onNext(SendMessageResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getMessages(com.angelomelonas.grpcwebchat.ChatOuterClass.GetMessagesRequest request, io.grpc.stub.StreamObserver<com.angelomelonas.grpcwebchat.ChatOuterClass.Message> responseObserver) {
        LOGGER.info("Client requested messages {}", request);

        messages.forEach((id, message) -> responseObserver.onNext(message));
        responseObserver.onCompleted();
    }

}
