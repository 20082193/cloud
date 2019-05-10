package com.example.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class myFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
//        RequestContext currentContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = currentContext.getRequest();
//        String name = request.getParameter("name");
//        if (name!=null && !name.equalsIgnoreCase("jack")) {
//            try {
//                currentContext.getResponse().getWriter().write("guolvle");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("过滤了");
//            return false;
//        }
//        System.out.println("===========");
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println((String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString())));
        Object accessToken = request.getParameter("token");
        if(accessToken != null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}
            return null;
        }
        return null;
    }
}
