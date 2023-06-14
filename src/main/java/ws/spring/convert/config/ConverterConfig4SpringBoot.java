package ws.spring.convert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import ws.spring.convert.converter.CatConverter;

import java.util.Set;

/**
 * SpringBoot下，用 ApplicationConversionService 代替 ConversionServiceFactoryBean
 * @author WindShadow
 * @version 2021-11-21.
 */

//@Configuration
public class ConverterConfig4SpringBoot {

    /**
     *
     * @param converterSet 所有的转换器
     * @param converterFactorySet 所有的转换器工厂
     * @return {@link ConversionServiceFactoryBean}
     */
    @Bean("conversionService")
    public ApplicationConversionService conversionService(
            @Autowired Set<Converter<?,?>> converterSet,
            @Autowired Set<ConverterFactory<?,?>> converterFactorySet) {

        ApplicationConversionService service = new ApplicationConversionService();
        service.addConverter(new CatConverter());
        converterSet.forEach(service::addConverter);
        converterFactorySet.forEach(service::addConverterFactory);
        return service;
    }
}
