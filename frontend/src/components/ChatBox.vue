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
                v-model="messages"
        ></v-textarea>
    </v-container>
</template>

<script lang="ts">
    import {Component} from "vue-property-decorator";
    import ChatParent from "@/components/ChatParent";

    @Component({
        name: "ChatBox"
    })
    export default class TypeBox extends ChatParent {
    }
</script>

<style></style>
