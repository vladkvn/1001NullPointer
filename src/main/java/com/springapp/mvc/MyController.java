package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vladkvn on 12.11.2016.
 */
@Controller
@RequestMapping("/")
public class MyController {
    @RequestMapping("abc")
    public String hi()
    {
        return "hi";
    }
}
