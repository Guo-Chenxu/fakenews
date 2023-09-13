package com.guochenxu.wemedia.interceptor;

import com.guochenxu.model.wemedia.entity.WmUser;
import com.guochenxu.utils.thread.WmThreadLocalUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: fakenews
 * @description: wm拦截器
 * @author: 郭晨旭
 * @create: 2023-09-13 15:40
 * @version: 1.0
 **/
public class WmTokenInterceptor implements HandlerInterceptor {

    /**
     * 前置处理, 获取用户信息并存入当前线程中
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        if (StringUtils.isNotBlank(userId)) {
            WmUser user = new WmUser(Integer.valueOf(userId));
            WmThreadLocalUtil.setUser(user);
        }
        return true;
    }

    /**
     * 后置处理, 清除线程中的数据
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        WmThreadLocalUtil.removeUser();
    }
}
