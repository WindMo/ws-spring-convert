package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.convert.dto.City;
import ws.spring.convert.dto.County;
import ws.spring.convert.dto.Province;
import ws.spring.convert.dto.Town;

/**
 * 数据转换器生效与Controller
 * @author WindShadow
 * @date 2021-11-22.
 */

@Slf4j
@RestController
//@RequestMapping("/converter")
public class ConvertController {

    /**
     * get请求string参数转{@link Town}
     * @param town
     * @return
     * @see ws.spring.convert.converter.TownConverter 加入IOC之后控制层直接可以进行String到{@link Town}的转换
     */
    @GetMapping("/converter")
    public String stringToTown(@RequestParam("town") Town town) {

        log.info("town: {}",town);
        return String.valueOf(town);
    }

    @GetMapping("/converter-factory/province")
    public String stringToProvince(@RequestParam("province") Province province) {

        log.info("province: {}",province);
        return String.valueOf(province);
    }

    @GetMapping("/converter-factory/city")
    public String stringToCity(@RequestParam("city") City city) {

        log.info("city: {}",city);
        return String.valueOf(city);
    }

    @GetMapping("/converter-factory/county")
    public String stringToCounty(@RequestParam("county") County county) {

        log.info("county: {}",county);
        return String.valueOf(county);
    }
}