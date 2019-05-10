<template>
    <v-container pa-2>
        <v-textarea
                outline
                hide-details
                clearable
                no-resize
                single-line
                flat
                height="480"
                name="input-7-4"
                readonly
                v-model=messages
        ></v-textarea>
    </v-container>
</template>

<script lang="ts">
    import {Component, Prop, Vue} from "vue-property-decorator";
    import {MessagesRequest} from "../../proto/chat_pb";
    import {ChatClient} from "../../proto/chat_grpc_web_pb";

    @Component({
        name: "ChatBox"
    })
    export default class TypeBox extends Vue {
        @Prop({type: ChatClient, required: true})
        chatClient!: ChatClient;

        messages: String = "";

        created() {
            const getMessagesRequest = new MessagesRequest();

            const client = this.chatClient.getMessages(getMessagesRequest, {});

            client.on("data", data => {
                this.messages += (data.getUsername() + ": " + data.getMessage() + "\n");
            });
        }
    }
</script>

<style></style>
