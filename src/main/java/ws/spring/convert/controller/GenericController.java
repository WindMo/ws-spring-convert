package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.convert.pojo.Town;

/**
 * 普通的Controller
 * @author WindShadow
 * @version 2021-12-05.
 */

@Slf4j
@RestController
public class GenericController {

    /**
     * <code>town</code>参数来自request.body，不走{@link ws.spring.convert.converter.TownConverter}，
     * 所以这个{@link Town}对象的创建由SpringMVC兜底处理，此参数对应的{@link org.springframework.web.bind.WebDataBinder}
     * 该有的配置它都有，如validator校验器，在{@link GenericControllerAdvice#initBinder(WebDataBinder)}得以可以知晓，
     * 故此时此方法中<code>town</code>参数的{@link Validated}注解的校验可以被触发
     * @param town town
     * @return town
     */
    @PostMapping("/gen-body")
    public String genericRequestBody(@Validated @RequestBody Town town) {

        log.info("town: {}",town);
        return String.valueOf(town);
    }
}
