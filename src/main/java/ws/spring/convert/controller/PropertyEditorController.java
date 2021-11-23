package ws.spring.convert.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.spring.convert.dto.Student;

/**
 * @author WindShadow
 * @date 2021-11-22.
 */

@Slf4j
@RestController
public class PropertyEditorController {

    @GetMapping("/property-editor")
    public String stringToStudent(@RequestParam("student") Student student) {

        log.info("student: {}",student);
        return String.valueOf(student);
    }
}
