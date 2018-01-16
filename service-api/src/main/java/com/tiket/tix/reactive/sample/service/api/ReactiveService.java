package com.tiket.tix.reactive.sample.service.api;

import io.reactivex.Single;
import java.util.List;

public interface ReactiveService {

  Single<String> simpleReactive();

  Single<List<Integer>> calculateSingleThreading(List<Integer> integers, Integer modifier, Long sleep);

  Single<List<Integer>> calculateMultiThreadingPerOperator(List<Integer> integers, Integer modifier, Long sleep);

  Single<List<Integer>> calculateMultiThreadingPerItem(List<Integer> integers, Integer modifier, Long sleep);
}
