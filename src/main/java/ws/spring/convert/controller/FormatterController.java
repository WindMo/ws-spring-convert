package ws.spring.convert.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.convert.pojo.User;

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

    @GetMapping("/user-request")
    public String formatUserWhenRequest(@RequestParam("user") User user) {

        log.info("user: {}",user);
        return String.valueOf(user);
    }
}
