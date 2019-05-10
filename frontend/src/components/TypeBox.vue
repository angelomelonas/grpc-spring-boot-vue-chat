<template>
    <v-container pa-2>

        <v-text-field
                label="Username"
                placeholder="Your username here..."
                outline
                prepend-icon="account_circle"
                v-model="username"
        ></v-text-field>

        <v-textarea
                outline
                no-resize
                single-line
                flat
                autofocus
                height="96"
                counter="512"
                maxlength="512"
                label="Type message here..."
                v-model="message"
        ></v-textarea>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="primary" @click="sendMessage()" :disabled="message.length < 1">Send</v-btn>
        </v-card-actions>
    </v-container>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from "vue-property-decorator";
    import * as grpcWeb from 'grpc-web';
    import {ChatClient} from "../../proto/chat_grpc_web_pb";
    import {MessageRequest} from "../../proto/chat_pb";
    import {Message} from "google-protobuf";

    @Component({
        name: "TypeBox"
    })
    export default class TypeBox extends Vue {
        @Prop({type: ChatClient, required: true})
        chatClient!: ChatClient;

        message: string = "";
        username: string = "";

        sendMessage() {
            const messageRequest = new MessageRequest();

            messageRequest.setUsername(this.username);
            messageRequest.setMessage(this.message);

            this.chatClient.sendMessage(messageRequest, {}, (err: grpcWeb.Error, message: Message) => {
                console.error(err);
            });

            // Reset the message.
            this.message = "";
        }
    }
</script>

<style></style>
