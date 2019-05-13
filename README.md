# gRPC-Web Chat
A very simple Hello World gRPC (and gRPC-Web) proof of concept project using Spring Boot, Vue.js and EnvoyProxy. 

## Requirements

##### Docker
Install Docker

##### Protoc
1. Go to [the releases page](https://github.com/protocolbuffers/protobuf/releases)  of [Protobuf](https://github.com/protocolbuffers/protobuf)
2. Select the latest release version
3. Scroll down to `Assets` and download the applicable file (e.g., for Windows `protoc-3.8.0-rc-1-win64.zip`)
4. Extract the contents and add to your path (e.g., for Windows, simply add the `protoc.exe` to your path)

##### gRPC-Web Protoc Plugin
1. Go to [the releases page](https://github.com/grpc/grpc-web/releases)  of [gRPC-Web](https://github.com/grpc/grpc-web)
2. Select the latest release version
3. Scroll down to `Assets` and download the applicable file (e.g., for Windows `protoc-gen-grpc-web-1.0.4-windows-x86_64.exe`)
4. Extract the `protoc-gen-grpc-web` file a directory and it to your path (e.g., for Windows add the `protoc-gen-grpc-web.exe` file to your path) 

## Install and Run

##### gRPC-Web Chat
Run `mvn clean install` to build and compile the project. This will also generate all the necessary Protocol Buffer files for the backend.

#### Spring Boot Backend
Simply run the `ChatApplication.java` as a normal Java application. This will start the server. See the `resources/application.properties` file for server details.

#### Envoy Proxy
To build and run the Docker container, follow the instructions below. 

For Windows:

    $ docker build -t helloworld/envoy -f ./envoy.Dockerfile .
    $ docker run -d -p 8080:8080 helloworld/envoy
    
For Unix:

    $ docker build -t helloworld/envoy -f ./envoy.Dockerfile .
    $ docker run -d -p 8080:8080 --network=host helloworld/envoy
    
#### Note
This project is configured for Windows by default. To run Envoy on Unix, change the following line in the `envoy.yaml` file 

    hosts: [{ socket_address: { address: host.docker.internal, port_value: 9090 }}]
to 

    hosts: [{ socket_address: { address: localhost, port_value: 9090 }}]

See [here](https://github.com/grpc/grpc-web/issues/436) for more details regarding this change.

#### Vue Frontend

// TODO: Generate the proto buffs for the frontend using

    $ protoc -I=. helloworld.proto \
      --js_out=import_style=commonjs:. \
      --grpc-web_out=import_style=commonjs,mode=grpcwebtext:.
// TODO
## References and Resources
    https://medium.com/@aravindhanjay/a-todo-app-using-grpc-web-and-vue-js-4e0c18461a3e
    https://www.youtube.com/watch?v=RtyKEDZipsM
    https://github.com/grpc/grpc-web/issues/347
    https://grpc.io/blog/grpc-web-ga/
    https://blog.envoyproxy.io/envoy-and-grpc-web-a-fresh-new-alternative-to-rest-6504ce7eb880
    https://piotrminkowski.wordpress.com/2017/10/25/envoy-proxy-with-microservices/
    https://codenotfound.com/grpc-java-example.html
    https://improbable.io/blog/grpc-web-moving-past-restjson-towards-type-safe-web-apis
    https://grpc.io/docs/quickstart/java/
    https://github.com/grpc/grpc-web/issues/436
    https://developers.google.com/protocol-buffers/docs/proto3
    https://github.com/rodaine/grpc-chat/blob/master/protos/chat.proto
    https://github.com/saturnism/grpc-java-by-example
    https://github.com/grpc/grpc-java/blob/master/examples/src/main/java/io/grpc/examples/routeguide/RouteGuideServer.java
    https://github.com/gluons/vuex-typescript-example
    https://medium.com/@koonradstraszewski/using-fully-typed-vuex-mutations-with-vuex-typescript-7597f56eceec
