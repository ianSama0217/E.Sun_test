import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("../views/HomeView.vue"),
    },
    {
      path: "/seatsChart",
      name: "seatsChart",
      component: () => import("../views/SeatsView.vue"),
    },
    {
      path: "/employee",
      name: "employee",
      component: () => import("../views/EmployeeView.vue"),
    },
  ],
});

export default router;
