import { Component, Vue } from "vue-property-decorator";
import chat from "@/store/modules/chat";

@Component
export default class ChatParent extends Vue {
  private created(): void {
    // If the window is closed or reloaded, unsubscribe.
    window.addEventListener("beforeunload", this.onClose);

    // On app creation, connect to the gRPC server.
    chat.connectClient("http://localhost:8080");
  }

  private destroyed(): void {
    if (this.isSubscribed) {
      chat.unsubscribe();
    }
  }

  get username(): string {
    return chat.getUsername;
  }

  get isSubscribed(): boolean {
    return chat.isSubscribed;
  }

  get messages(): string {
    return chat.getMessages;
  }

  sendMessage(message: string): void {
    chat.sendMessage(message);
  }

  subscribe(): void {
    chat.subscribe();
  }

  unsubscribe(): void {
    chat.unsubscribe();
  }

  setUsername(username: string) {
    chat.setUsername(username);
  }

  private onClose() {
    if (this.isSubscribed) {
      chat.unsubscribe();
    }
  }
}
