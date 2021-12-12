package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ws.spring.convert.pojo.NumberWrapper;
import ws.spring.convert.pojo.User;

import java.util.Locale;

/**
 * 格式转换器生效于Controller，由于{@link Formatter}具有转换器的功能，所以在控制层中，本身也可以映射进行参数转换，
 * 如{@link ws.spring.convert.formatter.UserFormatter}
 * <p>
 * @author WindShadow
 * @date 2021-11-24.
 */

@Slf4j
@Controller
@RequestMapping("/formatter")
public class FormatterController {

    //-------------------------------
    // Formatter 的转换器效果
    //-------------------------------

    /**
     * @return User 不会被格式化，而是json序列化
     * @see ws.spring.convert.formatter.UserFormatter#print(User, Locale)
     *
     */
    @GetMapping("/user-response")
    @ResponseBody
    public User formatUserWhenResponse() {

        User user = new User(100L,"tom","123@qq.com");
        log.info("user: {}",user);
        return user;
    }

    /**
     * Get参数(String类型)直接解析为{@link User}，指定参数名
     * <p>/formatter/user-request-query-assign?user=100-tom-123qq.com
     * <p>但无法进行{@link Validated}校验
     * @param user user
     * @return String
     * @see ws.spring.convert.formatter.UserFormatter#parse(String, Locale)
     */
    @GetMapping("/user-request-query-assign")
    @ResponseBody
    public String formatUserWhenQueryParamAssign(@Validated @RequestParam("user") User user) {

        log.info("user: {}",user);
        return String.valueOf(user);
    }

    /**
     * Get参数(String类型)直接解析为{@link User}，不指定参数名
     * <p>/formatter/user-request-query-non-assign?user=100-tom-123qq.com
     * <p>可以进行{@link Validated}校验
     * @param user user
     * @return String
     * @see ws.spring.convert.formatter.UserFormatter#parse(String, Locale)
     */
    @GetMapping("/user-request-query-non-assign")
    @ResponseBody
    public String formatUserWhenQueryParamNonAssign(@Validated User user) {

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
    @ResponseBody
    public String userWhenRequestBody(@Validated @RequestBody User user) {

        log.info("user: {}",user);
        return String.valueOf(user);
    }

    //-------------------------------
    // Formatter 真正的用途，格式化
    //-------------------------------

    /**
     * 在jsp视图中格式化，在前后端分离模式下，很少用了
     * @param model
     * @return
     */
    @RequestMapping("/number-print")
    public String numberWrapperPrint(Model model) {

        NumberWrapper wrapper = new NumberWrapper(123,456L);
        log.info("wrapper: {}",wrapper);
        model.addAttribute("wrapper",wrapper);
        return "show";
    }

    /**
     * 在参数绑定时解析参数到注解上的属性
     * <p> /formatter/number-parse?code=1-2-3&number=4=5=6 即 /formatter/number-parse?code=1-2-3&number=4%3D5%3D6
     * @param wrapper
     * @return
     */
    @GetMapping("/number-parse")
    @ResponseBody
    public String numberWrapperParse(NumberWrapper wrapper) {

        log.info("wrapper: {}",wrapper);
        return String.valueOf(wrapper);
    }
}
