package ws.spring.convert.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import ws.spring.convert.dto.AdministrativeRegion;
import ws.spring.convert.dto.County;
import ws.spring.convert.dto.Province;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 转换器工厂；1 -> N 的转换
 * 以{@link AdministrativeRegion}与其子类为例
 * @author WindShadow
 * @date 2021-11-21.
 */

public class AdministrativeRegionConverterFactory implements ConverterFactory<String, AdministrativeRegion> {

    private Map<Class<? extends AdministrativeRegion>,Converter<String,? extends AdministrativeRegion>> converterMap;

    public AdministrativeRegionConverterFactory() {
        this.converterMap = getConverterMap();
    }

    @Override
    public <T extends AdministrativeRegion> Converter<String, T> getConverter(Class<T> targetType) {

        Converter<String, ? extends AdministrativeRegion> converter = converterMap.get(targetType);
        return (Converter<String, T>) converter;
    }

    protected Map<Class<? extends AdministrativeRegion>,Converter<String,? extends AdministrativeRegion>> getConverterMap() {

        Map<Class<? extends AdministrativeRegion>,Converter<String,? extends AdministrativeRegion>> converterMap = new HashMap<>();

        final Pattern administrativeRegionPprovincePattern = Pattern.compile("[a-zA-Z\\u4e00-\\u9fa5]{1,}");
        converterMap.put(AdministrativeRegion.class,source -> {

            if (administrativeRegionPprovincePattern.matcher(source).matches()) {
                return new AdministrativeRegion(source);
            }
            throw new IllegalArgumentException("Format like: \"江苏省\"");
        });

        final Pattern provincePattern = Pattern.compile("[a-zA-Z\\u4e00-\\u9fa5]{1,}-[a-zA-Z\\u4e00-\\u9fa5]{1,}");
        converterMap.put(Province.class, source -> {

            if (provincePattern.matcher(source).matches()) {

                String[] params = source.split("-");
                return new Province(params[0],params[1]);
            }
            throw new IllegalArgumentException("Format like: \"江苏省-南京市\"");
        });

        final Pattern countyPattern = Pattern.compile("[a-zA-Z\\u4e00-\\u9fa5]{1,}-[a-zA-Z\\u4e00-\\u9fa5]{1,}-[a-zA-Z\\u4e00-\\u9fa5]{1,}");
        converterMap.put(County.class, source -> {

            if (countyPattern.matcher(source).matches()) {

                String[] params = source.split("-");
                return new County(params[0],params[1],params[2]);
            }
            throw new IllegalArgumentException("Format like: \"江苏省-南京市-江宁区\"");
        });
        return converterMap;
    }
}
