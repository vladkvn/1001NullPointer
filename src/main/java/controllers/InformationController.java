package controllers;

import dao.Info.InfoDao;
import dao.User.UserDao;
import dto.UserDto;
import entity.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import service.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 06.12.2016.
 */
@Controller
public class InformationController {
    @Autowired
    Service service;


    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String updateInfo(
            HttpSession session, Model model,
            @ModelAttribute("information") Info info,
            @SessionAttribute("UserDto")UserDto userDto)
    {
        if(userDto!=null) {
            model.addAttribute(info);
            int id = service.getUserId(userDto);
            session.setAttribute("id", id);
            service.updateInfo(userDto,info);
            return "redirect:/myInfo";
        }
        else {
            model.addAttribute("message", "error, session is empty");
            return "redirect:/auth";
        }
    }


    @RequestMapping(value = "/updateInfo", method = RequestMethod.GET)
     public String updateInfoGet(HttpSession session,
                                 Model model,
                                 @ModelAttribute("information") Info info)
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
                                @SessionAttribute("UserDto")UserDto userDto,
                                @ModelAttribute("information") Info info)
    {
        if(userDto==null) {
            return "redirect:/auth";
        }
        else {
            model.addAttribute("info", service.getInfo(userDto));
            return "myInfo";
        }
    }

    @RequestMapping(value = "/editInfo", method = RequestMethod.POST)
    public String editInfo(HttpSession session,
                         Model model,
                         @SessionAttribute("UserDto")UserDto userDto,
                         @ModelAttribute("information") Info info)
    {
        if(userDto==null) {
            return "redirect:/auth";
        }
        else {
            model.addAttribute("info", service.getInfo(userDto));
            return "editInfo";
        }
    }
}
