package com.tiket.tix.reactive.sample.rest.web.model;

import com.tiket.tix.common.entity.ObjectHelper;

public class CommonModel {

  @Override
  public boolean equals(Object obj) {
    return ObjectHelper.equals(this, obj);
  }

  @Override
  public int hashCode() {
    return ObjectHelper.hashCode(this);
  }
}
