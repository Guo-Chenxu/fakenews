package com.guochenxu.search.interceptor;

import com.guochenxu.model.user.entity.ApUser;
import com.guochenxu.model.wemedia.entity.WmUser;
import com.guochenxu.utils.thread.AppThreadLocalUtil;
import com.guochenxu.utils.thread.WmThreadLocalUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 郭晨旭
 * @create: 2023-10-15 16:45
 * @version: 1.0
 */
public class AppTokenInterceptor implements HandlerInterceptor {

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
            ApUser user = new ApUser();
            user.setId(Integer.valueOf(userId));
            AppThreadLocalUtil.setUser(user);
        }
        return true;
    }

    /**
     * 后置处理, 清除线程中的数据
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        AppThreadLocalUtil.removeUser();
    }
}
