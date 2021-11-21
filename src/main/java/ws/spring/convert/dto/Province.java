package ws.spring.convert.dto;

import lombok.*;

/**
 * 省份
 * @author WindShadow
 * @date 2021-11-21.
 */

@Getter
@Setter
@ToString(callSuper = true)
public class Province extends AdministrativeRegion {

    /** 省份名称 */
    private String provinceName;

    public Province(String provinceName) {
        this.provinceName = provinceName;
    }

    public Province(String region, String provinceName) {
        super(region);
        this.provinceName = provinceName;
    }
}
