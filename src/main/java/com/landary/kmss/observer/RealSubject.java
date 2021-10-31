package com.landary.kmss.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 帅
 * @Date 2021/8/4 21:12
 * @Description 观察者模式:定义对象间一种一对多的依赖关系，使得每当一个对象状态发生改变时，则所有依赖它的对象都会得到通知并自动更新
 *  一个对象（目标对象）状态发生改变，所有依赖的对象（观察者）都会的得到通知
 *              生产者与消费者模式：
 *                  传统模式：1.生产者直接将消息传递给指定的消费者
 *                           2.耦合性特别高，当生产者或者消费者发生变化时，都需要重写业务逻辑
 *                  生产者与消费者模式：通过一个容器来解决生产者和消费者的耦合问题，消费者和生产者之间不直接通讯，而通过阻塞队列进行通讯
 *                          数据传递流程：
 *                              生产者与消费者模式：1.即N个线程进行进行生产，N个线程进行消费，两种角色通过内存缓存区进行通信
 *                              2.生产者负责向缓存区中添加数据单元
 *                              3.消费者负责从缓存区中取出数据单元
 *                              4.一般遵循先进先出的原则
 **/
public class RealSubject implements Subject{
    private List<Observer> list = new ArrayList<>();

    @Override
    public void attache(Observer observer) {
        list.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void notifyChanged() {
        for (Observer observer : list){
            observer.update();
        }
    }
}
