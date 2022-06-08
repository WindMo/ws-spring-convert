package ws.spring.convert.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import ws.spring.convert.converter.CustomFormatterRegistrar;

import java.util.Collections;
import java.util.Locale;

/**
 * 如果想在项目业务代码中使用格式化器，可以配置{@link FormattingConversionServiceFactoryBean}到IOC中，即可注入{@link FormattingConversionService}bean
 * @author WindShadow
 * @date 2021-11-22.
 */

//@Configuration
public class FormatterConfig {

    /**
     * 注册格式化器
     * <p>{@link Formatter}格式化器一般用于应用层（如web环境）中，根据不同的地区{@link java.util.Locale}调用{@link Formatter#print(Object, Locale)}方法
     * 得到对应格式化之后的字符串。同时用{@link Formatter#parse(String, Locale)}方法完成反向解析
     * <p>{@link FormattingConversionService}继承{@link GenericConversionService}
     * 实现了{@link FormatterRegistry}注册器。所以 FormattingConversionService 本身也是一个管理转换器的{@link ConversionService}。
     * <p>阅读{@link FormattingConversionService#addFormatter(Formatter)}等添加格式化器方法
     * 和{@link FormattingConversionService#addFormatterForFieldType(Class, Formatter)}方法的实现，
     * 不难发现{@link FormattingConversionService}通过实现{@link GenericConverter}接口的内部类
     * PrinterConverter 和 ParserConverter 维护{@link Formatter}格式化器，然后作为转换器注册到父类{@link ConversionService}中，
     * 完美复用 Converter 的功能进行适配增强
     *
     * @param customFormatterRegistrar 格式化器记录器
     * @return 格式化器注册服务的工厂bean {@link FormattingConversionServiceFactoryBean}
     * @see FormattingConversionServiceFactoryBean#getObject()
     * @see org.springframework.format.support.FormattingConversionService
     * @see org.springframework.format.FormatterRegistry
     * @see org.springframework.core.convert.support.GenericConversionService
     */
//    @Bean(value = {"formatterRegistrars","conversionService"}) // 此种方式可以统一管理Converter和Formatter
//    @Bean(value = {"formatterRegistrars"})
    public FormattingConversionServiceFactoryBean formattingConversionService(@Autowired CustomFormatterRegistrar customFormatterRegistrar) {

        FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean = new FormattingConversionServiceFactoryBean();
        formattingConversionServiceFactoryBean.setFormatterRegistrars(Collections.singleton(customFormatterRegistrar));
        return formattingConversionServiceFactoryBean;
    }
}
