package com.angelomelonas.helloworld.grpc;

import com.angelomelonas.helloworld.GreeterGrpc;
import com.angelomelonas.helloworld.Helloworld.HelloReply;
import com.angelomelonas.helloworld.Helloworld.HelloRequest;
import com.angelomelonas.helloworld.Helloworld.RepeatHelloRequest;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreeterImpl.class);

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        LOGGER.info("server received {}", request);
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        LOGGER.info("server responded {}", reply);
    }

    @Override
    public void sayRepeatHello(RepeatHelloRequest request, StreamObserver<HelloReply> responseObserver) {
        LOGGER.info("server received {}", request);
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello again " + request.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        LOGGER.info("server responded {}", reply);
    }
}
