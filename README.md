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
Execute `mvn clean install` to build and compile the project. This will also generate all the necessary Protocol Buffer files for the backend and frontend.

#### Spring Boot Backend
Simply run the `ChatApplication.java` as a normal Java application. This will start the server. See the `resources/application.properties` file for server details. By default it runs on http://localhost:8000.

#### Envoy Proxy
To build and run the Docker container, follow the instructions below. 

###### Windows
Execute the following Docker commands to build and run the EnvoyProxy container:
    
    $ docker build -t helloworld/envoy -f ./envoy.Dockerfile .
    $ docker run -d -p 8080:8080 helloworld/envoy
    
###### Unix
Note: This project is configured for Windows by default. To run Envoy on Unix, change the following line in the `envoy.yaml` file (see [here](https://github.com/grpc/grpc-web/issues/436) for more details):

    hosts: [{ socket_address: { address: host.docker.internal, port_value: 9090 }}]
to 

    hosts: [{ socket_address: { address: localhost, port_value: 9090 }}]

Now execute the following Docker commands to build and run the EnvoyProxy container:

    $ docker build -t helloworld/envoy -f ./envoy.Dockerfile .
    $ docker run -d -p 8080:8080 --network=host helloworld/envoy
    
#### Vue Frontend
To specifically generate Proto files for the front-end client, navigate to the `frontend` directory and run the following command:
    
    $ npm run proto
    
To serve the frontend code in a development environment, execute:

    $ npm run serve -- --port 8081    # This serves the front end on port 8081.

## References and Resources
* [gRPC-Web is Generally Available](https://grpc.io/blog/grpc-web-ga/)
* [CodeNotFound: gRPC Java Example](https://codenotfound.com/grpc-java-example.html)
* [gRPC-Web: Moving past REST+JSON towards type-safe Web APIs](https://improbable.io/blog/grpc-web-moving-past-restjson-towards-type-safe-web-apis)
* [gRPC: Java Quick Start](https://grpc.io/docs/quickstart/java/)
* [Language Guide (proto3)](https://developers.google.com/protocol-buffers/docs/proto3)
