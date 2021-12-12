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
    private UserFormatter userFormatter;

    @Autowired
    @Setter
    private Set<Converter<?,?>> converterSet;

    @Autowired
    @Setter
    private Set<ConverterFactory<?,?>> converterFactorySet;

    @Override
    public void registerFormatters(FormatterRegistry registry) {

        converterSet.forEach(registry::addConverter);
        converterFactorySet.forEach(registry::addConverterFactory);

        registry.addFormatter(userFormatter);
        registry.addConverter(new CatConverter());
        // 注解格式化器
        registry.addFormatterForFieldAnnotation(numberSeparateAnnotationFormatterFactory);
    }
}
