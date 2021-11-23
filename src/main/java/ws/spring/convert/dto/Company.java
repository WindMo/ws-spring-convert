package ws.spring.convert.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author WindShadow
 * @date 2020/9/20.
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Company {

    private Long id;
    private String name;
    @NotNull
    private String address;
}
