package ws.spring.convert.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author WindShadow
 * @version 2021-12-10.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Cat {

    private String name;
    private Integer age;
}
