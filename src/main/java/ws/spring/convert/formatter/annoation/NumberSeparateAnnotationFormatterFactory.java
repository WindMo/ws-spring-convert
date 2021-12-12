package ws.spring.convert.formatter.annoation;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.*;
import java.util.function.Function;

/**
 * 将数字分割的格式化工厂，如 123 -> 1-2-3
 * @author WindShadow
 * @version 2021-12-11.
 */

@Component
public class NumberSeparateAnnotationFormatterFactory implements AnnotationFormatterFactory<NumberSeparate> {

    /**
     * @return 支持的类型
     */
    @Override
    public Set<Class<?>> getFieldTypes() {

        return new HashSet<>(Arrays.asList(Integer.class,Long.class));
    }

    @Override
    public Printer<?> getPrinter(NumberSeparate annotation, Class<?> fieldType) {

        char separator = annotation.separator();
        return getFormatter(separator,fieldType);
    }

    @Override
    public Parser<?> getParser(NumberSeparate annotation, Class<?> fieldType) {

        char separator = annotation.separator();
        return getFormatter(separator,fieldType);
    }

    private static Formatter<?> getFormatter(char separator, Class<?> clazz) {

        if (Integer.class.equals(clazz)) {

            return new NumberSeparateFormatter<>(separator, Integer::valueOf);
        } else if (Long.class.equals(clazz)) {

            return new NumberSeparateFormatter<>(separator, Long::valueOf);
        } else {

            throw new IllegalArgumentException("不支持的类型");
        }
    }

    private static class NumberSeparateFormatter<T extends Number> implements Formatter<T> {

        private final char separator;
        private final Function<String,T> converter;

        private NumberSeparateFormatter(char separator, Function<String,T> converter) {
            this.separator = separator;
            this.converter = converter;
        }

        @Override
        public String print(T object, Locale locale) {

            char[] chars = object.toString().toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char aChar : chars) {
                sb.append(aChar).append(separator);
            }
            return sb.substring(0,sb.length() - 1);
        }

        @Override
        public T parse(String text, Locale locale) throws ParseException {

            String replace = text.replace(String.valueOf(separator), "");
            try {
                return converter.apply(replace);
            } catch (NumberFormatException e) {
                throw new ParseException("格式错误",0);
            }
        }
    }
}
