package com.landary.kmss.observer;

/**
 * @Author 帅
 * @Date 2021/8/4 21:14
 * @Description
 **/
public class RealObject implements Observer{
    @Override
    public void update() {
        System.out.println("接收到了通知");
    }
}
