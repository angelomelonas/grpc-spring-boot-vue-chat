<template>
  <v-container pa-2>
    <v-textarea
      outline
      clearable
      no-resize
      single-line
      flat
      autofocus
      height="96"
      counter="512"
      maxlength="512"
      label="Type message here..."
    ></v-textarea>

    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="primary">Send</v-btn>
    </v-card-actions>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component({
  name: "TypeBox"
})
export default class TypeBox extends Vue {}
</script>

<style></style>
