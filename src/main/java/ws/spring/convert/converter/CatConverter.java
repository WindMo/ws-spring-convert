package ws.spring.convert.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import ws.spring.convert.pojo.Cat;

import java.util.regex.Pattern;

/**
 * 不加入IOC的Converter
 * @author WindShadow
 * @version 2021-12-10.
 */

@Slf4j
public class CatConverter implements Converter<String, Cat> {

    private static final String LINE = "-";
    private static final Pattern TOWN_PATTERN = Pattern.compile("[0-9]{1,}" + LINE + "[a-zA-Z\\u4e00-\\u9fa5]{1,}");

    @Override
    public Cat convert(String source) {

        log.info("CatConverter >>> {}",source);
        if (TOWN_PATTERN.matcher(source).matches()) {

            // params.length == 2
            String[] params = source.split(LINE);
            Integer age = Integer.valueOf(params[0]);
            String name = params[1];
            return new Cat(name,age);
        }
        throw new IllegalArgumentException("The string<" + source + "> format is incorrect, like: \"18-tom\"");
    }
}
