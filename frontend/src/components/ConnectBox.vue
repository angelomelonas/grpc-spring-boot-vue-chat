<template>
  <v-container pa-2 pb-1>
    <v-layout align-center justify-center row fill-height>
      <v-flex xs12>
        <v-text-field
          label="Username"
          placeholder="Your username here..."
          outline
          maxlength="64"
          minlength="4"
          hide-details
          autofocus
          prepend-icon="account_circle"
          :value="username"
          @input="setUsername"
          :disabled="isSubscribed"
        ></v-text-field>
      </v-flex>

      <v-flex>
        <v-btn
          v-if="!isSubscribed"
          color="success"
          @click="subscribe()"
          :disabled="username.length < 4"
          large
          >Subscribe
        </v-btn>

        <v-btn
          v-else
          color="error"
          @click="unsubscribe()"
          :disabled="username.length < 4 || username.length > 64"
          large
          >Unsubscribe
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import ChatParent from "@/components/ChatParent";

@Component({
  name: "ConnectBox"
})
export default class TypeBox extends ChatParent {}
</script>

<style></style>
