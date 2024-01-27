<script setup>
import { ref, onMounted } from "vue";
import api from "../api/api.js";

const { getAllEmp } = api;

const emps = ref([]);

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
          <td><button type="button">刪除</button></td>
        </tr>
      </tbody>
    </table>
    <button type="button" class="addBtn">新增員工</button>
  </div>
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
</style>
