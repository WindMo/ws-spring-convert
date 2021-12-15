package ws.spring.convert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import ws.spring.convert.converter.CatConverter;
import ws.spring.convert.converter.CustomFormatterRegistrar;

import java.util.HashSet;
import java.util.Set;

/**
 * 转换器{@link Converter}和转换器工厂{@link ConverterFactory}配置
 * <p>注册所有的转换器和转换器工厂，bean名称为“conversionService”，因为Spring会使用名为“conversionService”的{@link ConversionService}bean，
 * 见{@link ConfigurableApplicationContext#CONVERSION_SERVICE_BEAN_NAME}
 * <p>Spring在注入bean属性时，优先使用名为“conversionService”的{@link ConversionService}将配置文件内的字符串转换为bean
 * <p>如果想一个配置搞定，可以使用{@link FormattingConversionServiceFactoryBean}代替“conversionService”，统一管理{@link Converter}和{@link Formatter}，
 * 如{@link FormatterConfig#formattingConversionService(CustomFormatterRegistrar)}
 * @author WindShadow
 * @version 2021-11-21.
 */

@Configuration
public class ConverterConfig {

    /**
     *
     * @param converterSet 所有的转换器
     * @param converterFactorySet 所有的转换器工厂
     * @return {@link ConversionServiceFactoryBean}
     */
    @Bean("conversionService")
    public ConversionServiceFactoryBean conversionService(
            @Autowired Set<Converter<?,?>> converterSet,
            @Autowired Set<ConverterFactory<?,?>> converterFactorySet) {

        Set<Object> converters = new HashSet<>();
        converters.addAll(converterSet);
        converters.addAll(converterFactorySet);
        converters.add(new CatConverter());
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean;
    }
}
