package ws.spring.convert.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author WindShadow
 * @version 2021-3-7.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Town {

    private Integer code;
    private String name;
}
