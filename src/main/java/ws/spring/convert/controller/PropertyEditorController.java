package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.convert.pojo.Student;

/**
 * @author WindShadow
 * @date 2021-11-22.
 */

@Slf4j
@RestController
public class PropertyEditorController {

    /**
     * get请求string参数转{@link Student}，使用{@link RequestParam}指定参数名
     * <p>但是无法进行数据校验，见{@link CustomControllerAdvice#initBinder(WebDataBinder)}
     * @param student Get请求的String参数
     * @return String
     * @see ws.spring.convert.editor.StudentEditor
     */
    @GetMapping("/property-editor-assign")
    public String stringToStudentAssign(@Validated @RequestParam("student") Student student) {

        log.info("student: {}",student);
        return String.valueOf(student);
    }

    /**
     * get请求string参数转{@link Student}，不指定参数名
     * <p>无法映射，见{@link CustomControllerAdvice#initBinder(WebDataBinder)}
     * @param student Get请求的String参数
     * @return String
     * @deprecated 无法从Get请求的String参数映射
     * @see ws.spring.convert.editor.StudentEditor
     */
    @GetMapping("/property-editor-non-assign")
    @Deprecated
    public String stringToStudentNonAssign(@Validated Student student) {

        log.info("student: {}",student);
        return String.valueOf(student);
    }
}
