/*
 =========================================================
 * Vue Black Dashboard - v1.1.0
 =========================================================

 * Product Page: https://www.creative-tim.com/product/black-dashboard
 * Copyright 2018 Creative Tim (http://www.creative-tim.com)

 =========================================================

 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 */
import Vue from "vue";
import VueRouter from "vue-router";
import RouterPrefetch from 'vue-router-prefetch'
import App from "./App";
// TIP: change to import router from "./router/starterRouter"; to start with a clean layout
import router from "./router/index";

import BlackDashboard from "./plugins/blackDashboard";
import i18n from "./i18n"
import './registerServiceWorker'
import mixins from "./mixins";
import FormatDate from "./plugins/formatDate"
Vue.use(BlackDashboard);
Vue.use(VueRouter);
Vue.use(RouterPrefetch);
Vue.mixin(mixins);

Vue.prototype.$prefixURL = 'http://13.124.138.102/api';
// Vue.prototype.$prefixURL = 'http://localhost/api';
Vue.prototype.$formatDate = FormatDate;

Vue.filter("makeComma", val =>{
  return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ",");
});

/* eslint-disable no-new */
new Vue({
  router,
  i18n,
  render: h => h(App)
}).$mount("#app");
