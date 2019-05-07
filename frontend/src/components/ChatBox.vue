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
      value="User 1: Hello"
    ></v-textarea>
  </v-container>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component({
  name: "ChatBox"
})
export default class TypeBox extends Vue {}
</script>

<style></style>
