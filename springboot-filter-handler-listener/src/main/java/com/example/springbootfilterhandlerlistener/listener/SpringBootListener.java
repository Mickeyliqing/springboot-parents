package com.example.springbootfilterhandlerlistener.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@WebListener
public class SpringBootListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("sessionCreated :: 监听 Listener");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("sessionDestroyed :: 销毁 Listener");
    }
}
