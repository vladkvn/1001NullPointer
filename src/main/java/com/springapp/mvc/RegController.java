package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vladkvn on 12.11.2016.
 */
@Controller
@RequestMapping("/reg")
public class RegController {
    @Autowired
    Connect db;
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView reg(
            @RequestParam("login") String login,
            @RequestParam("pass") String pass
            )
    {
        System.out.println(login);
        System.out.println(pass);
        Map<String,Object> model = new HashMap<String,Object>();
        if(true) {
            model.put("message","Пользователь успешно зарегестрирован");
        }
        else {
            model.put("message","Такой пользователь уже существует");
        }
        model.put("login",login);
        model.put("pass",pass);
        try {
            db.test();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ModelAndView("info",model);
    }
}
