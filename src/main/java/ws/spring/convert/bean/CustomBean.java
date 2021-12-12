package ws.spring.convert.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import ws.spring.convert.pojo.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

/**
 * 利用注册的{@link org.springframework.core.convert.converter.Converter} 进行数据转换，直接进行注入
 * <p>{@link CustomBean#town}依旧可使用校验功能
 * @author WindShadow
 * @date 2021-11-21.
 */

@Validated
@ConfigurationProperties("custom.bean")
@Component
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomBean {

    @Valid
    private Town town;
    private Province province;
    private City city;
    private County county;
    private Student student;
    private Cat cat;

    @PostConstruct
    public void init() {

        log.info("town: {}", town);
        log.info("province: {}",province);
        log.info("city: {}",city);
        log.info("county: {}",county);
        log.info("student: {}",student);
        log.info("cat: {}",cat);
    }
}
