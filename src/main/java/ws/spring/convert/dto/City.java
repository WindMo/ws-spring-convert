package ws.spring.convert.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author WindShadow
 * @date 2021-11-21.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class City extends Province{

    private String cityName;

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(String provinceName, String cityName) {
        super(provinceName);
        this.cityName = cityName;
    }
}
