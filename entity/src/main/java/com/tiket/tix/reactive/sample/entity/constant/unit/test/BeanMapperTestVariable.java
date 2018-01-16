package com.tiket.tix.reactive.sample.entity.constant.unit.test;

import com.tiket.tix.reactive.sample.entity.dao.SystemParameter;
import com.tiket.tix.reactive.sample.entity.dao.SystemParameterBuilder;
import java.util.Date;

public interface BeanMapperTestVariable {
  String VARIABLE = "variable";
  String VALUE = "value";
  String DESCRIPTION = "description";
  String USERNAME = "username";
  SystemParameter SYSTEM_PARAMETER = new SystemParameterBuilder()
      .withStoreId(CommonTestVariable.STORE_ID)
      .withValue(VALUE).withDescription(DESCRIPTION).withVariable(VARIABLE)
      .withCreatedBy(USERNAME).withCreatedDate(new Date()).withUpdatedBy(USERNAME)
      .withUpdatedDate(new Date())
      .build();
}
