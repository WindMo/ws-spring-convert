package ws.spring.convert;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.format.support.FormattingConversionService;
import ws.spring.convert.converter.CarToCommercialDescConverter;
import ws.spring.convert.converter.CommercialDescToCarConverter;
import ws.spring.convert.formatter.CommercialDescFormatter;
import ws.spring.convert.pojo.User;
import ws.spring.convert.vo.Car;
import ws.spring.convert.vo.CommercialDesc;

import java.util.Locale;

/**
 * @author WindShadow
 * @version 2021-11-28.
 */

@Slf4j
public class FormattingConversionServiceTests {

    public FormattingConversionService formattingConversionService = new FormattingConversionService();

    /**
     * {@link FormattingConversionService#canConvert(Class, Class)}可以转换{@link String}类型为{@link User}类型，
     * 使用的是已经注册的{@link ws.spring.convert.formatter.UserFormatter}
     */
    @Test
    void formattingConversionServiceConvertTest() {

        Assertions.assertTrue(formattingConversionService.canConvert(String.class, User.class));
        Assertions.assertTrue(formattingConversionService.canConvert(User.class, String.class));
        User user = formattingConversionService.convert("100-tom-123@qq.com",User.class);
        log.info("user: {}",user);
    }

    /**
     * Formatter 的 converter 能力
     */
    @Test
    public void convertOfFormatter() {

        // 没有任何格式化器之前 CommercialDesc -> String 或 String -> CommercialDesc 都不行
        Assertions.assertFalse(formattingConversionService.canConvert(CommercialDesc.class, String.class));
        Assertions.assertFalse(formattingConversionService.canConvert(String.class, CommercialDesc.class));

        //  CommercialDesc -> String 或 String -> CommercialDesc 都可以
        Assertions.assertTrue(formattingConversionService.canConvert(CommercialDesc.class, String.class));
        Assertions.assertTrue(formattingConversionService.canConvert(String.class, CommercialDesc.class));
    }

    /**
     * Formatter通过converter的能力增强自身的适配能力，见{@link FormattingConversionService}的内部类PrinterConverter 和 ParserConverter
     * 实现{@link GenericConverter#convert(Object, TypeDescriptor, TypeDescriptor)}方法的源码
     * <p>
     * {@link CarToCommercialDescConverter} 与 {@link CommercialDescFormatter#print(CommercialDesc, Locale)} 关联<br>
     * {@link CommercialDescToCarConverter} 与 {@link CommercialDescFormatter#parse(String, Locale)} 关联
     */
    @Test
    public void adviceFormatterByConverter() {

        // 此时 Car -> String 或 String -> Car 都不行
        Assertions.assertFalse(formattingConversionService.canConvert(Car.class, String.class));
        Assertions.assertFalse(formattingConversionService.canConvert(String.class, Car.class));

        // 注册一个 CommercialDesc 的 Formatter，指定处理类型为 Car
        formattingConversionService.addFormatterForFieldType(Car.class,new CommercialDescFormatter());

        // 添加Converter转换器 Car -> CommercialDesc
        formattingConversionService.addConverter(new CarToCommercialDescConverter());
        // 此时 Car -> String 可以
        Assertions.assertTrue(formattingConversionService.canConvert(Car.class, String.class));
        String text = formattingConversionService.convert(new Car("宝马",1001),String.class);
        log.info("text: {}",text);

        // 添加Converter转换器 CommercialDesc -> Car
        formattingConversionService.addConverter(new CommercialDescToCarConverter());
        // 此时 String -> Car 可以
        Assertions.assertTrue(formattingConversionService.canConvert(String.class, Car.class));
        Car car = formattingConversionService.convert("1002=奔驰",Car.class);
        log.info("car: {}",car);
    }
}
