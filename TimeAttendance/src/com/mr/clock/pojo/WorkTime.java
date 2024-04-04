package com.mr.clock.pojo;
/**
 * @author orane
 * @create 2024/3/31 20:29
 * @Description 作息时间类，与MySQL中的t_work_time对应
 **/

public class WorkTime {
    private String start;           //上班时间
    private String end;             //下班时间

    public WorkTime() {
    }

    public WorkTime(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
