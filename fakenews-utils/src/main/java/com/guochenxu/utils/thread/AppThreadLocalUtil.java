package com.guochenxu.utils.thread;

import com.guochenxu.model.user.entity.ApUser;
import com.guochenxu.model.wemedia.entity.WmUser;

/**
 * @program: fakenews
 * @description: thread
 * @author: 郭晨旭
 * @create: 2023-09-13 15:44
 * @version: 1.0
 **/
public class AppThreadLocalUtil {
    private final static ThreadLocal<ApUser> AP_USER_THREAD_LOCAL = new ThreadLocal<ApUser>();

    public static void setUser(ApUser user) {
        AP_USER_THREAD_LOCAL.set(user);
    }

    public static ApUser getUser() {
        return AP_USER_THREAD_LOCAL.get();
    }

    public static void removeUser() {
        AP_USER_THREAD_LOCAL.remove();
    }
}
