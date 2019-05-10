package com.angelomelonas.grpcwebchat.grpc;

import com.angelomelonas.grpcwebchat.ChatGrpc;
import com.angelomelonas.grpcwebchat.ChatOuterClass.Message;
import com.angelomelonas.grpcwebchat.ChatOuterClass.MessageRequest;
import com.angelomelonas.grpcwebchat.ChatOuterClass.MessagesRequest;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

@GRpcService
public class ChatImpl extends ChatGrpc.ChatImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatImpl.class);
    private ConcurrentHashMap<Long, Message> messages = new ConcurrentHashMap<>();

    private Long idCounter = 0L;

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<Message> responseObserver) {
        LOGGER.info("Received client message: {} from user: {}", request.getMessage(), request.getUsername());

        // Get the user and message details sent from the client.
        String receivedMessage = request.getMessage();
        String username = request.getUsername();
        Long timestamp = Instant.now().toEpochMilli();

        // Create a new message.
        Message message = Message.newBuilder().setMessage(receivedMessage).setUsername(username).setTimestamp(timestamp).build();

        // Store the message on the server side.
        messages.put(idCounter++, message);

        // Respond with the newly created message.
        responseObserver.onNext(message);
        responseObserver.onCompleted();
    }

    @Override
    public void getMessages(MessagesRequest request, StreamObserver<Message> responseObserver) {
        LOGGER.info("Client requested messages {}", request);

        // Stream all messages to the client.
        messages.forEach((id, message) -> responseObserver.onNext(message));
        responseObserver.onCompleted();
    }
}
