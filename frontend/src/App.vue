<template>
  <v-app>
    <v-toolbar dark class="primary" app dense flat clipped-left>
      <v-toolbar-title> gRPC CHAT</v-toolbar-title>
      <v-toolbar-items class="hidden-xs-only"> </v-toolbar-items>
    </v-toolbar>

    <v-content fluid>
      <v-container>
        <chat-box></chat-box>
        <v-divider></v-divider>
        <type-box></type-box>
      </v-container>
    </v-content>
  </v-app>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import ChatBox from "@/components/ChatBox.vue";
import TypeBox from "@/components/TypeBox.vue";

@Component({
  name: "App",
  components: { ChatBox, TypeBox }
})
export default class App extends Vue {}
</script>

<style>
/* Styles here will be globally applied. */
html {
  overflow-y: hidden !important;
}
</style>
