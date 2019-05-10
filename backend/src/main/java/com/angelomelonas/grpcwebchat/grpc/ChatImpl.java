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
import java.util.HashMap;

@GRpcService
public class ChatImpl extends ChatGrpc.ChatImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatImpl.class);

    private Long idCounter = 0L;

    private HashMap<Long, StreamObserver<Message>> streamObserverMap = new HashMap<>();

    @Override
    public void sendMessage(MessageRequest request, StreamObserver<Message> responseObserver) {
        LOGGER.info("Received client message: {} from user: {}", request.getMessage(), request.getUsername());

        // Get the user and message details sent from the client.
        String receivedMessage = request.getMessage();
        String username = request.getUsername();
        Long timestamp = Instant.now().toEpochMilli();

        // Create a new message.
        Message message = Message.newBuilder().setMessage(receivedMessage).setUsername(username).setTimestamp(timestamp).build();

        // Respond to the client who sent the message.
        responseObserver.onNext(message);

        // Forward message to all clients.
        streamObserverMap.forEach((id, subscribedObserver) -> subscribedObserver.onNext(message));

        responseObserver.onCompleted();
    }

    @Override
    public void getMessages(MessagesRequest request, StreamObserver<Message> responseObserver) {
        LOGGER.info("Client requested messages {}", request);

        String username = "Server";
        Long timestamp = Instant.now().toEpochMilli();

        String welcomeMessage = "Welcome to gRPC Chat!";
        Message serverMessage = Message.newBuilder().setMessage(welcomeMessage).setUsername(username).setTimestamp(timestamp).build();

        responseObserver.onNext(serverMessage);

        // Store this client's StreamObserver.
        streamObserverMap.put(idCounter++, responseObserver);
    }
}
