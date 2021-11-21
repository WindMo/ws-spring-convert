package ws.spring.convert.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import ws.spring.convert.dto.City;
import java.util.regex.Pattern;

/**
 * 转换器；1 -> 1 的转换

 * @author WindShadow
 * @date 2021-3-7.
 */
@Slf4j
public class CityConverter implements Converter<String, City> {

    private static final String LINE = "-";
    private static final Pattern CITY_PATTERN = Pattern.compile("[0-9]{1,}" + LINE + "[a-zA-Z\\u4e00-\\u9fa5]{1,}");

    /**
     * 将 "1001-南京" 转换成{@link City}对象
     * @param source not null
     * @return
     */
    @Override
    public City convert(String source) {

        log.info("CityConverter >>> {}",source);
        if (CITY_PATTERN.matcher(source).matches()) {

            // params.length == 2
            String[] params = source.split(LINE);
            Integer code = Integer.valueOf(params[0]);
            String name = params[1];
            return new City(code,name);
        }
        throw new IllegalArgumentException("The string<" + source + "> format is incorrect, like: \"1001-南京\"");
    }
}
