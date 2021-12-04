package ws.spring.convert.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ws.spring.convert.vo.CommercialDesc;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 商品描述{@link CommercialDesc}的格式化器
 * @author WindShadow
 * @version 2021-11-28.
 */

@Component
public class CommercialDescFormatter implements Formatter<CommercialDesc> {

    /** like: "1003=宝马" */
    private static final Pattern CAR_STRING_PATTERN = Pattern.compile("[0-9]{1,}=[a-zA-Z\\u4e00-\\u9fa5]{1,}");

    @Override
    public CommercialDesc parse(String text, Locale locale) throws ParseException {

        if (CAR_STRING_PATTERN.matcher(text).matches()) {

            String[] params = text.split("=");
            return new CommercialDesc(params);
        }
        throw new ParseException("CarString 格式错误",0);
    }

    @Override
    public String print(CommercialDesc object, Locale locale) {

        return String.join("=", object.getDesc());
    }
}
