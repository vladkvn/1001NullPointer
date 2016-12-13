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
                         @ModelAttribute("contract")Contract contract)
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))){
            if(validation.ContractIsValid(contract)) {
                service.addContractToUser((UserDto) session.getAttribute("UserDto"), contract);
                return "redirect:edit_Contract" + service.getContractId(contract);
            }
            else {
                model.addAttribute("message", "Некоректный ввод");
                return "myInfo";
            }
        }
        else {
            model.addAttribute("contracts", service.getContracts((UserDto) session.getAttribute("UserDto")));
            return "view_Contracts";
        }
    }

    @RequestMapping(value = "/my_Con")
    public String myCon(HttpSession session, Model model)
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        else {
            model.addAttribute("contracts", service.getContracts((UserDto) session.getAttribute("UserDto")));
            return "view_Contracts";
        }
    }


    @RequestMapping(value = "/Contract{id}")
    public String viewContract(HttpSession session,
                        Model model,
                        @PathVariable(value = "id") int contractId)
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))||
                service.UserGetContract((UserDto)session.getAttribute("UserDto"), contractId)) {
                if(service.isContractExist(contractId)) {
                    model.addAttribute("roleName", "admin");
                    model.addAttribute("contract", service.getContractById(contractId));
                    model.addAttribute("login", ((UserDto) session.getAttribute("UserDto")).getLogin());
                    model.addAttribute("comments", service.getComments(contractId));
                    return "view_Contract";
                }
                else {
                    model.addAttribute("message", "Некоректный ввод");
                    return "myInfo";
                }
        }
        else {
            model.addAttribute("message","Недостаточно прав");
            return "/myInfo";
        }
    }

    @RequestMapping(value = "/cr_Con", method = RequestMethod.GET)
    public String crConGet(HttpSession session,
                         Model model,
                         @ModelAttribute("contract")Contract contract)
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))) {
            if(validation.ContractIsValid(contract))
            {
                return "create_Contract";
            }
            else {
                model.addAttribute("message", "Некоректный ввод");
                return "myInfo";
            }
        }
        else {
            model.addAttribute("message","Недостаточно прав");
            return "/myInfo";
        }
    }

    @RequestMapping(value = "/edit_Contract{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String editContract(
            @PathVariable(value = "id") int contractId,
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))) {
            if(service.isContractExist(contractId)) {
                model.addAttribute("contract", service.getContractById(contractId));
                model.addAttribute("usersNot", service.notInTeam(contractId));
                model.addAttribute("users", service.getUsers(contractId));
                model.addAttribute("companies", service.allCompanies());
                return "edit_Contract";
            }
            else {
                model.addAttribute("message", "Некоректный ввод");
                return "myInfo";
            }
        }
        else {
            model.addAttribute("message","Недостаточно прав");
            return "/myInfo";
        }
    }

    @RequestMapping(value = "/updateContract{id}", method = RequestMethod.POST)
    public String updateContract(
            @PathVariable(value = "id") int contractId,
            HttpSession session,
            Model model,
            @RequestParam("discription")String discription,
            @RequestParam("fullDiscription")String fullDiscription){
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))) {
            Contract contract = new Contract(contractId, discription, fullDiscription);
            service.updateContract(contract);
            if(validation.ContractIsValid(contract)) {
                return "redirect:/edit_Contract" + contractId;
            }
            else {
                model.addAttribute("message", "Некоректный ввод");
                return "myInfo";
            }
        }
        else {
            model.addAttribute("message","Недостаточно прав");
            return "/myInfo";
        }
    }

    @RequestMapping(value = "/addUser{id}", method = RequestMethod.POST)
    public String addUserToContract(
            @PathVariable(value = "id") int contractId,
            HttpSession session,Model model,
            @ModelAttribute("user") UserDto userdto
    ){
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))) {
            if(!service.isContractExist(contractId)){
                model.addAttribute("message","Запрашиваемый контракт не найден");
                return "myInfo";
            }
            if(validation.UserDtoIsValid(userdto)) {
                service.addUserToContract(userdto, contractId);
                return "redirect:/edit_Contract" + contractId;
            }
            else {
                model.addAttribute("message", "Некоректный ввод");
                return "myInfo";
            }
        }
        else {
            model.addAttribute("message","Недостаточно прав");
            return "/myInfo";
        }
    }

    @RequestMapping(value = "/deleteUserFromContract{id}", method = RequestMethod.POST)
    public String deleteUserFromContract(
            @PathVariable(value = "id") int contractId,
            HttpSession session,Model model,
            @ModelAttribute("user") UserDto userdto
    ){
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))) {
            if(!service.isContractExist(contractId)){
                model.addAttribute("message","Запрашиваемый контракт не найден");
                return "myInfo";
            }
            if(validation.UserDtoIsValid(userdto)) {
                service.deleteUserFromContract(userdto, service.getContractById(contractId));
                return "redirect:/edit_Contract" + contractId;
            }
            else {
                model.addAttribute("message", "Некоректный ввод");
                return "myInfo";
            }
        }
        else {
            model.addAttribute("message","Недостаточно прав");
            return "/myInfo";
        }
    }

    @RequestMapping(value = "/deleteComment{id}", method = RequestMethod.POST)
    public String deleteComment(
            @PathVariable(value = "id") int commentId,
            HttpSession session, Model model,
            @ModelAttribute("contractId") int contractId){
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))||
                service.UserGetContract((UserDto)session.getAttribute("UserDto"), contractId)) {
            if(service.isCommentExist(commentId)) {
                service.deleteComment(commentId);
                return "redirect:/Contract" + contractId;
            }
            else {
                model.addAttribute("message", "Комментарий не найден");
                return "myInfo";
            }
        }
        else {
            model.addAttribute("message","Недостаточно прав");
            return "/myInfo";
        }
    }


    @RequestMapping(value = "/addComment{id}", method = RequestMethod.POST)
    public String addComment(
            @PathVariable(value = "id") int contractId,
            HttpSession session, Model model,
            @ModelAttribute("comment") Comment comment){
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        else {
            if(service.UserGetContract((UserDto) session.getAttribute("UserDto"), contractId)) {
                service.addCommentToContract(comment, contractId, (UserDto) session.getAttribute("UserDto"));
                return "redirect:/Contract" + contractId;
            }
            else {
                model.addAttribute("message","Недостаточно прав");
                return "/myInfo";
            }
        }
    }


    @RequestMapping(value = "/deleteContract{id}", method = RequestMethod.POST)
    public String deleteContract(
            @PathVariable(value = "id") int contractId,
            HttpSession session, Model model)
    {
        if(session.getAttribute("UserDto")==null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))) {
            if(service.isContractExist(contractId)) {
                service.deleteContract(contractId);
                return "redirect:/my_Con";
            }
            else {
                model.addAttribute("message", "Контракт не найден");
                return "myInfo";
            }
        }
        else
        {
            model.addAttribute("message","Недостаточно прав");
            return "/myInfo";
        }
    }
}
