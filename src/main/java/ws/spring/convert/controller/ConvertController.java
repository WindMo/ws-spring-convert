package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.convert.pojo.*;

/**
 * 数据转换器生效于Controller
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
    public String stringToTown(@Validated @RequestParam("town") Town town) {

        log.info("town: {}",town);
        return String.valueOf(town);
    }

    /**
     * get请求string参数转{@link Town}，不指定参数名
     * <p>/converter/non-assign?town=1001-风雷镇
     * <p>可以进行数据校验
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
     * @param province Get请求的String参数
     * @return String
     * @deprecated 无法转换
     * @see ws.spring.convert.converter.ProvinceConverterFactory
     */
    @GetMapping("/converter-factory/province")
    @Deprecated
    public String stringToProvince(@RequestParam("province") Province province) {

        log.info("province: {}",province);
        return String.valueOf(province);
    }

    /**
     * get请求string参数转{@link City}
     * <p>/converter-factory/city?city=江苏省-南京市
     * @param city Get请求的String参数
     * @return String
     * @deprecated 无法转换
     * @see ws.spring.convert.converter.ProvinceConverterFactory
     */
    @GetMapping("/converter-factory/city")
    @Deprecated
    public String stringToCity(@RequestParam("city") City city) {

        log.info("city: {}",city);
        return String.valueOf(city);
    }

    /**
     * get请求string参数转{@link County}
     * <p>/converter-factory/county?county=江苏省-南京市-玄武区
     * @param county Get请求的String参数
     * @return String
     * @deprecated 无法转换
     * @see ws.spring.convert.converter.ProvinceConverterFactory
     */
    @GetMapping("/converter-factory/county")
    @Deprecated
    public String stringToCounty(@RequestParam("county") County county) {

        log.info("county: {}",county);
        return String.valueOf(county);
    }

    /**
     * get请求string参数转{@link Cat}
     * <p>/converter-non-register?cat=18-tom
     * @param cat Get请求的String参数
     * @return String
     * @deprecated {@link ws.spring.convert.converter.CatConverter}未加入IOC，无法转换
     * @see ws.spring.convert.converter.CatConverter
     */
    @GetMapping("/converter-non-register")
    @Deprecated
    public String stringToCat(@RequestParam("cat") Cat cat) {

        log.info("cat: {}",cat);
        return String.valueOf(cat);
    }
}
