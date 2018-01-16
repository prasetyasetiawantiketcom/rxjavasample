package com.tiket.tix.reactive.sample.service.impl;

import com.tiket.tix.reactive.sample.service.api.ReactiveService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReactiveServiceImpl implements ReactiveService {

  @Override
  public Single<String> simpleReactive() {
    return Single.<String>create(singleEmitter -> {
      System.out.println(Thread.currentThread().getName() + "-Simple");
      singleEmitter.onSuccess("SimpleReactive");
    }).subscribeOn(Schedulers.io());
  }

  @Override
  public Single<List<Integer>> calculateSingleThreading(List<Integer> integers, Integer modifier,
      Long sleep) {
    System.out.println(Thread.currentThread().getName() + " Service");
    List<Integer> integersResponse = new ArrayList<>();

    return this.calcTime(integers, modifier, sleep)
        .map(timeResponse -> integersResponse.addAll(timeResponse))
        .flatMap(timeResponse -> this.calcAdd(integers, modifier, sleep))
        .map(addResponse -> integersResponse.addAll(addResponse))
        .flatMap(addResponse -> this.calcMinus(integers, modifier, sleep))
        .map(minusResponse -> {
          integersResponse.addAll(minusResponse);
          return integersResponse;
        });
  }

  @Override
  public Single<List<Integer>> calculateMultiThreadingPerOperator(List<Integer> integers, Integer modifier,
      Long sleep) {
    System.out.println(Thread.currentThread().getName() + " Service");
    return Single
        .zip(this.createCalculation(integers, modifier, sleep),
            args -> this.<Integer>mergeList(args));
  }

  @Override
  public Single<List<Integer>> calculateMultiThreadingPerItem(List<Integer> integers,
      Integer modifier,
      Long sleep) {
    System.out.println(Thread.currentThread().getName() + " Service");
    List<Single<Integer>> integersSingle = new ArrayList<>();
    for (Integer integer : integers) {
      integersSingle.add(this.calcAddOne(integer, modifier));
    }

    return Single.zip(integersSingle, args -> this.<Integer>mergeObject(args));
  }

  private List<Single<List<Integer>>> createCalculation(List<Integer> integers, Integer modifier,
      Long sleep) {
    List<Single<List<Integer>>> singleIntegers = new ArrayList<>();
    singleIntegers.add(this.calcTime(integers, modifier, sleep));
    singleIntegers.add(this.calcAdd(integers, modifier, sleep));
    singleIntegers.add(this.calcMinus(integers, modifier, sleep));

    return singleIntegers;
  }

  private <T> List<T> mergeList(Object... objects) {
    List<T> list = new ArrayList<>();
    for (Object object : objects) {
      List<T> tList = (List<T>) object;
      list.addAll(tList);
    }

    return list;
  }

  private <T> List<T> mergeObject(Object... objects) {
    List<T> list = new ArrayList<>();
    for (Object object : objects) {
      T t = (T) object;
      list.add(t);
    }

    return list;
  }

  private Single<List<Integer>> calcTime(List<Integer> integers, Integer modifier, Long sleep) {
    return Single.<List<Integer>>create(singleEmitter -> {
      List<Integer> calcIntegerTime = new ArrayList<>();
      integers.forEach(integer -> {
        try {
          Thread.sleep(sleep);
        } catch (Exception e) {

        }
        calcIntegerTime.add(integer * modifier);
        System.out
            .println(Thread.currentThread().getName() + "-Times-" + System.currentTimeMillis());
      });
      singleEmitter.onSuccess(calcIntegerTime);
    }).subscribeOn(Schedulers.computation());
  }

  private Single<List<Integer>> calcMinus(List<Integer> integers, Integer modifier, Long sleep) {
    return Single.<List<Integer>>create(singleEmitter -> {
      List<Integer> calcIntegerMinus = new ArrayList<>();
      integers.forEach(integer -> {
        try {
          Thread.sleep(sleep);
        } catch (Exception e) {

        }
        calcIntegerMinus.add(integer - modifier);
        System.out
            .println(Thread.currentThread().getName() + "-Minus-" + System.currentTimeMillis());
      });
      singleEmitter.onSuccess(calcIntegerMinus);
    }).subscribeOn(Schedulers.computation());
  }

  private Single<List<Integer>> calcAdd(List<Integer> integers, Integer modifier, Long sleep) {
    return Single.<List<Integer>>create(singleEmitter -> {
      List<Integer> calcIntegerAdd = new ArrayList<>();
      integers.forEach(integer -> {
        try {
          Thread.sleep(sleep);
        } catch (Exception e) {

        }
        calcIntegerAdd.add(integer + modifier);
        System.out.println(Thread.currentThread().getName() + "-Add-" + System.currentTimeMillis());
      });
      singleEmitter.onSuccess(calcIntegerAdd);
    }).subscribeOn(Schedulers.computation());
  }

  private Single<Integer> calcAddOne(Integer integer, Integer modifier) {
    return Single.<Integer>create(singleEmitter -> {
      System.out
          .println(Thread.currentThread().getName() + "-AddOne-" + System.currentTimeMillis());
      Integer finalPrice = integer + modifier;
      singleEmitter.onSuccess(finalPrice);
    }).subscribeOn(Schedulers.computation());
  }
}
