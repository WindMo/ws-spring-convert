package ws.spring.convert.util;

import org.springframework.context.MessageSource;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author WindShadow
 * @version 2021-11-14.
 */

public class ValidateUtils {

    /** 国际化消息转换对象 */
    private static MessageSource messageSource;
    public static synchronized void setMessageSource(MessageSource messageSource) {

        Assert.notNull(messageSource,"messageSource is null");
        Assert.isNull(ValidateUtils.messageSource,"ValidateUtils.messageSource is not null");
        ValidateUtils.messageSource = messageSource;
    }

    private static final MessageFormat CONSTRAINT_VIOLATION_FAILED_MESSAGE_FORMAT = new MessageFormat("校验属性：{0}，校验值：{1}，失败原因：{2}");

    public static String cleanUpBindingResult(BindingResult bindingResult) {

        return bindingResult
                .getAllErrors()
                .stream()
                // 错误信息国际化
                .map(e -> e.getObjectName() + ": " + messageSource.getMessage(e.getCode(),e.getArguments(),e.getDefaultMessage(), Locale.CHINA))
                .collect(Collectors.joining(";"));
    }

    public static String cleanUpBindingResult(Set<ConstraintViolation<?>> constraintValidateResult) {

        return constraintValidateResult
                .stream()
                .map(cv -> CONSTRAINT_VIOLATION_FAILED_MESSAGE_FORMAT.format(new Object[]{
                        cv.getPropertyPath().toString(),
                        cv.getInvalidValue().toString(),
                        cv.getMessage()}))
                .collect(Collectors.joining(";"));
    }

    public static <T> String cleanUpBindingResultByGeneric(Set<ConstraintViolation<T>> constraintValidateResult) {

        return constraintValidateResult
                .stream()
                .map(cv -> CONSTRAINT_VIOLATION_FAILED_MESSAGE_FORMAT.format(new Object[]{
                        cv.getPropertyPath().toString(),
                        cv.getInvalidValue().toString(),
                        cv.getMessage()}))
                .collect(Collectors.joining(";"));
    }
}
