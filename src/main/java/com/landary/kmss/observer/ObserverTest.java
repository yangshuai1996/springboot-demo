package com.landary.kmss.observer;

/**
 * @Author 帅
 * @Date 2021/8/4 21:15
 * @Description 观察者测试类
 **/
public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        Observer observer = new RealObject();
        subject.attache(observer);
        subject.notifyChanged();
    }
}
