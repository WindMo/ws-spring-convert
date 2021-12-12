/**
 * Spring校验体系示例与解读
 * @author WindShadow
 * @version 2021-11-14.
 */

package ws.spring.convert;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ws.spring.convert.util.ValidateUtils;

/**
 * 一个Helper
 * @author WindShadow
 * @version 2021-11-14.
 */
@Slf4j
@Component
class ValidateHelper implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
        helpValidateUtils();
    }

    /**
     * 给{@link ValidateUtils}设置国际化资源
     */
    private void helpValidateUtils() {

        ValidateUtils.setMessageSource(this.applicationContext.getBean(MessageSource.class));
    }
}

