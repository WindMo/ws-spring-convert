package ws.spring.convert.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ws.spring.convert.pojo.User;
import java.text.ParseException;
import java.util.Locale;

/**
 * {@link org.springframework.format.FormatterRegistry}
 * {@link User}的格式化器
 * @author WindShadow
 * @date 2021-11-22.
 */
// https://blog.csdn.net/f641385712/article/details/111824522?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522163758397016780366520045%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=163758397016780366520045&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_v2~rank_v29-3-111824522.pc_v2_rank_blog_default&utm_term=convert&spm=1018.2226.3001.4450
@Slf4j
@Component
public class UserFormatter implements Formatter<User> {

    @Override
    public User parse(String text, Locale locale) throws ParseException {

        log.info("parse: {}", text);
        String[] fields = text.split("-");
        return new User(Long.valueOf(fields[0]),fields[1],fields[2]);
    }

    @Override
    public String print(User user, Locale locale) {

        log.info("print: {}", user);
        String userText = user.getId() + "-" + user.getName() + "-" + user.getEmail();
        return userText;
    }
}
