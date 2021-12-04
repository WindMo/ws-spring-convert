package ws.spring.convert.pojo;

import lombok.*;

/**
 * 区/县
 * @author WindShadow
 * @date 2021-11-21.
 */


@Getter
@Setter
@ToString(callSuper = true)
public class County extends City {

    /** 区/县名 */
    private String countyName;

    public County(String cityName, String countyName) {
        super(cityName);
        this.countyName = countyName;
    }

    public County(String provinceName, String cityName, String countyName) {
        super(provinceName, cityName);
        this.countyName = countyName;
    }
}
