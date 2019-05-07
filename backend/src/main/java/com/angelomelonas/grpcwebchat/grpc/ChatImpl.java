package com.angelomelonas.grpcwebchat.grpc;

import com.angelomelonas.grpcwebchat.ChatGrpc;
import com.angelomelonas.grpcwebchat.ChatOuterClass.ClientMessage;
import com.angelomelonas.grpcwebchat.ChatOuterClass.MessagesRequest;
import com.angelomelonas.grpcwebchat.ChatOuterClass.SendMessageResponse;
import com.angelomelonas.grpcwebchat.ChatOuterClass.ServerMessage;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatImpl extends ChatGrpc.ChatImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatImpl.class);

    @Override
    public void sendMessage(ClientMessage request, StreamObserver<SendMessageResponse> responseObserver) {
        LOGGER.info("Received client message {}", request);
        // TODO AM: Implement.
    }

    @Override
    public void getMessages(MessagesRequest request, StreamObserver<ServerMessage> responseObserver) {
        LOGGER.info("Client requested messages {}", request);
        // TODO AM: Implement.
    }

}
