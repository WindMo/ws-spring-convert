package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.convert.pojo.City;
import ws.spring.convert.pojo.County;
import ws.spring.convert.pojo.Province;
import ws.spring.convert.pojo.Town;

/**
 * 数据转换器生效于Controller
 * @author WindShadow
 * @date 2021-11-22.
 */

@Slf4j
@RestController
public class ConvertController {

    /**
     * get请求string参数转{@link Town}
     * <p>但是跳过了数据校验{@link CustomControllerAdvice#initBinder(WebDataBinder)}
     * @param town Get请求的String参数
     * @return String
     * @see ws.spring.convert.converter.TownConverter 加入IOC之后控制层直接可以进行String到{@link Town}的转换
     */
    @GetMapping("/converter")
    public String stringToTown(@Validated @RequestParam("town") Town town) {

        log.info("town: {}",town);
        return String.valueOf(town);
    }

    /**
     * @param province
     * @return
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
     * @param city
     * @return
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
     * @param county
     * @return
     * @deprecated 无法转换
     * @see ws.spring.convert.converter.ProvinceConverterFactory
     */
    @GetMapping("/converter-factory/county")
    @Deprecated
    public String stringToCounty(@RequestParam("county") County county) {

        log.info("county: {}",county);
        return String.valueOf(county);
    }
}
