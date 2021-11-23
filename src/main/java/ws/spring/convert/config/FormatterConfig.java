package ws.spring.convert.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import ws.spring.convert.formatter.UserFormatter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author WindShadow
 * @date 2021-11-22.
 */

@Configuration
public class FormatterConfig {

    /**
     *
     * @return
     */
    @Bean
    public FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean() {

        FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean = new FormattingConversionServiceFactoryBean();
        Set<Formatter<?>> formatSet = new HashSet<>();
        formatSet.add(new UserFormatter());
        formattingConversionServiceFactoryBean.setFormatters(formatSet);
        return formattingConversionServiceFactoryBean;
    }
}
