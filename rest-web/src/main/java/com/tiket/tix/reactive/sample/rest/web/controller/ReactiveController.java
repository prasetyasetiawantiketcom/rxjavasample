package com.tiket.tix.reactive.sample.rest.web.controller;

import com.tiket.tix.common.rest.web.model.response.BaseResponse;
import com.tiket.tix.common.rest.web.model.response.CommonResponse;
import com.tiket.tix.reactive.sample.entity.constant.enums.ResponseCode;
import com.tiket.tix.reactive.sample.service.api.ReactiveService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import io.swagger.annotations.Api;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@Api(value = "Reactive", description = "Reactive Example")
public class ReactiveController {

  @Autowired
  ReactiveService reactiveService;

  @RequestMapping(value = "/exampleNoSubscribe", method = RequestMethod.GET)
  public void exampleReactiveNoSubscribe() {
    System.out.println(Thread.currentThread().getName() + " Controller");
    Single<String> single = Single.create(singleEmitter -> {
      Thread.sleep(3000);
      singleEmitter.onSuccess("Hi Im Reactive");
    });
  }

  @RequestMapping(value = "/exampleSubscribe", method = RequestMethod.GET)
  public void exampleReactiveSubscribe() {
    System.out.println(Thread.currentThread().getName() + " Controller");
    Single<String> single = Single.create(singleEmitter -> {
      System.out.println(Thread.currentThread().getName() + " exampleSubscribe");
      Thread.sleep(3000);
      singleEmitter.onSuccess("Hi Im Reactive");
    });
    single.subscribe(System.out::println);
  }

  @RequestMapping(value = "/exampleSubscribeWithCustomThread", method = RequestMethod.GET)
  public void exampleReactiveSubscribeWithCustomThread() {
    System.out.println(Thread.currentThread().getName() + " Controller");
    Single<String> single = Single.<String>create(singleEmitter -> {
      System.out.println(Thread.currentThread().getName() + " exampleSubscribeWithCustomThread");
      Thread.sleep(5000);
      singleEmitter.onSuccess("Hi Im Reactive");
    }).subscribeOn(Schedulers.computation());
    single.subscribe(System.out::println);
  }

  @RequestMapping(value = "/simple", method = RequestMethod.GET)
  public DeferredResult<BaseResponse<String>> simpleReactive() {
    System.out.println(Thread.currentThread().getName() + " Controller");

    DeferredResult<BaseResponse<String>> deferred = new DeferredResult<>();

    this.reactiveService.simpleReactive()
        .map(result -> CommonResponse
            .constructResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),
                null, result))
        .subscribe(success -> deferred.setResult(success), error -> deferred.setErrorResult(error));

    return deferred;
  }

  @RequestMapping(value = "/calculateSingleThread", method = RequestMethod.GET)
  public DeferredResult<BaseResponse<List<Integer>>> calculateSingleThread(
      @RequestParam("sample") Integer sample, @RequestParam("modifier") Integer modifier,
      @RequestParam("sleep") Long sleep) {
    System.out.println(Thread.currentThread().getName() + " Controller");
    DeferredResult<BaseResponse<List<Integer>>> deferred = new DeferredResult<>();

    this.reactiveService.calculateSingleThreading(this.generateSample(sample), modifier, sleep)
        .map(calculateResult -> CommonResponse
            .constructResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),
                null, calculateResult))
        .subscribe(success -> deferred.setResult(success), error -> deferred.setErrorResult(error));

    return deferred;

  }

  @RequestMapping(value = "/calculateMultiThreadPerOperator", method = RequestMethod.GET)
  public DeferredResult<BaseResponse<List<Integer>>> calculateMultiThread(
      @RequestParam("sample") Integer sample, @RequestParam("modifier") Integer modifier,
      @RequestParam("sleep") Long sleep) {
    System.out.println(Thread.currentThread().getName() + " Controller");
    DeferredResult<BaseResponse<List<Integer>>> deferred = new DeferredResult<>();

    this.reactiveService.calculateMultiThreadingPerOperator(this.generateSample(sample), modifier, sleep)
        .map(calculateResult -> CommonResponse
            .constructResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),
                null, calculateResult))
        .subscribe(success -> deferred.setResult(success), error -> deferred.setErrorResult(error));

    return deferred;

  }

  @RequestMapping(value = "/calculateMultiThreadPerItem", method = RequestMethod.GET)
  public DeferredResult<BaseResponse<List<Integer>>> calculateMultiThreadPerItem(
      @RequestParam("sample") Integer sample, @RequestParam("modifier") Integer modifier,
      @RequestParam("sleep") Long sleep) {
    System.out.println(Thread.currentThread().getName() + " Controller");
    DeferredResult<BaseResponse<List<Integer>>> deferred = new DeferredResult<>();

    this.reactiveService.calculateMultiThreadingPerItem(this.generateSample(sample), modifier, sleep)
        .map(calculateResult -> CommonResponse
            .constructResponse(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),
                null, calculateResult))
        .subscribe(success -> deferred.setResult(success), error -> deferred.setErrorResult(error));

    return deferred;

  }

  private List<Integer> generateSample(Integer loop) {
    List<Integer> integers = new ArrayList<>();
    for (int i = 0; i < loop; i++) {
      integers.add(i);
    }

    return integers;
  }
}
