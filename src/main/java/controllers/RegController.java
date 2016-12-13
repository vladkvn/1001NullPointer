package controllers;
import dto.UserDto;
import dto.UserRegDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Service;
import validation.Validation;
import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 12.11.2016.
 */
@Controller
public class RegController {
    @Autowired
    Service service;

    @Autowired
    Validation validation;

    @RequestMapping("/")
    public String hello(Model model)
    {
        return "redirect:/auth";
    }


    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    public String registration(
            Model model, HttpSession session,
            @ModelAttribute("user") UserRegDto userRegDto)
    {
        if((UserDto) session.getAttribute("UserDto")!=null)
            return "/myInfo";
        if(validation.UserDtoIsValid(userRegDto)) {
            if (service.canRegistr(userRegDto)) {
                model.addAttribute("message", "Регистрация прошла успешно");
                return "auth";
            } else {
                model.addAttribute("message", "Пользователь с таким логином уже существует");
                return "reg";
            }
        }
        model.addAttribute("message","Проверьте введенные данные");
        model.addAttribute("login",userRegDto.getLogin());
        return "reg";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public String authorization(
            Model model, HttpSession session,
            @ModelAttribute("user") UserDto userDto) {
        if((UserDto) session.getAttribute("UserDto")!=null) {
            return "/myInfo";
        }
        if(!validation.UserDtoIsValid(userDto)){
            model.addAttribute("message","Проверьте введенные данные");
            return "auth";
        }
        if(service.auth(userDto))
        {
            session.setAttribute("UserDto", userDto);
            session.setAttribute("roleName", service.getUserRole(userDto));
            int id= service.getUserId(userDto);
            model.addAllAttributes(service.info((UserDto) session.getAttribute("UserDto")));
            session.setAttribute("id",id);
            return "redirect:/info"+id;
        }
        else {
            model.addAttribute("message", "Введенная комбинация не найдена");
            return "auth";
        }
    }

    @RequestMapping(value = "/info{id}", method = RequestMethod.GET)
    public String infoGet(Model model,
                          @PathVariable(value = "id") int id,
                          HttpSession session)
    {
        if(session.getAttribute("UserDto")==null)
        {
            return "redirect:/auth";
        }
        if(service.idExist(id)) {
            model.addAttribute("info", service.getInfo(id));
            model.addAttribute("user",service.getUserById(id));
            return "info";
        }
        else {
            model.addAttribute("message","Страница пользователя не найдена");
            return "/myInfo";
        }
    }



    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public String goReg(HttpSession session)
    {
        if(session.getAttribute("UserDto")==null) {
            return "reg";
        }
        else {
            return "redirect:/myInfo";
        }
    }


    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String goAuth(Model model,HttpSession session)
    {
        if(session.getAttribute("UserDto")!=null) {
            return "redirect:/myInfo";
        }
        else
        {
           return "auth";
        }
    }


    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String invalidate(HttpSession session)
    {
        session.invalidate();
        return "redirect:/auth";
    }
}
