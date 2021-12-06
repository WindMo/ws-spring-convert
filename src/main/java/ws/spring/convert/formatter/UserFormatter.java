package ws.spring.convert.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ws.spring.convert.pojo.User;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * {@link org.springframework.format.FormatterRegistry}
 * {@link User}的格式化器
 * @author WindShadow
 * @date 2021-11-22.
 */

@Slf4j
@Component
public class UserFormatter implements Formatter<User> {

    /** like: "id-name-email" */
    private static final Pattern USER_STRING_PATTERN = Pattern.compile("[0-9]{1,}-[a-zA-Z\\u4e00-\\u9fa5]{1,}-\\S*");

    @Override
    public User parse(String text, Locale locale) throws ParseException {

        log.info("parse: {}", text);
        if (!USER_STRING_PATTERN.matcher(text).matches()) {
            throw new ParseException("The value [" + text + "] is not matcher format <id-name-email>",0);
        }
        String[] fields = text.split("-");
        return new User(Long.valueOf(fields[0]),fields[1],fields[2]);
    }

    @Override
    public String print(User user, Locale locale) {

        log.info("print: {}", user);
        return user.getId() + "-" + user.getName() + "-" + user.getEmail();
    }
}
