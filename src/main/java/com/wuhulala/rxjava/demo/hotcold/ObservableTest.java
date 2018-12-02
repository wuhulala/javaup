package com.wuhulala.rxjava.demo.hotcold;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
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
public class ObservableTest {

    /**
     * Cold Observable
     * <p>
     * <p>只有观察者订阅了，才开始执行发射数据流的代码</p>
     * <p>
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
     * <p>
     * <p>多个subscribe（观察者）共享同一事件。在这里，ConnectableObservable</p>
     *
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
        Consumer<Long> subscribe1 = aLong -> System.out.println("subscribe1: " + aLong);
        Consumer<Long> subscribe2 = aLong -> System.out.println("  subscribe2: " + aLong);
        Consumer<Long> subscribe3 = aLong -> System.out.println("    subscribe3: " + aLong);

        Observable<Long> observable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> observableEmitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(observableEmitter::onNext);
            }
        }).observeOn(Schedulers.newThread());

        PublishSubject<Long> subject = PublishSubject.create();
        // 既可以订阅
        observable.subscribe(subject);

        // 又可以发送
        subject.subscribe(subscribe1);
        subject.subscribe(subscribe2);
        Thread.sleep(200);

        subject.subscribe(subscribe3);

        Thread.sleep(100);

    }

    /**
     * 数据流会中断
     *
     * @throws InterruptedException
     */
    @Test
    @DisplayName("Hot Observable 转换成 Cold Observable -- 全部取消订阅")
    public void testHot2ColdObservable() throws InterruptedException {
        Consumer<Long> subscribe1 = aLong -> System.out.println("subscribe1: " + aLong);
        Consumer<Long> subscribe2 = aLong -> System.out.println("  subscribe2: " + aLong);

        ConnectableObservable<Long> connectableObservable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> observableEmitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(observableEmitter::onNext);
            }
        }).observeOn(Schedulers.newThread()).publish();
        connectableObservable.connect();


        Observable<Long> observable = connectableObservable.refCount();

        Disposable disposable1 = observable.subscribe(subscribe1);
        Disposable disposable2 = observable.subscribe(subscribe2);
        Thread.sleep(20L);
        // 对资源释放，也就是取消订阅
        disposable1.dispose();
        disposable2.dispose();
        System.out.println(">>>>>> 重新开始数据流");

        disposable1 = observable.subscribe(subscribe1);
        disposable2 = observable.subscribe(subscribe2);
        Thread.sleep(20L);

    }


    /**
     * 部分取消订阅 数据流增加并不会中断
     *
     * @throws InterruptedException
     */
    @Test
    @DisplayName("Hot Observable 转换成 Cold Observable -- 部分取消订阅")
    public void testHot2ColdObservable2() throws InterruptedException {
        Consumer<Long> subscribe1 = aLong -> System.out.println("subscribe1: " + aLong);
        Consumer<Long> subscribe2 = aLong -> System.out.println("  subscribe2: " + aLong);
        Consumer<Long> subscribe3 = aLong -> System.out.println("    subscribe3: " + aLong);

        ConnectableObservable<Long> connectableObservable = Observable.create(new ObservableOnSubscribe<Long>() {
            @Override
            public void subscribe(ObservableEmitter<Long> observableEmitter) throws Exception {
                Observable.interval(10, TimeUnit.MILLISECONDS, Schedulers.computation())
                        .take(Integer.MAX_VALUE)
                        .subscribe(observableEmitter::onNext);
            }
        }).observeOn(Schedulers.newThread()).publish();
        connectableObservable.connect();


        Observable<Long> observable = connectableObservable.refCount();

        Disposable disposable1 = observable.subscribe(subscribe1);
        Disposable disposable2 = observable.subscribe(subscribe2);
        observable.subscribe(subscribe3);
        Thread.sleep(20L);
        // 对资源释放，也就是取消订阅
        disposable1.dispose();
        disposable2.dispose();
        System.out.println(">>>>>> 重新开始数据流");

        disposable1 = observable.subscribe(subscribe1);
        disposable2 = observable.subscribe(subscribe2);
        Thread.sleep(20L);

    }

    /**
     * 只能发射一个数据，后面的数据再次发射也不会进行处理
     */
    @Test
    @DisplayName("测试 single 这种类型的Observable")
    public void testSingle() {
        Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(SingleEmitter<String> singleEmitter) throws Exception {
                singleEmitter.onSuccess("test single");
                singleEmitter.onSuccess("test single2");

                //throw new RuntimeException("hello world");
            }
        }).subscribe(System.out::println, Throwable::printStackTrace);
    }

    /**
     * Completable 在创建后不会发射任何的数据，也就是发射0个数据
     */
    @Test
    @DisplayName("测试 Completable")
    public void testCompletable() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                TimeUnit.SECONDS.sleep(1);
                // 执行完成之后，再执行其他操作
                completableEmitter.onComplete();
            }
        }).andThen(Observable.range(1, 10))
                .subscribe(System.out::println);
    }


    /**
     * Maybe
     */
    @Test
    @DisplayName("测试 Maybe： 能发射0,1个数据")
    public void testMaybe() {
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> maybeEmitter) throws Exception {
                // 如果先调用了onComplete，那么后面也不会发射了
                maybeEmitter.onComplete();
                maybeEmitter.onSuccess("maybe -- 1");
            }
        })
                .subscribe(System.out::println);

    }

    /**
     * Maybe
     */
    @Test
    @DisplayName("测试 Maybe： 能发射0,1个数据,添加complete函数")
    public void testMaybe2() {
        Maybe.create(new MaybeOnSubscribe<String>() {
            @Override
            public void subscribe(MaybeEmitter<String> maybeEmitter) throws Exception {
                // 如果先调用了onComplete，那么后面也不会发射了
                maybeEmitter.onComplete();
                maybeEmitter.onSuccess("maybe -- 1");
            }
        }).subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("Maybe Complete"));

    }


}
