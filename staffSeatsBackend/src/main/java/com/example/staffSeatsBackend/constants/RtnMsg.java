package com.example.staffSeatsBackend.constants;

public enum RtnMsg {

	PARAM_ERROR("參數錯誤"), //
	EMP_ID_FORMAT_ERROR("員工編號格式錯誤"), //
	EMAIL_FORMAT_ERROR("信箱格式錯誤"), //
	EMP_ID_IS_EXIST("員工編號已存在"), //
	EMAIL_IS_EXIST("信箱已存在"), //
	CREATE_EMPLOYEE_FAILED("新增員工失敗"), //
	CREATE_EMPLOYEE_SUCCESSFUL("新增員工成功"), //
	FLOOR_IS_EXIST("樓層已存在"), //
	CREATE_FLOOR_FAILED("新增樓層失敗"), //
	CREATE_FLOOR_SUCCESSFUL("新增樓層成功"), //
	EMP_ID_NOT_FOUND("找不到員工編號"), //
	DELETE_EMPLOYEE_FAILED("刪除員工失敗"), //
	DELETE_EMPLOYEE_SUCCESSFUL("刪除員工成功"), //
	EMPLOYEE_DATA_ERROR("員工資料異常"), //
	GET_EMPLOYEE_INFO_SUCCESSFUL("取得員工資料"), //
	SEAT_ID_NOT_FOUND("找不到座位編號"), //
	SEAT_DATA_ERROR("座位資料異常"), //
	GET_SEAT_INFO_SUCCESSFUL("取得座位資料"),//
	INSERT_USER_FAILED("新增使用者失敗"), //
	INSERT_USER_SUCCESSFUL("新增使用者成功"), //
	;

	private String message;

	private RtnMsg(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
