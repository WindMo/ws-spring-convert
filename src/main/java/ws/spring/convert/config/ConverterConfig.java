package ws.spring.convert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import ws.spring.convert.converter.AdministrativeRegionConverterFactory;
import ws.spring.convert.converter.CityConverter;

import java.util.HashSet;
import java.util.Set;

/**
 * 转换器{@link Converter}和转换器工厂{@link ConverterFactory}配置
 * @author WindShadow
 * @date 2021-11-21.
 */

@Configuration
public class ConverterConfig {

    @Bean
    public ConversionServiceFactoryBean conversionService() {

        Set<Object> converters = new HashSet<>();
        converters.add(new CityConverter());
        converters.add(new AdministrativeRegionConverterFactory());

        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
        conversionServiceFactoryBean.setConverters(converters);
        return conversionServiceFactoryBean;
    }
}
