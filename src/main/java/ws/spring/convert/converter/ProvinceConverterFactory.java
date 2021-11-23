package ws.spring.convert.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import ws.spring.convert.dto.City;
import ws.spring.convert.dto.County;
import ws.spring.convert.dto.Province;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 转换器工厂；1 -> N 的转换
 * 以{@link Province}与其子类为例
 * 加入IOC
 * @author WindShadow
 * @date 2021-11-21.
 * @see ws.spring.convert.bean.CustomBean#setProvince(Province)
 * @see ws.spring.convert.bean.CustomBean#setCity(City)
 * @see ws.spring.convert.bean.CustomBean#setCounty(County) Spring在装载bean的属性时使用{@link ConverterFactory}提供的{@link Converter}
 * @see ws.spring.convert.config.ConverterConfig#conversionService(Set, Set) 通过方法参数依赖注入获取全部的{@link ConverterFactory}bean，注册到{@link  org.springframework.context.support.ConversionServiceFactoryBean}中
 */

@Slf4j
@Component
public class ProvinceConverterFactory implements ConverterFactory<String, Province> {

    private Map<Class<? extends Province>,Converter<String,? extends Province>> converterMap;

    public ProvinceConverterFactory() {
        this.converterMap = getConverterMap();
    }

    @Override
    public <T extends Province> Converter<String, T> getConverter(Class<T> targetType) {

        Converter<String, ? extends Province> converter = converterMap.get(targetType);
        return (Converter<String, T>) converter;
    }

    protected Map<Class<? extends Province>,Converter<String,? extends Province>> getConverterMap() {

        Map<Class<? extends Province>,Converter<String,? extends Province>> converterMap = new HashMap<>();

        final Pattern provincePattern = Pattern.compile("[a-zA-Z\\u4e00-\\u9fa5]{1,}");
        converterMap.put(Province.class,source -> {

            log.info("ProvinceConverterFactory#ProvinceConverter >>> {}",source);
            if (provincePattern.matcher(source).matches()) {
                return new Province(source);
            }
            throw new IllegalArgumentException("The source is <"+ source + ">, Format like: \"江苏省\"");
        });

        final Pattern cityPattern = Pattern.compile("[a-zA-Z\\u4e00-\\u9fa5]{1,}-[a-zA-Z\\u4e00-\\u9fa5]{1,}");
        converterMap.put(City.class, source -> {

            log.info("ProvinceConverterFactory#CityConverter >>> {}",source);
            if (cityPattern.matcher(source).matches()) {

                String[] params = source.split("-");
                return new City(params[0],params[1]);
            }
            throw new IllegalArgumentException("The source is <"+ source + ">, Format like: \"江苏省-南京市\"");
        });

        final Pattern countyPattern = Pattern.compile("[a-zA-Z\\u4e00-\\u9fa5]{1,}-[a-zA-Z\\u4e00-\\u9fa5]{1,}-[a-zA-Z\\u4e00-\\u9fa5]{1,}");
        converterMap.put(County.class, source -> {

            log.info("ProvinceConverterFactory#CountyConverter >>> {}",source);
            if (countyPattern.matcher(source).matches()) {

                String[] params = source.split("-");
                return new County(params[0],params[1],params[2]);
            }
            throw new IllegalArgumentException("The source is <"+ source + ">, Format like: \"江苏省-南京市-江宁区\"");
        });
        return converterMap;
    }
}
