package com.haki.l4d2.manage.common.handler;

import com.haki.l4d2.manage.common.Exception.CustomException;
import com.haki.l4d2.manage.common.pojo.ResultBean;
import com.haki.l4d2.manage.util.exception.IllegalException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:haki
 * @DATE:2019/5/12
 * @Description:全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 捕获自定的异常，并返回出去
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResultBean customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        CustomException exception = (CustomException) e;
        return ResultBean.toFail(exception.getCode(), exception.getMessage());
    }

    /**
     * 捕获逻辑异常，并返回出去
     * @param request
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(IllegalException.class)
    public ResultBean illegalExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse
            response){
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        IllegalException exception = (IllegalException)e;
        return ResultBean.toFail(ResultBean.LEGAL_ERROR,exception.getMessage());
    }




    /**
     * 捕获  RuntimeException 异常
     * TODO  如果你觉得在一个 exceptionHandler 通过  if (e instanceof xxxException) 太麻烦
     * TODO  那么你还可以自己写多个不同的 exceptionHandler 处理不同异常
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultBean runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException exception = (RuntimeException) e;
        return ResultBean.toFail(400, exception.getMessage());
    }



    /**
     *  配置通用异常
     * @param ex
     * @param body
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            return new ResponseEntity<>(ResultBean.toFail(status.value(), exception.getBindingResult().getAllErrors().get
                    (0).getDefaultMessage()), status);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());
            return new ResponseEntity<>(ResultBean.toFail(status.value(), "参数转换失败"), status);
        }

        return new ResponseEntity<>(ResultBean.toFail(status.value(), "参数转换失败"), status);
    }
}
