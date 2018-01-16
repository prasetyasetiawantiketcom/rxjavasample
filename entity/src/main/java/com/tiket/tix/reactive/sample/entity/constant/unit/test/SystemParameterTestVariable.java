package com.tiket.tix.reactive.sample.entity.constant.unit.test;

import com.tiket.tix.reactive.sample.entity.dao.SystemParameter;
import com.tiket.tix.reactive.sample.entity.dao.SystemParameterBuilder;
import java.util.Date;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface SystemParameterTestVariable {

  String VARIABLE = "variable";
  String VALUE = "value";
  String DESCRIPTION = "description";
  String USERNAME = "username";
  Integer PAGE = 0;
  int SIZE = 10;
  String SIZE_STRING = "10";
  Pageable PAGEABLE = new PageRequest(PAGE,SIZE);
  SystemParameter SYSTEM_PARAMETER = new SystemParameterBuilder()
      .withStoreId(CommonTestVariable.STORE_ID)
      .withValue(VALUE).withDescription(DESCRIPTION).withVariable(VARIABLE)
      .withCreatedBy(USERNAME).withCreatedDate(new Date()).withUpdatedBy(USERNAME)
      .withUpdatedDate(new Date())
      .build();
  String SYSTEM_PARAMETER_REQUEST = "{\"description\":\"description\",\"value\":\"value\",\"variable\":\"variable\"}";
}
