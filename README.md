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

##### gRPC-Web Hello World
Run `mvn clean install` to build and compile the project. This will generate all the necessary Protocol Buffer files for the backend.

#### Spring Boot Backend
Simply run the `HellowWorldApplication.java` as a normal Java application. This will start the server. See the `resources/application.properties` file for server details.

#### Envoy Proxy
To build and run the Docker container, follow the instructions below. 

For Windows:

    $ docker build -t helloworld/envoy -f ./envoy.Dockerfile .
    $ docker run -d -p 8080:8080 helloworld/envoy
    
For Unix:

    $ docker build -t helloworld/envoy -f ./envoy.Dockerfile .
    $ docker run -d -p 8080:8080 --network=host helloworld/envoy
    
**NOTE**: This project is configured for Windows by default. To run Envoy on Unix, change the following line in the `envoy.yaml` file 

    hosts: [{ socket_address: { address: host.docker.internal, port_value: 9090 }}]
to 

    hosts: [{ socket_address: { address: localhost, port_value: 9090 }}]

See [here](https://github.com/grpc/grpc-web/issues/436) for more details regarding this change.

#### Vue Frontend

// TODO: Generate the proto buffs for the frontend using

    $ protoc -I=. helloworld.proto \
      --js_out=import_style=commonjs:. \
      --grpc-web_out=import_style=commonjs,mode=grpcwebtext:.
