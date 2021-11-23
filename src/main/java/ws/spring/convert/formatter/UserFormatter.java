package ws.spring.convert.formatter;

import org.springframework.format.Formatter;
import ws.spring.convert.dto.User;

import java.text.ParseException;
import java.util.Locale;

/**
 * {@link User}的格式转换器
 * @author WindShadow
 * @date 2021-11-22.
 */

public class UserFormatter implements Formatter<User> {

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(User object, Locale locale) {
        return null;
    }
}
