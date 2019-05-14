<template>
  <v-container pa-2>
    <v-layout align-center justify-center row fill-height>
      <v-flex xs12>
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
          @keydown.enter.exact.prevent
          @keyup.enter.exact="
            sendMessage(message);
            clearMessage();
          "
          :disabled="!isSubscribed"
        ></v-textarea>
      </v-flex>

      <v-flex align-self-start>
        <v-spacer></v-spacer>
        <v-btn
          color="primary"
          @click="
            sendMessage(message);
            clearMessage();
          "
          :disabled="!isSubscribed || message.length < 1"
          large
          >Send
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import ChatParent from "@/components/ChatParent.ts";

@Component({
  name: "TypeBox"
})
export default class TypeBox extends ChatParent {
  private message: string = "";

  clearMessage(): void {
    this.message = "";
  }
}
</script>

<style></style>
