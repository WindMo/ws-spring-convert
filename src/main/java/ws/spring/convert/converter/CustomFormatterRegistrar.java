package ws.spring.convert.converter;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import ws.spring.convert.formatter.UserFormatter;
import ws.spring.convert.formatter.annoation.NumberSeparateAnnotationFormatterFactory;

import java.util.Set;

/**
 * {@link FormatterRegistrar}可以进行{@link Converter}和{@link Formatter}的分类
 * @author WindShadow
 * @version 2021-12-12.
 */

@Component
public class CustomFormatterRegistrar implements FormatterRegistrar {

    @Autowired
    @Setter
    private NumberSeparateAnnotationFormatterFactory numberSeparateAnnotationFormatterFactory;

    @Autowired
    @Setter
    @Deprecated
    private UserFormatter userFormatter;

    @Autowired
    @Setter
    @Deprecated
    private Set<Converter<?,?>> converterSet;

    @Autowired
    @Setter
    private Set<ConverterFactory<?,?>> converterFactorySet;

    @Override
    public void registerFormatters(FormatterRegistry registry) {

//        converterSet.forEach(registry::addConverter); // 不需要注册 Converter，mvc自动从IOC中获取并配置
        converterFactorySet.forEach(registry::addConverterFactory); // Converter工厂需要自己注册

//        registry.addFormatter(userFormatter);  // 不需要注册 Formatter，mvc自动从IOC中获取并配置
        registry.addConverter(new CatConverter());
        // 注解格式化器
        registry.addFormatterForFieldAnnotation(numberSeparateAnnotationFormatterFactory);
    }
}
