package controllers;
import dto.UserDto;
import entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import service.Info.InfoService;
import service.Service;
import service.User.UserService;
import validation.Validation;

import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 06.12.2016.
 */
@Controller
public class InformationController {



    @Autowired
    Validation validation;
    @Autowired
    private UserService userService;
    @Autowired
    private InfoService infoService;

    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String updateInfo(
            HttpSession session, Model model,
            @ModelAttribute("information") Info info)
    {
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        else  {
            if(validation.InfoDtoIsValid(info)) {
                model.addAttribute(info);
                int id = userService.getUserId((UserDto) session.getAttribute("UserDto"));
                session.setAttribute("id", id);
                infoService.updateInfo((UserDto) session.getAttribute("UserDto"), info);
                return "redirect:/myInfo";
            }
            else {
                model.addAttribute("message", "Введенные данные некоректны");
                return "myInfo";
            }
        }
    }

    @RequestMapping(value = "/updateInfo", method = RequestMethod.GET)
     public String updateInfoGet(HttpSession session,
                                 Model model,
                                 @ModelAttribute("information") Info info,
                                 @SessionAttribute(name = "UserDto")UserDto userDto)
    {
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        return "redirect:/myInfo";
    }

    @RequestMapping(value = "/myInfo", method = {RequestMethod.POST, RequestMethod.GET})
    public String myInfo(HttpSession session,
                                Model model,
                                @ModelAttribute("information") Info info)
    {
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        else {
            model.addAttribute("info", infoService.getInfo((UserDto) session.getAttribute("UserDto")));
            return "myInfo";
        }
    }

    @RequestMapping(value = "/editInfo", method = RequestMethod.POST)
    public String editInfo(HttpSession session,
                         Model model)
    {
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        else {
            model.addAttribute("info", infoService.getInfo((UserDto) session.getAttribute("UserDto")));
            return "editInfo";
        }
    }
}
