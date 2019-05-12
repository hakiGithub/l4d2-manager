package com.haki.l4d2.manage.common.Exception;

import lombok.Data;

/**
 * @Author:haki
 * @DATE:2019/5/12
 * @Description:自定义的异常
 */
@Data
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = -431872626849612416L;

    private int code;

    public CustomException(){
        super();
    }

    public CustomException(int code,String message){
        super(message);
        this.setCode(code);
    }


}
