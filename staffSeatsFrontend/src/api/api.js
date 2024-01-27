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

export default { getAllSeat };
