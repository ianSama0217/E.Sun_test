import axios from "axios";

const API_URL = "http://localhost:8080";

//顯示所有座位
const getAllSeat = () => {
  return axios
    .get(`${API_URL}/seat`)
    .then((res) => {
      // console.log(res.data.seatInfoList);
      return res.data.seatInfoList;
    })
    .catch((e) => {
      throw e;
    });
};

//顯示對應id座位
const getSeatInfo = (id) => {
  return axios
    .get(`${API_URL}/seat/${id}`)
    .then((res) => {
      //console.log(res.data.seatInfo);
      return res.data.seatInfo;
    })
    .catch((e) => {
      throw e;
    });
};

//顯示所有員工
const getAllEmp = () => {
  return axios
    .get(`${API_URL}/employee`)
    .then((res) => {
      //console.log(res.data.employeeList);
      return res.data.employeeList;
    })
    .catch((e) => {
      throw e;
    });
};

//選擇座位
const insertUser = (req) => {
  return axios({
    url: `${API_URL}/seat/insert`,
    method: "PATCH",
    data: req,
  })
    .then((res) => {
      console.log(res);
    })
    .catch((e) => {
      throw e;
    });
};

//清除座位
const clearUser = (seatId) => {
  return axios({
    url: `${API_URL}/seat/${seatId}`,
    method: "PATCH",
  })
    .then((res) => {
      console.log(res);
    })
    .catch((e) => {
      throw e;
    });
};

//新增員工
const createEmp = (req) => {
  return axios({
    url: `${API_URL}/employee`,
    method: "POST",
    data: req,
  })
    .then((res) => {
      console.log(res);
    })
    .catch((e) => {
      throw e;
    });
};

//刪除員工
const deleteEmp = (empId) => {
  return axios({
    url: `${API_URL}/employee/${empId}`,
    method: "DELETE",
  })
    .then((res) => {
      console.log(res);
    })
    .catch((e) => {
      throw e;
    });
};

export default {
  getAllSeat,
  getSeatInfo,
  getAllEmp,
  insertUser,
  clearUser,
  createEmp,
  deleteEmp,
};
