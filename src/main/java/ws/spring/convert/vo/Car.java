package ws.spring.convert.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author WindShadow
 * @version 2021-11-25.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Car {

    private String name;
    private Integer engineCode;
}
