package com.example.springbootfilterhandlerlistener.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 *
 * 使用注解标注过滤器
 * @WebFilter将一个实现了javax.servlet.Filter接口的类定义为过滤器
 * 属性filterName 声明过滤器的名称,可选
 * 属性urlPatterns指定要过滤 的URL模式,这是一个数组参数，可以指定多个。也可使用属性value来声明.(指定要过滤的URL模式是必选属性)
 *
 *
 * 如果不使用注解，可以使用如下形式
 *   private IDataTopicService iDataTopicService;
 *
 *   public SpringBootFilter(IDataTopicService iDataTopicService) {
 *       this.iDataTopicService = iDataTopicService;
 *   }
 */
@WebFilter(filterName="SpringBootFilter", urlPatterns={"/*"})
public class SpringBootFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init :: 过滤器的初始方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("doFilter :: 在这里执行过滤器的方法");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy :: 过滤器的销毁方法");
    }
}
