package ws.spring.convert.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ws.spring.convert.dto.City;
import ws.spring.convert.dto.Town;
import ws.spring.convert.dto.County;
import ws.spring.convert.dto.Province;

import javax.annotation.PostConstruct;

/**
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

    @PostConstruct
    public void init() {

        log.info("town: {}", town);
        log.info("province: {}",province);
        log.info("city: {}",city);
        log.info("county: {}",county);
    }
}
