package com.springapp.mvc;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 12.11.2016.
 */
@Controller
public class RegController {
    @Resource(name = "HC")
    Connect db;

    @RequestMapping("/")
    public String hello(Model model)
    {
        model.addAttribute("message","Добро пожаловать!");
        return "auth";
    }

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registration(
            Model model,
            @ModelAttribute("user") UserDTO userdto)
    {
        if(db.chLogin(userdto)) {
            model.addAttribute("message","Пользователь с таким логином уже существует");
            return "reg";
        }
        else {
            db.addUser(userdto);
            model.addAttribute("message","Регистрация прошла успешно");
            return "reg";
        }
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String authorization(
            Model model, HttpSession Session,
            @ModelAttribute("user") UserDTO userdto)
        {
        if(db.chLoginAndPass(userdto))
        {
            User user = db.getUserByLogin(userdto.getLogin());
            model.addAttribute("user", user);
            Session.setAttribute("UserDTO",userdto);
            model.addAttribute(db.getInfoByLogin(userdto.getLogin()));
            return "info";
        }
        else
        {
            model.addAttribute("message","Введенная комбинация логина и пароля не существует");
            return "auth";
        }
    }



    @RequestMapping(value = "/goReg", method = RequestMethod.POST)
    public String goReg()
    {
        return "reg";
    }
    @RequestMapping(value = "/goAuth", method = RequestMethod.POST)
    public String goAuth()
    {
        return "auth";
    }

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String invalidate(HttpSession session)
    {
        session.invalidate();
        return "auth";
    }
}
