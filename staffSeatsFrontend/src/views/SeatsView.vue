<script setup>
import { ref, onMounted, reactive } from "vue";
import api from "../api/api.js";

const { getAllSeat, getSeatInfo, getAllEmp, insertUser, clearUser } = api;

//儲存所有座位資料
const seats = ref([]);
//儲存特定座位資料
let seat = reactive({});
//儲存員工資料
const emps = ref([]);
//select v-model
const employeeId = ref(null);
//控制彈跳視窗
const isDisplay = ref(false);

const isDisplayHandler = () => {
  isDisplay.value = !isDisplay.value;
};

const clearEmp = () => {
  employeeId.value = null;
};

//點擊確認按鈕，如果有employeeId就新增，沒有就刪除
const setSeatHandler = async (seatId) => {
  if (employeeId.value !== null) {
    // insertUser請求資料
    const req = {
      emp_id: employeeId.value,
      seat_id: seatId,
    };
    await insertUser(req);
  } else {
    await clearUser(seatId);
  }

  //重新印出更新後的資料
  seats.value = await getAllSeat();
  isDisplayHandler();
};

const getSeatInfoHandler = async (id) => {
  seat = await getSeatInfo(id);
  if (seat.employee !== null) {
    employeeId.value = seat.employee.emp_id;
  } else {
    employeeId.value = null;
  }
  isDisplayHandler();
};

onMounted(async () => {
  seats.value = await getAllSeat();
  emps.value = await getAllEmp();
});
</script>

<template>
  <div class="body">
    <div class="seatArea">
      <div v-for="(item, index) in seats" :key="index" class="seatChart">
        <div
          @click="getSeatInfoHandler(item.seatingChart.floor_seat_seq)"
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

  <!-- 彈跳視窗 -->
  <div class="popWindow" v-if="isDisplay">
    <p>{{ seat.seatingChart.floor_no }}樓</p>
    <p>座位{{ seat.seatingChart.seat_no }}</p>
    <span>選擇員工:</span>
    <select v-model="employeeId">
      <option v-if="seat.employee" :value="seat.employee.emp_id">
        {{ seat.employee.emp_id }}
      </option>
      <option v-for="item in emps" :value="item.emp_id">
        {{ item.emp_id }}
      </option>
    </select>

    <button type="button" @click="clearEmp">清除員工資料</button>
    <div class="btnbar">
      <button type="button" @click="isDisplayHandler">取消</button>
      <button
        type="button"
        @click="setSeatHandler(seat.seatingChart.floor_seat_seq)"
      >
        確認
      </button>
    </div>
  </div>
  <div class="background" v-if="isDisplay"></div>
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

.popWindow {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 30vw;
  height: 50vh;
  background-color: white;
  border-radius: 8px;
  z-index: 5;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.background {
  position: fixed;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 1;
}
</style>
