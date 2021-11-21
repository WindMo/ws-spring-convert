package ws.spring.convert.editor;

import ws.spring.convert.dto.Student;

import java.beans.PropertyEditorSupport;
import java.util.regex.Pattern;

/**
 * {@link Student}的属性编辑器
 * 把“(1001,张三)”转换成{@link Student}
 * @author WindShadow
 * @date 2021-3-1
 *
 */
public class StudentEditor extends PropertyEditorSupport {

    private static final Pattern STUDENT_PATTEN = Pattern.compile("\\([0-9]{1,},[a-zA-Z\\u4e00-\\u9fa5]{1,}\\)");

    /**
     * @param text 这里进来的字符串不会是空的
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if (STUDENT_PATTEN.matcher(text).matches()) {

            String[] params = text.substring(1,text.length() - 1).split(",");
            Integer id = Integer.valueOf(params[0]);
            String name = params[1];
            // 设置值
            this.setValue(new Student(id,name));
            return;
        }
        throw new IllegalArgumentException("The format like：\"(1001,tom)\"");
    }
}
