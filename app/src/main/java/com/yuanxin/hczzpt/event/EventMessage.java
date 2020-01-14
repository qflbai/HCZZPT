package com.yuanxin.hczzpt.event;

/**
 * @author: qflbai
 * @CreateDate: 2019/7/30 17:17
 * @Version: 1.0
 * @description:
 */
public class EventMessage {
    public EventMessage(){}

    public EventMessage(int action) {
        this.action = action;
    }

    public EventMessage(int action, Object object) {
        this.action = action;
        this.object = object;
    }

    public EventMessage(int action, String content) {
        this.action = action;
        this.content = content;
    }

    public EventMessage(int action, Object object, String content) {
        this.action = action;
        this.object = object;
        this.content = content;
    }

    private int action;
    private Object object;
    private String content;

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
