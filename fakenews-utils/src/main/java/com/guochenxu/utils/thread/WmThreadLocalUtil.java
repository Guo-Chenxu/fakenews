package com.guochenxu.utils.thread;

import com.guochenxu.model.wemedia.entity.WmUser;

/**
 * @program: fakenews
 * @description: thread
 * @author: 郭晨旭
 * @create: 2023-09-13 15:44
 * @version: 1.0
 **/
public class WmThreadLocalUtil {
    private final static ThreadLocal<WmUser> WM_USER_THREAD_LOCAL = new ThreadLocal<WmUser>();

    public static void setUser(WmUser user) {
        WM_USER_THREAD_LOCAL.set(user);
    }

    public static WmUser getUser() {
        return WM_USER_THREAD_LOCAL.get();
    }

    public static void removeUser() {
        WM_USER_THREAD_LOCAL.remove();
    }
}
