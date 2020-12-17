package com.daop.basic.demo.common.aop;

import com.daop.basic.demo.common.enums.ResultEnum;
import com.daop.basic.demo.common.exception.CustomException;
import com.daop.basic.demo.common.utils.ResultVoUtil;
import com.daop.basic.demo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

/**
 * @BelongsProject: demo
 * @BelongsPackage: com.daop.basic.demo.common.aop
 * @Description: 全局异常处理
 * @DATE: 2020-11-25
 * @AUTHOR: Daop
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandling {

    /**
     * 自定义异常处理
     *
     * @param e 自定义异常
     * @return ResultBean
     */
    @ExceptionHandler(value = CustomException.class)
    public ResultVO processException(CustomException e) {
        log.error("Error Position:{} -> Message:{}", e.getMethod(), e.getLocalizedMessage());
        return ResultVoUtil.error(Objects.requireNonNull(ResultEnum.getByCode(e.getCode())));
    }

    /**
     * 拦截表单参数校验
     *
     * @param e 参数校验异常
     * @return ResultBean
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public ResultVO bindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ResultVoUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * JSON 参数校验
     *
     * @param e 方法参数无效异常
     * @return ResultBean
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ResultVoUtil.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /**
     * 方法参数不匹配
     *
     * @param e 方法参数不匹配异常
     * @return ResultBean
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultVO methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("Error Info: {}", e.getLocalizedMessage());
        return ResultVoUtil.error(ResultEnum.FORMAT_ERROR);
    }

    /**
     * 参数格式错误
     *
     * @param e 参数格式错误异常
     * @return ResultBean
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultVO httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("Error Info: {}", e.getLocalizedMessage());
        return ResultVoUtil.error(ResultEnum.FORMAT_ERROR);
    }

    /**
     * 请求方式不支持
     *
     * @param e 请求方式不支持异常
     * @return ResultBean
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultVO httpReqMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("Error Info: {}", e.getLocalizedMessage());
        return ResultVoUtil.error(ResultEnum.REQ_METHOD_NOT_SUPPORT);
    }

    /**
     * 通用异常
     *
     * @param e 异常
     * @return ResultBean
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    public ResultVO exception(Exception e) {
        e.printStackTrace();
        return ResultVoUtil.error(ResultEnum.UNKNOWN_EXCEPTION);
    }
}
