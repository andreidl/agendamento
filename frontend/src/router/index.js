import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/Home.vue';
import ExtratoView from '../views/Extrato.vue';

const routes = [
  { path: '/', name: 'HomeView', component: HomeView },
  { path: '/extrato', name: 'ExtratoView', component: ExtratoView },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
