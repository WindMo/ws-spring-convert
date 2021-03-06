package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ws.spring.convert.config.PropertyEditorConfig;

import java.beans.PropertyEditor;
import java.lang.reflect.Constructor;

/**
 * 自定义了 Converter 或 Formatter 或 PropertyEditor 的类，在Controller上使用时，在此进行增强
 * @author WindShadow
 * @date 2021-11-22.
 */

@Slf4j
@RestControllerAdvice(assignableTypes = {ConvertController.class,FormatterController.class,PropertyEditorController.class})
public class CustomControllerAdvice {


    /**
     * 此时 {@link WebDataBinder#getTarget()} 为 null，即使为其注册校验器也不生效了
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {

        log.info("参数名：{} 目标对象：{} 校验器数量：{}",binder.getObjectName(),binder.getTarget(),binder.getValidators().size());

        // 注册属性编辑器
        registerCustomEditor(binder);
    }


    private void registerCustomEditor(WebDataBinder binder) {

/*
        此种绑定方式看似很好，先找到对应的属性编辑器，再进行注册，但是此时 target 为 null，不知道 target的类型，也就无法“对症下药”

        Object target = binder.getTarget();
        if (target == null) {
            return;
        }
        // 注册对应的属性编辑器到当前的绑定器，如果存在的话
        Class<?> entityClass = target.getClass();
        Class<? extends PropertyEditor> entityPropertyEditorClass = PropertyEditorConfig.getPropertyEditors().get(entityClass);
        if (entityPropertyEditorClass != null) {

            registerCustomEditor(binder,entityClass,entityPropertyEditorClass);
        }
*/
        // 注册全部的属性编辑器到当前的绑定器（比较退而求其次的做法）
        PropertyEditorConfig.getPropertyEditors().forEach((entityClass,entityPropertyEditorClass) -> registerCustomEditor(binder,entityClass,entityPropertyEditorClass));
    }

    private void registerCustomEditor(WebDataBinder binder,Class<?> entityClass, Class<? extends PropertyEditor> entityPropertyEditorClass) {

        try {
            Constructor<? extends PropertyEditor> constructor = entityPropertyEditorClass.getConstructor();
            PropertyEditor editor = constructor.newInstance();
            binder.registerCustomEditor(entityClass,editor);
        } catch (ReflectiveOperationException e) {
            log.error("属性编辑器<{}>没有无参构造方法",entityPropertyEditorClass.getTypeName(),e);
            throw new UnsupportedOperationException(e);
        }
    }
}
