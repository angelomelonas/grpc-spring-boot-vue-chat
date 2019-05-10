<template>
    <v-app>
        <v-toolbar dark class="primary" app dense flat clipped-left>
            <v-toolbar-title> gRPC CHAT</v-toolbar-title>
            <v-toolbar-items class="hidden-xs-only"></v-toolbar-items>
        </v-toolbar>

        <v-content fluid>
            <v-container>
                <chat-box :chatClient="chatClient"></chat-box>
                <v-divider></v-divider>
                <type-box :chatClient="chatClient"></type-box>
            </v-container>
        </v-content>
    </v-app>
</template>

<script lang="ts">
    import {Component, Vue} from "vue-property-decorator";
    import ChatBox from "@/components/ChatBox.vue";
    import TypeBox from "@/components/TypeBox.vue";
    import {ChatClient} from "../proto/chat_grpc_web_pb";

    @Component({
        name: "App",
        components: {ChatBox, TypeBox},
    })
    export default class App extends Vue {
        chatClient!: ChatClient;

        created() {
            this.chatClient = new ChatClient("http://localhost:8080", null, null);
        }

    }
</script>

<style>
    /* Styles here will be globally applied. */
    html {
        overflow-y: hidden !important;
    }
</style>
