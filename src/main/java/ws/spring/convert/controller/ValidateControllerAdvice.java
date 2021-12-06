package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ws.spring.convert.util.ValidateUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * @author WindShadow
 * @version 2021-11-14.
 */

@Slf4j
@RestControllerAdvice
public class ValidateControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 绑定错误、校验失败、参数映射（转换）失败，应该给予 400 状态码
    @ExceptionHandler(value = {
            MethodArgumentNotValidException.class,// @RequestBody 参数映射验证失败抛出此类型异常
            BindException.class,// get、post等参数自动绑定到bean的请求方式时，验证失败抛出此类型异常
            ConstraintViolationException.class//  @RequestParam 参数映射或校验失败抛出此类型异常
    })
    public Object validatedErrEx(Exception e) {

        String exType;
        String detail;
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {

            exType = "MethodArgumentNotValidException";
            bindingResult = ((MethodArgumentNotValidException)e).getBindingResult();
            detail = ValidateUtils.cleanUpBindingResult(bindingResult);
        } else if (e instanceof BindException) {

            exType = "BindException";
            bindingResult = ((BindException)e).getBindingResult();
            detail = ValidateUtils.cleanUpBindingResult(bindingResult);
        }else if (e instanceof ConstraintViolationException) {

            exType = "ConstraintViolationException";
            Set<ConstraintViolation<?>> constraintViolationSet  = ((ConstraintViolationException)e).getConstraintViolations();
            detail = ValidateUtils.cleanUpBindingResult(constraintViolationSet);
        }else {
            // @ExceptionHandler注解限定了异常类型，此句不可能被执行到
            throw new RuntimeException("未知异常");
        }
        log.info("捕获到异常：{},detail: {}",exType,detail);
        return "捕获到异常：" + exType +" " + detail;
    }
}
