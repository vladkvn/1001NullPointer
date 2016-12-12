package controllers;

import dto.UserDto;
import entity.Comment;
import entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Service;
import validation.Validation;

import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 09.12.2016.
 */
@Controller
public class ContractController {
    @Autowired
    Service service;

    @Autowired
    Validation validation;

    @RequestMapping(value = "/cr_Con", method = RequestMethod.POST)
    public String cr_Con(HttpSession session,
                         Model model,
                         @ModelAttribute("contract")Contract contract,
                         @SessionAttribute("UserDto")UserDto userDto,
                         @SessionAttribute("roleName")String roleName)
    {
        if(validation.adminAccess(roleName,userDto))
        {
            service.addContractToUser(userDto, contract);
            return "redirect:edit_Contract" + service.getContractId(contract);
        }
        else return "redirect:/";
        }

    @RequestMapping(value = "/my_Con")
    public String myCon(HttpSession session, Model model,@SessionAttribute("UserDto")UserDto userDto)
    {

        model.addAttribute("contracts", service.getContracts(userDto));
        return "view_Contracts";
    }


    @RequestMapping(value = "/Contract{id}")
    public String viewContract(HttpSession session,
                        Model model,
                        @PathVariable(value = "id") int id,
                        @SessionAttribute("UserDto")UserDto userDto)
    {
        if(session.getAttribute("UserDto")!=null)
        {
                model.addAttribute("roleName","admin");
                model.addAttribute("contract", service.getContractById(id));
                model.addAttribute("login", userDto.getLogin());
                model.addAttribute("comments", service.getComments(id));
                return "view_Contract";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/cr_Con", method = RequestMethod.GET)
    public String crConGet(HttpSession session,
                         Model model,
                         @ModelAttribute("contract")Contract contract)
    {
        if((session.getAttribute("roleName").equals("admin"))) {
            return "create_Contract";
        }
        else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/edit_Contract{id}")
    public String editContract(
            @PathVariable(value = "id") int id,
            HttpSession session,
            Model model
    ){
        if((session.getAttribute("roleName").equals("admin"))) {
            model.addAttribute("contract", service.getContractById(id));
            model.addAttribute("usersNot", service.notInTeam(id));
            model.addAttribute("users", service.getUsers(id));
            return "edit_Contract";
        }
        else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/updateContract{id}", method = RequestMethod.POST)
    public String updateContract(
            @PathVariable(value = "id") int id,
            HttpSession session,
            Model model,
            @RequestParam("discription")String discription,
            @RequestParam("fullDiscription")String fullDiscription
    ){
        if(session.getAttribute("roleName").equals("admin")) {
            Contract contract = new Contract(id, discription, fullDiscription);
            service.updateContract(contract);
            return "redirect:edit_Contract" + id;
        }
        else{
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/addUser{id}", method = RequestMethod.POST)
    public String addUserToContract(
            @PathVariable(value = "id") int id,
            HttpSession session,
            @ModelAttribute("user") UserDto userdto
    ){
        if(session.getAttribute("roleName").equals("admin")&&
                !userdto.getLogin().equals(null)) {
            service.addUserToContract(userdto, service.getContractById(id));
            return "redirect:edit_Contract" + id;
        }
        else{
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/deleteUserFromContract{id}", method = RequestMethod.POST)
    public String deleteUserFromContract(
            @PathVariable(value = "id") int id,
            HttpSession session,
            @ModelAttribute("user") UserDto userdto
    ){
        if(session.getAttribute("roleName").equals("admin")&&
                !userdto.getLogin().equals(null)) {

            service.deleteUserFromContract(userdto, service.getContractById(id));
            return "redirect:edit_Contract" + id;
        }
        else{
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/deleteComment{id}", method = RequestMethod.POST)
    public String deleteComment(
            @PathVariable(value = "id") int id,
            HttpSession session,
            @ModelAttribute("contractId") int contractId
    ){
        if(session.getAttribute("UserDto")!=null) {
                service.deleteComment(id);
                return "redirect:/Contract" + contractId;
            }
        else {
            return "redirect:/";
        }
    }


    @RequestMapping(value = "/addComment{id}", method = RequestMethod.POST)
    public String addComment(
            @PathVariable(value = "id") int contractId,
            HttpSession session,
            @ModelAttribute("comment") Comment comment,
            @SessionAttribute("UserDto")UserDto userDto
    ){
        if(userDto!=null) {
            service.addCommentToContract(comment, contractId, userDto);
            return "redirect:/Contract" + contractId;
        }
        else {
                return "redirect:/";
        }
    }


    @RequestMapping(value = "/deleteContract{id}", method = RequestMethod.POST)
    public String deleteContract(
            @PathVariable(value = "id") int id,
            HttpSession session,
            @SessionAttribute(name = "UserDto") UserDto userDto)
    {
        service.deleteContract(id);
        return "redirect:/my_Con";
    }
}
