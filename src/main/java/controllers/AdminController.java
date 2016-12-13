package controllers;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Contract.ContractService;
import service.Graphics.GraphicsService;
import service.Service;
import service.User.UserService;
import validation.Validation;

import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 12.12.2016.
 */
@Controller
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    ContractService contractService;
    @Autowired
    GraphicsService graphicsService;

    @Autowired
    Validation validation;

    @RequestMapping(value = "/adminMenu", method = {RequestMethod.POST, RequestMethod.GET})
    public String adminMenu(HttpSession session, Model model) {
        if (session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if ("admin".equals(session.getAttribute("roleName"))) {
            model.addAttribute("data", graphicsService.getGraphicData());
            model.addAttribute("data2", graphicsService.getGraphicData2());
            model.addAttribute("legend", graphicsService.getGraphic2Legend());
            return "adminMenu";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/allContracts")
    public String allContracts(HttpSession session, Model model) {
        if (session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if ("admin".equals(session.getAttribute("roleName"))) {
            model.addAttribute("contracts", contractService.getAllContracts());
            return "view_Contracts";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/userManage", method = {RequestMethod.GET, RequestMethod.POST})
    public String editRoles(HttpSession session, Model model) {
        if (session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if ("admin".equals(session.getAttribute("roleName"))) {
            model.addAttribute("users", userService.getAllUsers());
            return "editUsers";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/setNewRole", method = {RequestMethod.GET, RequestMethod.POST})
    public String setNewRole(HttpSession session, Model model, @ModelAttribute("User") UserDto userToEdit) {
        if (session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if ("admin".equals(session.getAttribute("roleName"))) {
            if (validation.UserDtoIsValid(userToEdit)) {
                if (true) {
                    userService.changeRole(userToEdit);
                    return "redirect:/userManage";
                } else {
                    //TODO
                    model.addAttribute("message", "Некоректный ввод");
                    return "myInfo";
                }
            } else {
                return "redirect:/";
            }
        }
        else {
            return "redirect:/";
        }
    }
}
