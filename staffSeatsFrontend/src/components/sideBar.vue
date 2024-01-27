<script setup>
import { RouterLink } from "vue-router";
</script>

<template>
  <div class="sideBar">
    <RouterLink to="/seatsChart" class="link">座位表</RouterLink>
    <RouterLink to="/employee" class="link">員工資訊</RouterLink>
  </div>
</template>

<style scoped lang="scss">
.sideBar {
  height: 100vh;
  width: 20vw;
  background-color: #00a19b;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 5vh;

  .link {
    text-decoration: none;
    width: 95%;
    text-align: center;
    padding: 0.2rem;
    border-radius: 8px;
    color: white;
    font-size: 2rem;
    margin: 2rem 0;
    transition: 0.3s ease;

    &:hover {
      background-color: white;
      color: #00a19b;
    }
  }
}
</style>
