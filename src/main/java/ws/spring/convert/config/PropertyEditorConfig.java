package ws.spring.convert.config;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.WebDataBinder;
import ws.spring.convert.pojo.Student;
import ws.spring.convert.editor.StudentEditor;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WindShadow
 * @date 2021-3-1.
 */
@Configuration
public class PropertyEditorConfig {

    private static Map<Class<?>, Class<? extends PropertyEditor>> customEditors;

    /**
     * <p>
     *     注册属性编辑器，使IOC创建bean时拥有string类型转为目标类型的能力
     * </p>
     * <p>
     *     注意SpringMVC进行参数绑定时是无法利用此能力的，这是{@link org.springframework.beans.factory.BeanFactory}的能力，SpringMVC上使用需要额外注册
     * </p>
     * <p>
     *     不难发现，{@link CustomEditorConfigurer}注册属性编辑器时，注册的是Class，而在 SpringMVC在参数绑定时，注册的是编辑器的实例，
     *     所以{@link PropertyEditor}的实现类肯定是不能加入IOC的成为bean而复用的。
     *     在实现{@link PropertyEditor}接口，不难发现此类肯定是线程不安全的，一个目标对象，就要使用一个编辑器实例，
     *     这一点和上述{@link PropertyEditor}的实现类肯定不能加入IOC复用呼应上了
     * </p>
     *
     * {@link CustomEditorConfigurer}实现了{@link org.springframework.beans.factory.config.BeanFactoryPostProcessor}接口，设置为静态方法以提高优先级
     * @return {@link CustomEditorConfigurer}
     * @see ws.spring.convert.controller.CustomControllerAdvice#initBinder(WebDataBinder) SpringMVC在参数绑定期间注册属性编辑器实例
     */
    @Bean
    public static CustomEditorConfigurer customEditorConfigurer() {

        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        configurer.setCustomEditors(getPropertyEditors());
        return configurer;
    }

    /**
     * 缓存全部的属性编辑器
     * @return
     */
    public static Map<Class<?>, Class<? extends PropertyEditor>> getPropertyEditors() {

        if (customEditors == null) {

            customEditors = new HashMap<>(4/3 + 1);
            customEditors.put(Student.class, StudentEditor.class);
        }
        return customEditors;
    }
}
