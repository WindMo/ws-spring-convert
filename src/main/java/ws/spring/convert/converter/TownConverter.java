package ws.spring.convert.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ws.spring.convert.pojo.Town;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * 转换器；1 -> 1 的转换
 * 转换器必须是线程安全的，也就是建议整个应用中相同的转换操作只使用这一个转换器就行
 * <p>
 *     Spring在装载bean的属性时，先从配置文件中获取String类型的配置，再从各个{@link org.springframework.core.convert.ConversionService}bean中获取可用的{@link Converter}，
 *     如果存在可用的转换器，则直接使用该转换器转换得到对应属性的实例，为bean设置属性
 * </p>
 * <p>
 *     SpringMVC在进行参数映射时，会先从IOC中查询对应的【String类型转换为参数类型】的转换器{@link Converter}bean，如果有则直接使用该转换器进行参数绑定
 * </p>
 * 所以实现{@link Converter}时建议注册到IOC成为单例bean，在通过依赖注入将这些{@link Converter}bean注册到{@link org.springframework.core.convert.ConversionService}中
 * @author WindShadow
 * @date 2021-3-7.
 * @see Component 注册为单例bean
 * @see ws.spring.convert.bean.CustomBean#setTown(Town) Spring在装载bean的属性时使用{@link Converter}
 * @see ws.spring.convert.config.ConverterConfig#conversionService(Set, Set) 通过方法参数依赖注入获取全部的{@link Converter}bean，注册到{@link  org.springframework.context.support.ConversionServiceFactoryBean}中
 */
@Slf4j
@Component
public class TownConverter implements Converter<String, Town> {

    private static final String LINE = "-";
    private static final Pattern TOWN_PATTERN = Pattern.compile("[0-9]{1,}" + LINE + "[a-zA-Z\\u4e00-\\u9fa5]{1,}");

    /**
     * 将 "1001-南京" 转换成{@link Town}对象
     * @param source not null
     * @throws IllegalArgumentException 无法转换允许抛出参数错误异常
     * @return
     */
    @Override
    public Town convert(String source) throws IllegalArgumentException {

        log.info("TownConverter >>> {}",source);
        if (TOWN_PATTERN.matcher(source).matches()) {

            // params.length == 2
            String[] params = source.split(LINE);
            Integer code = Integer.valueOf(params[0]);
            String name = params[1];
            return new Town(code,name);
        }
        throw new IllegalArgumentException("The string<" + source + "> format is incorrect, like: \"1001-town\"");
    }
}
