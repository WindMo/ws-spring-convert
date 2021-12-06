package ws.spring.convert.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;

/**
 * @author WindShadow
 * @version 2021-3-7.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Town {

    @Max(100)
    private Integer code;
    private String name;
}
