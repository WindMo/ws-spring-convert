package ws.spring.convert.config;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ws.spring.convert.dto.Student;
import ws.spring.convert.editor.StudentEditor;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WindShadow
 * @date 2021-3-1 0001
 * @since
 */

@Configuration
public class PropertyEditorConfig {

    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {

        CustomEditorConfigurer configurer = new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>(4/3 + 1);
        customEditors.put(Student.class, StudentEditor.class);
        configurer.setCustomEditors(customEditors);
        return configurer;
    }
}
