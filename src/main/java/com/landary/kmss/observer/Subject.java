package com.landary.kmss.observer;

/**
 * @Author 帅
 * @Date 2021/8/4 21:08
 * @Description 观察者主题对象
 **/
public interface Subject {
    /**
     * @Description 订阅操作
     * @Param
     **/
    void attache(Observer observer);

    /**
     * @Description 取消订阅操作
     * @Param
     **/
    void detach(Observer observer);

    /**
     * @Description 通知变动
     * @Param
     **/
    void notifyChanged();
}
