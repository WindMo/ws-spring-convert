package ws.spring.convert.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

/**
 * @author WindShadow
 * @date 2021-11-21.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class City extends Province{

    @Length(max = 2)
    private String cityName;

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City(String provinceName, String cityName) {
        super(provinceName);
        this.cityName = cityName;
    }
}
