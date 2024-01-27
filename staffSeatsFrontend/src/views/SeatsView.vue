<script setup>
import { ref, onMounted } from "vue";
import api from "../api/api.js";

const { getAllSeat } = api;

const seats = ref([]);

onMounted(async () => {
  seats.value = await getAllSeat();
});
</script>

<template>
  <div class="body">
    <div class="seatArea">
      <div v-for="(item, index) in seats" :key="index" class="seatChart">
        <div
          class="seatBox"
          :class="{
            empty: item.seatingChart.state == '空位',
            used: item.seatingChart.state == '已佔用',
          }"
        >
          <span>{{ item.seatingChart.floor_no }}樓:</span>
          <span>座位{{ item.seatingChart.seat_no }}</span>
          <span v-if="item.employee != null"
            >[員編:{{ item.employee.emp_id }}]</span
          >
        </div>
      </div>
    </div>

    <div class="hint">
      <div class="hintBox">
        <div class="emptyColor"></div>
        <p>空位</p>
      </div>

      <div class="hintBox">
        <div class="usedColor"></div>
        <p>已佔用</p>
      </div>

      <div class="hintBox">
        <div class="chooseColor"></div>
        <p>請選擇</p>
      </div>
    </div>

    <button type="button">送出</button>
  </div>
</template>

<style scoped lang="scss">
.body {
  display: flex;
  flex-direction: column;
  .seatArea {
    width: 80vw;
    height: 70vh;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    align-items: center;
    .seatChart {
      .seatBox {
        width: 18vw;
        padding: 0.2rem 0.5rem;
        border-radius: 8px;
        border: 1px solid #777;
        display: flex;
        justify-content: center;
        align-items: center;

        &:hover {
          cursor: pointer;
        }
      }

      .empty {
        background-color: #f1f1f1;
      }

      .used {
        background-color: #df0000;
        color: white;
      }
    }
  }

  .hint {
    height: 15vh;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    padding-left: 1rem;
    .hintBox {
      display: flex;
      .emptyColor {
        width: 2rem;
        height: 1.5rem;
        border-radius: 8px;
        background-color: #f1f1f1;
        margin-right: 0.5rem;
      }

      .usedColor {
        width: 2rem;
        height: 1.5rem;
        border-radius: 8px;
        background-color: #df0000;
        margin-right: 0.5rem;
      }

      .chooseColor {
        width: 2rem;
        height: 1.5rem;
        border-radius: 8px;
        background-color: #87ff96;
        margin-right: 0.5rem;
      }
    }
  }

  button {
    margin-top: 2rem;
    margin-left: 1rem;
    padding: 0.2rem;
    width: 5vw;
    color: white;
    background-color: #00479f;
    border: none;
    transition: 0.3s ease;

    &:hover {
      cursor: pointer;
    }
  }
}
</style>
