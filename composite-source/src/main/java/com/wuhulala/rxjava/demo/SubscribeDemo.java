package com.wuhulala.rxjava.demo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.jupiter.api.Test;
/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/11/29<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class SubscribeDemo {

    @Test
    public void testOne() {
        Observable.just("Hello World")
                .subscribe(System.out::println,
                        e -> {
                            System.out.println(e.getMessage());
                        }, () -> {
                            System.out.println("onComplete()");
                        });
    }


    @Test
    public void testTwo() {
        Observable.just("Hello World")
                .subscribe(System.out::println,
                        e -> {
                            System.out.println(e.getMessage());
                        }, () -> {
                            System.out.println("after -- onComplete()");
                        }, d -> {
                            System.out.println("pre -- subscribe");
                        });
    }

    /**
     * 等价于第三个
     */
    @Test
    public void testThree() {
        System.out.println("start....");
        Observable.just("Hello World")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("subscribe()");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete()");
                    }
                });
        // 还是同步的
        System.out.println("end....");
//        start....
//        subscribe()
//        Hello World
//        onComplete()
//        end....

    }

    @Test
    public void testFour() {
        Observable.just("Hello")
                .doOnNext(s -> {
                    System.out.println("doOnNext:" + s);
                })
                .doAfterNext(s -> {
                    System.out.println("doAfterNext:" + s);
                })
                .doOnComplete(() -> {
                    System.out.println("doOnCompelete");
                })
                .doOnSubscribe(disposable -> {
                    System.out.println("doOnSubscribe");
                })
                .doAfterTerminate(() -> {
                    System.out.println("doAfterTerminate: ");
                })
                .doFinally(() -> {
                    System.out.println("doFinally");
                })
                // 每发射一个数据事件就会触发这个回调，这个事件是 onNext，onError，onComplete
                .doOnEach(stringNotification -> {
                    System.out.println("doOnEach: " + (
                            stringNotification.isOnNext() ? "onNext" : stringNotification.isOnComplete() ? "onComplete" : "onError"));
                })
                // 订阅后可以取消订阅
                .doOnLifecycle(disposable -> {
                    System.out.println("doOnLifecycle: " + disposable.isDisposed());
                }, () -> {
                    System.out.println("doOnLifecycle run ：");
                })
                .doOnError(e -> {
                    System.out.println("onError: " + e.getMessage());
                })
                .subscribe(s -> {
                    System.out.println("收到消息： " + s);
                });
//        doOnSubscribe
//        doOnLifecycle: false
//        doOnNext:Hello
//        doOnEach: onNext
//        收到消息： Hello
//        doAfterNext:Hello
//                doOnCompelete
//        doOnEach: onComplete
//                doFinally
//        doAfterTerminate:
    }

}
