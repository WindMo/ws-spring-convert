package ws.spring.convert.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ws.spring.convert.vo.Car;
import ws.spring.convert.vo.CommercialDesc;

/**
 * 汽车{@link Car}到商品描述{@link CommercialDesc}的转换
 * @author WindShadow
 * @version 2021-11-28.
 */

@Component
public class CarToCommercialDescConverter implements Converter<Car, CommercialDesc> {

    @Override
    public CommercialDesc convert(Car car) {

        return new CommercialDesc(new String[]{car.getEngineCode().toString(),car.getName()});
    }
}
