package ws.spring.convert.formatter.annoation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author WindShadow
 * @version 2021-12-11.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NumberSeparate {

    @AliasFor("value")
    char separator() default '-';

    @AliasFor("separator")
    char value() default '-';
}
