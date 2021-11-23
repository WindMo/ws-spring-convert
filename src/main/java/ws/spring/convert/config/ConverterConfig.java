package ws.spring.convert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import java.util.HashSet;
import java.util.Set;

/**
 * 转换器{@link Converter}和转换器工厂{@link ConverterFactory}配置
 * @author WindShadow
 * @date 2021-11-21.
 */

@Configuration
public class ConverterConfig {

    /**
     * 注册所有的转换器和转换器工厂
     * @param converterSet
     * @param converterFactorySet
     * @return
     */
    @Bean
    public ConversionServiceFactoryBean conversionService(
            @Autowired Set<Converter<?,?>> converterSet,
            @Autowired Set<ConverterFactory<?,?>> converterFactorySet) {

        Set<Object> converters = new HashSet<>();
        converters.addAll(converterSet);
        converters.addAll(converterFactorySet);
        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean;
    }
}
