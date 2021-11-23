package ws.spring.convert.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ws.spring.convert.dto.*;

import javax.annotation.PostConstruct;

/**
 * 利用注册的{@link org.springframework.core.convert.converter.Converter} 进行数据转换，直接进行注入
 * @author WindShadow
 * @date 2021-11-21.
 */

@ConfigurationProperties("custom.bean")
@Component
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomBean {

    private Town town;
    private Province province;
    private City city;
    private County county;
    private County countyX;

    private Student student;

    @PostConstruct
    public void init() {

        log.info("town: {}", town);
        log.info("province: {}",province);
        log.info("city: {}",city);
        log.info("county: {}",county);
        log.info("student: {}",student);
    }
}
