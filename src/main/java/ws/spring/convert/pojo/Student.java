package ws.spring.convert.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Max;

/**
 * @author WindShadow
 * @date 2021-3-1
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Student {

    @Max(100)
    private Integer id;
    private String name;
}
