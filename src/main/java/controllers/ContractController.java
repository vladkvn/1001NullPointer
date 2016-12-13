package controllers;
import dto.UserDto;
import entity.Comment;
import entity.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Comment.CommentService;
import service.Company.CompanyService;
import service.Contract.ContractService;
import service.Service;
import service.User.UserService;
import validation.Validation;
import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 09.12.2016.
 */
@Controller
public class ContractController {
    @Autowired
    Validation validation;

    @Autowired
    private UserService userService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(value = "/cr_Con", method = RequestMethod.POST)
    public String cr_Con(HttpSession session,
                         Model model,
                        @ModelAttribute("contract")Contract contract
    )
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))){
            if(validation.ContractIsValid(contract)) {
                userService.addContractToUser((UserDto) session.getAttribute("UserDto"), contract);
                return "redirect:edit_Contract" + contractService.getContractId(contract);
            }
            else {
                //model.addAttribute("message", "Некоректный ввод");
                return "redirect:/myInfo";
            }
        }
        else {
            model.addAttribute("contracts", contractService.getContracts((UserDto) session.getAttribute("UserDto")));
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
            model.addAttribute("contracts", contractService.getContracts((UserDto) session.getAttribute("UserDto")));
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
                userService.UserGetContract((UserDto)session.getAttribute("UserDto"), contractId)) {
                if(contractService.isContractExist(contractId)) {
                    model.addAttribute("contract", contractService.getContractById(contractId));
                    model.addAttribute("login", ((UserDto) session.getAttribute("UserDto")).getLogin());

                    model.addAttribute("comments", commentService.getComments(contractId));
                    return "view_Contract";
                }
                else {
                    //TODO
                    return "redirect:/myInfo";
                }
        }
        else {
            model.addAttribute("message","Недостаточно прав");
            return "redirect:/myInfo";
        }
    }

    @RequestMapping(value = "/cr_Con", method = RequestMethod.GET)
    public String crConGet(HttpSession session,
                         Model model)
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message","Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))) {
            return "create_Contract";
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
            if(contractService.isContractExist(contractId)) {
                model.addAttribute("contract", contractService.getContractById(contractId));
                model.addAttribute("usersNot", contractService.notInTeam(contractId));
                model.addAttribute("users", contractService.getUsers(contractId));
                model.addAttribute("companies", companyService.allCompanies());
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
            contractService.updateContract(contract);
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
            if(!contractService.isContractExist(contractId)){
                model.addAttribute("message","Запрашиваемый контракт не найден");
                return "myInfo";
            }
            if(validation.UserDtoIsValid(userdto)) {
                userService.addUserToContract(userdto, contractId);
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
            if(!contractService.isContractExist(contractId)){
                model.addAttribute("message","Запрашиваемый контракт не найден");
                return "myInfo";
            }
            if(validation.UserDtoIsValid(userdto)) {
                contractService.deleteUserFromContract(userdto, contractService.getContractById(contractId));
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
                userService.UserGetContract((UserDto)session.getAttribute("UserDto"), contractId)) {
            if(commentService.isCommentExist(commentId)) {
                commentService.deleteComment(commentId);
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
            if(userService.UserGetContract((UserDto) session.getAttribute("UserDto"), contractId)) {
                commentService.addCommentToContract(comment, contractId, (UserDto) session.getAttribute("UserDto"));
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
            if(contractService.isContractExist(contractId)) {
                contractService.deleteContract(contractId);
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
