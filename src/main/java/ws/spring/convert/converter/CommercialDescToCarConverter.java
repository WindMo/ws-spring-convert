package ws.spring.convert.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ws.spring.convert.vo.Car;
import ws.spring.convert.vo.CommercialDesc;

/**
 * 商品描述{@link CommercialDesc}到汽车{@link Car}的转换
 * @author WindShadow
 * @version 2021-12-04.
 */

@Component
public class CommercialDescToCarConverter implements Converter<CommercialDesc, Car> {

    @Override
    public Car convert(CommercialDesc source) {

        String name = source.getDesc()[1];
        Integer engineCode = Integer.valueOf(source.getDesc()[0]);
        return new Car(name,engineCode);
    }
}
