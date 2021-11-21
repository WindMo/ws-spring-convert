package ws.spring.convert.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ws.spring.convert.dto.AdministrativeRegion;
import ws.spring.convert.dto.City;
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

    private City city;
    private AdministrativeRegion region;
    private Province province;
    private County county;

    @PostConstruct
    public void init() {

        log.info("city: {}",city);
        log.info("region: {}",region);
        log.info("province: {}",province);
        log.info("county: {}",county);
    }
}
