package ws.spring.convert.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ws.spring.convert.formatter.annoation.NumberSeparate;

import javax.validation.constraints.Max;

/**
 * @author WindShadow
 * @version 2021-12-12.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class NumberWrapper {

    @NumberSeparate
    private Integer code;
    @NumberSeparate('=')
    @Max(100)
    private Long number;
}
