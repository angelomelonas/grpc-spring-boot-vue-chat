import Vue from "vue";
import Vuetify from "vuetify";
import App from "@/App.vue";

// Stylesheets
import "vuetify/dist/vuetify.min.css";
import "material-design-icons-iconfont/dist/material-design-icons.css";

Vue.config.productionTip = false;
Vue.use(Vuetify, {
  theme: {
    primary: "#1e88e5",
    secondary: "#66bb6a",
    accent: "#8c9eff",
    success: "#a5d6a7",
    info: "#eceff1",
    warning: "#ffd699",
    error: "#ffab91"
  }
});

new Vue({
  el: "#app",
  render: h => h(App)
});
