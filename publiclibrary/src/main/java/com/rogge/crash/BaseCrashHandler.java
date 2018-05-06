package com.rogge.crash;

/**
 * [Description]
 * [How to use]
 * <p>
 * [Tips]
 *
 * @author Created by Rogge on 2017/6/18.
 * @since 1.0.0
 */

public abstract class BaseCrashHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        if (handleException(ex)) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(1);
        }
    }
    public abstract boolean handleException(Throwable ex);
}
