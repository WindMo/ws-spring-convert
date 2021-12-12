package ws.spring.convert.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ws.spring.convert.converter.CustomFormatterRegistrar;

/**
 * @author WindShadow
 * @version 2021-12-10.
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    @Setter
    private CustomFormatterRegistrar customFormatterRegistrar;

    /**
     * 一般格式化更多用于web环境，此处可以为SpringMVC添加格式化器或转换器
     * @param registry 注册器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {

        customFormatterRegistrar.registerFormatters(registry);
    }
}
