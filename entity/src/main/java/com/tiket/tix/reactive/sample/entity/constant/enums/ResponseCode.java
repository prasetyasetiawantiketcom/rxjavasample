package com.tiket.tix.reactive.sample.entity.constant.enums;

public enum ResponseCode {
  SUCCESS("SUCCESS", "SUCCESS"),
  SYSTEM_ERROR("SYSTEM_ERROR", "Contact our team"),
  DUPLICATE_DATA("DUPLICATE_DATA", "Duplicate data"),
  DATA_NOT_EXIST("DATA_NOT_EXIST", "No data exist"),
  RUNTIME_ERROR("RUNTIME_ERROR", "Runtime Error"),
  BIND_ERROR("BIND_ERROR", "Please fill in mandatory parameter");

  private String code;
  private String message;

  ResponseCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
