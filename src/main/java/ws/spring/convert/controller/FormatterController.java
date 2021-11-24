package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.convert.dto.User;

/**
 * 格式转换器生效于Controller
 * @author WindShadow
 * @date 2021-11-24.
 */

@Slf4j
@RestController
@RequestMapping("formatter/")
public class FormatterController {

    @GetMapping("/user")
    public User formatUser() {

        User user = new User();
        log.info("user: {}",user);
        return user;
    }
}
