package com.tiket.tix.reactive.sample.rest.web.model.request;

import com.tiket.tix.reactive.sample.rest.web.model.CommonModel;
import org.hibernate.validator.constraints.NotBlank;

public class SystemParameterRequest extends CommonModel {

  private static final long serialVersionUID = 1L;

  @NotBlank
  private String variable;

  @NotBlank
  private String value;

  @NotBlank
  private String description;

  public String getVariable() {
    return variable;
  }

  public void setVariable(String variable) {
    this.variable = variable;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "SystemParameterRequest{" +
        "variable='" + variable + '\'' +
        ", value='" + value + '\'' +
        ", description='" + description + '\'' +
        "} " + super.toString();
  }
}
