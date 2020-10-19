package com.Allicnce.taobaoAlliance.common.commonutils;

import java.util.*;

/**
 * author NullPointer
 * create time 2016/12/21$ 17:39$
 * descrition:
 */

public class TimerUtils {
    public static Map<String, Timer> timerMap = new HashMap<>();

    /**
     * 指定时间更新
     * @param id timer的id
     * @param hour 时
     * @param minute 分
     * @param second 秒
     * @param interval 间隔
     * @param task 执行任务接口
     */
    public static void setSomeTimeClock(String id, int hour, int minute, int second, long interval, final ExecuteTask task) {
        Timer timer = new Timer();
        timerMap.put(id, timer);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                task.excute();
            }
        };
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hour, minute, second);
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {//如果设置的时间当天已经过去,则加一天
            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH) + 1, hour, minute, second);
        }
        Date date = new Date(calendar.getTimeInMillis());
        timer.schedule(timerTask, date, interval); //启动timer
    }

    /**
     * 指定时间更新
     * @param id timer的id
     * @param interval 间隔
     * @param task 执行任务接口
     */
    public static void setNowClock(String id, int interval, final ExecuteTask task) {
        Timer timer = new Timer();
        timerMap.put(id, timer);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                task.excute();
            }
        };
        timer.schedule(timerTask, new Date(), interval); //启动timer
    }
    public static void cancleClock(String timerId) {
        timerMap.get(timerId).cancel();
    }

    //执行任务的接口回调
  public   interface ExecuteTask {
        void excute();
    }
}
