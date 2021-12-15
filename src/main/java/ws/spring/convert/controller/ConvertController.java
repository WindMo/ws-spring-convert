package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ws.spring.convert.converter.CatConverter;
import ws.spring.convert.pojo.*;

/**
 * 数据转换器生效于Controller
 * <p>
 * 添加到IOC的{@link Converter}会被SpringMVC的{@link ConversionService}管理，添加到IOC的{@link ConverterFactory}不会被MVC的{@link ConversionService}管理，
 * 所以一般使用{@link WebMvcConfigurer#addFormatters(FormatterRegistry)}配置转换器和格式化器时。
 * <p>
 * 在SpringMVC控制层使用{@link RequestParam}注解指定参数名称时，表示将此参数（一般是String类型）转为对应的java类，
 * 即直接使用SpringMVC的{@link ConversionService}完成转换，从而不经过数据校验阶段。
 * <p>若不使用{@link RequestParam}注解指定参数名称，则由SpringMVC完成兜底参数映射，MVC使用它的{@link ConversionService}完成转换，
 * 或属性填充绑定方式完成映射后，会经过参数校验阶段，数据可以被校验。
 *
 * @author WindShadow
 * @date 2021-11-22.
 */

@Slf4j
@RestController
public class ConvertController {

    /**
     * get请求string参数转{@link Town}，使用{@link RequestParam}指定参数名
     * <p>/converter/assign?town=1001-风雷镇
     * <p>但是无法进行数据校验，见{@link CustomControllerAdvice#initBinder(WebDataBinder)}
     * @param town Get请求的String参数
     * @return String
     * @see ws.spring.convert.converter.TownConverter 加入IOC之后控制层直接可以进行String到{@link Town}的转换
     */
    @GetMapping("/converter/assign")
    public String stringToTown(@RequestParam("town") @Validated Town town) {

        log.info("town: {}",town);
        return String.valueOf(town);
    }

    /**
     * get请求string参数转{@link Town}，不指定参数名
     * <p>/converter/non-assign?town=1001-风雷镇
     * <p>可以进行数据校验
     *
     * @param town Get请求的String参数
     * @return String
     */
    @GetMapping("/converter/non-assign")
    public String stringToTown2(@Validated Town town) {

        log.info("town: {}",town);
        return String.valueOf(town);
    }

    /**
     * get请求string参数转{@link Province}
     * <p>/converter-factory/province?province=江苏省
     * <p>使用{@link WebMvcConfigurer#addFormatters(FormatterRegistry)}配置转换器才能完成String参数的转换
     * <p>但{@link Province}只有一个参数，所以表面看不出来转换与否
     * @param province Get请求的String参数
     * @return String
     * @see ws.spring.convert.converter.ProvinceConverterFactory
     */
    @GetMapping("/converter-factory/province")
    public String stringToProvince(@RequestParam("province") Province province) {

        log.info("province: {}",province);
        return String.valueOf(province);
    }

    /**
     * get请求string参数转{@link City}
     * <p>/converter-factory/city?city=江苏省-南京市
     * <p>使用{@link WebMvcConfigurer#addFormatters(FormatterRegistry)}配置转换器才能完成String参数的转换
     * @param city Get请求的String参数
     * @return String
     * @see ws.spring.convert.converter.ProvinceConverterFactory
     */
    @GetMapping("/converter-factory/city")
    public String stringToCity(@RequestParam("city") @Validated City city) {

        log.info("city: {}",city);
        return String.valueOf(city);
    }

    /**
     * get请求string参数转{@link County}
     * <p>/converter-factory/county?county=江苏省-南京市-玄武区
     * <p>使用{@link WebMvcConfigurer#addFormatters(FormatterRegistry)}配置转换器才能完成String参数的转换
     * @param county Get请求的String参数
     * @return String
     * @see ws.spring.convert.converter.ProvinceConverterFactory
     */
    @GetMapping("/converter-factory/county")
    public String stringToCounty(@RequestParam("county") County county) {

        log.info("county: {}",county);
        return String.valueOf(county);
    }

    /**
     * get请求string参数转{@link Cat}
     * <p>/converter-non-ioc?cat=18-tom
     * <p>{@link CatConverter}未加入IOC，不被SpringMVC的{@link ConversionService}管理，理论上无法转换，
     * 但使用{@link WebMvcConfigurer#addFormatters(FormatterRegistry)}配置转换器就可完成String参数的转换，
     * @param cat Get请求的String参数
     * @return String
     * @see ws.spring.convert.converter.CatConverter
     */
    @GetMapping("/converter-non-ioc")
    public String stringToCat(@RequestParam("cat") Cat cat) {

        log.info("cat: {}",cat);
        return String.valueOf(cat);
    }
}
