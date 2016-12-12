package controllers;

import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Service;
import validation.Validation;

/**
 * Created by vladkvn on 12.12.2016.
 */
@Controller
public class AdminController {
    @Autowired
    Service service;
    @Autowired
    Validation validation;

    @RequestMapping(value = "/adminMenu", method = RequestMethod.POST)
    public String adminMenu(@SessionAttribute("roleName")String roleName,
                               @SessionAttribute("UserDto") UserDto userDto,
                               Model model)
    {
        if(validation.adminAccess(roleName,userDto)){
            model.addAttribute("data", service.getGraphicData());
            return "adminMenu";
        }
        else    {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/allContracts")
    public String allContracts(@SessionAttribute("roleName")String roleName,
                            @SessionAttribute("UserDto") UserDto userDto, Model model)
    {
        if(roleName==null){
            model.addAttribute("message","Авторизуйтесь");
            return "auth";
        }
        if(validation.adminAccess(roleName,userDto)){
            model.addAttribute("contracts",service.getAllContracts());
            return "view_Contracts";
        }
        else    {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/userManage", method = {RequestMethod.GET,RequestMethod.POST})
    public String editRoles(@SessionAttribute("roleName")String roleName,
                               @SessionAttribute("UserDto") UserDto userDto,
                               Model model)
    {
        if(validation.adminAccess(roleName,userDto)){
            model.addAttribute("users",service.getAllUsers());
            return "editUsers";
        }
        else    {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/setNewRole", method = {RequestMethod.GET,RequestMethod.POST})
    public String setNewRole(@SessionAttribute("roleName")String roleName,
                            @SessionAttribute("UserDto") UserDto userDto,
                            @ModelAttribute("User")UserDto userToEdit)
    {
        if(validation.adminAccess(roleName,userDto)){
            service.changeRole(userToEdit);

            return "redirect:/userManage";
        }
        else    {
            return "redirect:/";
        }
    }

}
