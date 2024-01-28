<script setup>
import { ref, onMounted } from "vue";
import api from "../api/api.js";

const { getAllEmp, createEmp, deleteEmp } = api;

//儲存員工資料
const emps = ref([]);
//v-model參數
const empId = ref("");
const empName = ref("");
const empEmail = ref("");

//控制彈跳視窗
const isDisplay = ref(false);

const isDisplayHandler = () => {
  isDisplay.value = !isDisplay.value;
};

const createEmpHandler = async () => {
  const req = {
    emp_id: empId.value,
    name: empName.value,
    email: empEmail.value,
  };

  await createEmp(req);

  //重新印出更新後的資料
  emps.value = await getAllEmp();
  isDisplayHandler();
};

const deleteEmpHandler = async (id) => {
  await deleteEmp(id);

  //重新印出更新後的資料
  emps.value = await getAllEmp();
};

onMounted(async () => {
  emps.value = await getAllEmp();
});
</script>

<template>
  <div class="body">
    <h1>員工資料</h1>
    <table border="1">
      <thead>
        <tr>
          <th>員工編號</th>
          <th>姓名</th>
          <th>信箱</th>
          <th>座位編號</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in emps">
          <td>{{ item.emp_id }}</td>
          <td>{{ item.name }}</td>
          <td>{{ item.email }}</td>
          <td v-if="item.floor_seat_seq == null">無</td>
          <td v-if="item.floor_seat_seq != null">{{ item.floor_seat_seq }}</td>
          <td>
            <button type="button" @click="deleteEmpHandler(item.emp_id)">
              刪除
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <button @click="isDisplayHandler" type="button" class="addBtn">
      新增員工
    </button>
  </div>

  <!-- 彈跳視窗 -->
  <div class="popWindow" v-if="isDisplay">
    <div class="inputBar">
      <label>員工編號</label>
      <input type="text" v-model="empId" />
    </div>
    <div class="inputBar">
      <label>姓名</label>
      <input type="text" v-model="empName" />
    </div>
    <div class="inputBar">
      <label>信箱</label>
      <input type="text" v-model="empEmail" />
    </div>
    <div class="btnBar">
      <button type="button" @click="isDisplayHandler">取消</button>
      <button type="button" @click="createEmpHandler">確認</button>
    </div>
  </div>

  <div class="background" v-if="isDisplay"></div>
</template>

<style scoped lang="scss">
.body {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 80vw;
  height: 80vh;
  padding: 1rem;
  table {
    border: 1px solid #00a19b;
    border-collapse: collapse;
    width: 100%;
    margin: 1rem 0;
    thead {
      th {
        background-color: #00a19b;
        color: white;
        font-size: 1.5rem;
        padding: 10px;
      }
    }
    tbody {
      tr {
        &:nth-child(even) {
          background-color: #f2f2f2;
        }
        td {
          border: 1px solid #00a19b;
          padding: 10px;
          font-size: 1rem;
        }
        button {
          background-color: transparent;
          border: none;
          color: #00a19b;
          cursor: pointer;
          &:hover {
            text-decoration: underline;
          }
        }
      }
    }
  }

  .addBtn {
    background-color: transparent;
    border: 1px solid #00a19b;
    border-radius: 8px;
    padding: 0.2rem 0.5rem;
    color: #00a19b;
    font-size: 1.5rem;
    cursor: pointer;

    &:hover {
      background-color: #00a19b;
      color: white;
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
