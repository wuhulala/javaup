package com.wuhulala.rxjava.demo.hotcold;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/12/2<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class HotColdObservableTest {

    /**
     * Cold Observable
     *
     * <p>只有观察者订阅了，才开始执行发射数据流的代码</p>
     *
     * <p>subscribe1 和 subscribe2 的结果不一定相同，它们两者完全是独立的。</p>
     *
     * @throws InterruptedException
     */
    @Test
    public void testColdObservable() throws InterruptedException {
        Consumer<Long> subscribe1 = aLong -> {
            System.out.println("subscribe1: " + aLong);
        };

        Consumer<Long> subscribe2 = aLong -> {
            System.out.println("subscribe2: " + aLong);
        };

        Observable<Long> observable = Observable.create((ObservableOnSubscribe<Long>) observableEmitter ->
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(observableEmitter::onNext)).observeOn(Schedulers.newThread());
        observable.subscribe(subscribe1);
        observable.subscribe(subscribe2);

        Thread.sleep(1000L);
    }


    /**
     * Hot Observable
     *
     * <p>多个subscribe（观察者）共享同一事件。在这里，ConnectableObservable</p>
     * @throws InterruptedException
     */
    @Test
    public void testHotObservable() throws InterruptedException {

        Consumer<Long> subscribe1 = aLong -> {
            System.out.println("subscribe1: " + aLong);
        };

        Consumer<Long> subscribe2 = aLong -> {
            System.out.println("subscribe2: " + aLong);
        };


        Consumer<Long> subscribe3 = aLong -> {
            System.out.println("subscribe3: " + aLong);
        };

        ConnectableObservable<Long> observable = Observable.create((ObservableOnSubscribe<Long>) observableEmitter ->
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(observableEmitter::onNext)).observeOn(Schedulers.newThread()).publish();
        observable.connect();

        observable.subscribe(subscribe1);
        observable.subscribe(subscribe2);
        Thread.sleep(100L);
        observable.subscribe(subscribe3);

        Thread.sleep(1000L);
    }


    @Test
    @DisplayName("测试RxJava的Subject")
    public void testSubject() throws InterruptedException {
        Consumer<Long> subscribe1 = aLong -> System.out.println("subscribe1: " +aLong);
        Consumer<Long> subscribe2 = aLong -> System.out.println("  subscribe2: " +aLong);
        Consumer<Long> subscribe3 = aLong -> System.out.println("    subscribe3: " +aLong);

        Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> observableEmitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(observableEmitter::onNext);
            }
        }).observeOn(Schedulers.newThread());

        PublishSubject<Long> subject = PublishSubject.create();
        observable.subscribe(subject);

        subject.subscribe(subscribe1);
        subject.subscribe(subscribe2);
        Thread.sleep(200);

        subject.subscribe(subscribe3);

        Thread.sleep(100);

    }
}
