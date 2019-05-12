package com.haki.l4d2.manage.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: haki
 * @Date: 2019/5/11 23:34
 * @Description:用于控制层返回信息给前端
 */
@Data
public class ResultBean<T> implements Serializable {

    /**
     * 没有登录
     */
    public static final int NO_LOGIN = -1;
    /**
     * 成功状态
     */
    public static final int SUCCESS = 233;
    /**
     * 失败状态
     */
    public static final int FAIL = 110;
    /**
     * 没有权限
     */
    public static final int NO_PERMISSION = 303;
    /**
     * 系统异常
     */
    public static final int SYSTEM_ERROR = 500;

    /**
     * 逻辑异常
     */
    public static final int LEGAL_ERROR = 101;


    private static final long serialVersionUID = -4532487565293471143L;
    /**
     * 返回的信息
     */
    private String message = "success";
    /**
     * 状态码
     */
    private int code = SUCCESS;

    /**
     * 返回的数据
     */
    private T data;

    private ResultBean() {
        super();
    }

    private ResultBean(T data) {
        super();
        this.data = data;
    }

    private ResultBean(Throwable e) {
        super();
        this.message = e.toString();
        this.code = FAIL;
    }


    private ResultBean(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public static ResultBean toFail(int code, String message) {
        return new ResultBean(code, message);
    }

    public static ResultBean toFail(Throwable e) {
        return new ResultBean(e);
    }

    public static <T> ResultBean<T> success(T data) {
        return new ResultBean(data);
    }


}
