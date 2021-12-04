package ws.spring.convert;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;

import java.util.stream.Stream;

@SpringBootApplication
public class WsSpringConvertApplication implements ApplicationContextAware {
//    https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzI0MTUwOTgyOQ==&action=getalbum&album_id=1727592481828978689&scene=173&from_msgid=2247490656&from_itemidx=1&count=3&nolastread=1#wechat_redirect
    public static void main(String[] args) {
        SpringApplication.run(WsSpringConvertApplication.class, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        String[] names = applicationContext.getBeanNamesForType(ConversionService.class);
        ConversionService[] cs = Stream.of(names).map(applicationContext::getBean).map(ConversionService.class::cast).toArray(ConversionService[]::new);
        System.out.println(names);
    }
}
