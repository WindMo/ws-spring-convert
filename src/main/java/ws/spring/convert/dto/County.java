package ws.spring.convert.dto;

import lombok.*;

/**
 * 区/县
 * @author WindShadow
 * @date 2021-11-21.
 */


@Getter
@Setter
@ToString(callSuper = true)
public class County extends Province {

    /** 区/县名 */
    private String countyName;

    public County(String provinceName, String countyName) {
        super(provinceName);
        this.countyName = countyName;
    }

    public County(String region, String provinceName, String countyName) {
        super(region, provinceName);
        this.countyName = countyName;
    }
}
