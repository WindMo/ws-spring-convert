package ws.spring.convert.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ws.spring.convert.pojo.User;

import java.util.Locale;

/**
 * 格式转换器生效于Controller
 * @author WindShadow
 * @date 2021-11-24.
 */

@Slf4j
@RestController
@RequestMapping("/formatter")
public class FormatterController {

    @Data
    @AllArgsConstructor
    static class Temp {


        private User user;
    }

    @GetMapping("/user-response")
    public Temp formatUserWhenResponse() {

        User user = new User(100L,"tom","123@qq.com");
        log.info("user: {}",user);
        return new Temp(user);
    }

    /**
     * Get参数(String类型)直接解析为{@link User}，但跳过了{@link Validated}校验
     * @param user user
     * @return String
     * @see ws.spring.convert.formatter.UserFormatter#parse(String, Locale)
     */
    @GetMapping("/user-request-query")
    public String formatUserWhenQueryParam(@Validated @RequestParam("user") User user) {

        log.info("user: {}",user);
        return String.valueOf(user);
    }

    /**
     * RequestBod参数，不能直接将String类型直接解析为{@link User}，这是肯定的，不符合json转换规则，
     * 也不跳过{@link Validated}校验
     * @param user user
     * @return String
     */
    @PostMapping("/user-request-body")
    public String userWhenRequestBody(@Validated @RequestBody User user) {

        log.info("user: {}",user);
        return String.valueOf(user);
    }
}
