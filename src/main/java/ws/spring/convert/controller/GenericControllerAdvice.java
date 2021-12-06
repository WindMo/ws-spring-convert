package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author WindShadow
 * @version 2021-12-05.
 */

@Slf4j
@RestControllerAdvice(assignableTypes = {GenericController.class})
public class GenericControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        log.info("参数名：{} 目标对象：{} 校验器数量：{}",binder.getObjectName(),binder.getTarget(),binder.getValidators().size());
    }
}
