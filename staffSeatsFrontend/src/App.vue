<script setup>
import { RouterView } from "vue-router";
import sideBar from "./components/sideBar.vue";
</script>

<template>
  <div class="body">
    <sideBar />
    <RouterView />
  </div>
</template>

<style scoped>
.body {
  display: flex;
}
</style>
