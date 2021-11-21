package ws.spring.convert.dto;

import lombok.*;

/**
 * 省份
 * @author WindShadow
 * @date 2021-11-21.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Province {

    /** 省份名称 */
    private String provinceName;
}
