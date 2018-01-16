package com.tiket.tix.reactive.sample.entity.constant;

public interface ApiPath {
    /* Change base path to micro service name ex : payment, promotion, member, login, etc
        String BASE_PATH = {project_name};
        String SYSTEM_PARAMETER = BASE_PATH + "/systemParameter";
    */

  String BASE_PATH = "/archetype-mongodb";
  String SYSTEM_PARAMETER = "/systemParameter";
  String EXTERNAL_SYSTEM_PARAMETER = "/externalSystemParameter";
}
