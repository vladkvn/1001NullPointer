package controllers;

import dao.Info.InfoDao;
import dao.User.UserDao;
import dto.UserDto;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 12.11.2016.
 */
@Controller
public class RegController {
    @Autowired
    Service service;


    @RequestMapping("/")
    public String hello(Model model)
    {
        return "forward:/auth";
    }


    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registration(
            Model model,
            @ModelAttribute("user") UserDto userdto)
    {
        if(service.regOk(userdto, model)) {
            model.addAttribute("message", "Регистрация прошла успешно");
        }
        else {
            model.addAttribute("message","Пользователь с таким логином уже существует");
        }
        return "redirect:/auth";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String authorization(
            Model model, HttpSession session,
            @ModelAttribute("user") UserDto userdto) {
        if(service.auth(userdto))
        {
            session.setAttribute("UserDto", userdto);
            session.setAttribute("roleName", service.getUserRole(userdto));
            System.out.println(service.getUserRole(userdto));
            int id= service.getUserId(userdto);
            model.addAllAttributes(service.info((UserDto) session.getAttribute("UserDto")));
            session.setAttribute("id",id);
            return "redirect:/info"+id;
        }
        else {
            model.addAttribute("message", "error");
            return "auth";
        }
    }

    @RequestMapping(value = "/info{id}", method = RequestMethod.GET)
    public String infoGet(Model model,
                          HttpSession session,
                          @PathVariable(value = "id") int id)
    {
        if(service.idExist(id)) {
            model.addAttribute("info", service.getInfo(id));
            model.addAttribute("user",service.getUserById(id));
            return "info";
        }
        return "errorInfo";
    }



    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String goReg()
    {
        return "reg";
    }


    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String goAuth(Model model, HttpSession session)
    {
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message", "Добро пожаловать!");
            return "auth";
        }
        else
        {
           return "redirect:info"+service.getUserId((UserDto) session.getAttribute("UserDto"));
        }
    }


    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String invalidate(HttpSession session)
    {
        session.invalidate();
        return "redirect:/auth";
    }
}
