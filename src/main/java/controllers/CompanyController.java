package controllers;
import entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.Company.CompanyService;
import service.Contract.ContractService;
import service.Service;
import validation.Validation;

import javax.servlet.http.HttpSession;

/**
 * Created by vladkvn on 12.12.2016.
 */
@Controller
public class CompanyController {
    @Autowired
    Validation validation;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private ContractService contractService;


    @RequestMapping(value = "/editCompany{id}", method = RequestMethod.POST)
    public String editCompany(@PathVariable("id")int contractId,
                              HttpSession session, Model model,
                              @ModelAttribute("company")Company company)
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))){
            if(validation.CompanyDtoIsValid(company)) {
                contractService.setNewCompany(contractId, company);
                return "redirect:edit_Contract" + contractId;
            }
            else {
                model.addAttribute("message", "Некоректный ввод");
                return "myInfo";
            }
        }
        else    {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/viewCompany{id}", method = RequestMethod.GET)
    public String editCompany(@PathVariable("id")int companyId,
                              HttpSession session, Model model)
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))){
            if(contractService.isContractExist(companyId)) {
                model.addAttribute("company", companyService.viewCompany(companyId));
                model.addAttribute("contracts", companyService.contractsCompany(companyId));
                return "view_Contracts";
            }
            else {
                model.addAttribute("message", "Некоректный ввод");
                return "myInfo";
            }
        }
        else    {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/allCompanies", method = {RequestMethod.POST, RequestMethod.GET})
    public String allCompanies(Model model, HttpSession session)
        {
            if(session.getAttribute("UserDto") == null) {
                model.addAttribute("message", "Необходимо авторизоваться");
                return "redirect:/auth";
            }
            if("admin".equals(session.getAttribute("roleName"))) {
                model.addAttribute("companies", companyService.allCompanies());
                return "companies";
            }
            else    {
                return "redirect:/";
            }
        }

    @RequestMapping(value = "/cr_Com", method = {RequestMethod.POST})
     public String crCom(HttpSession session, Model model,
                         @ModelAttribute("company") Company company)
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))) {
            if(validation.CompanyDtoIsValid(company)) {
                companyService.createCompany(company);
                return "redirect:/adminMenu";
            }
            else {
                model.addAttribute("message", "Некоректный ввод");
                return "myInfo";
            }
        }
        else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/cr_Com", method = {RequestMethod.GET})
    public String crComGet(HttpSession session,Model model)
    {
        if(session.getAttribute("UserDto") == null) {
            model.addAttribute("message", "Необходимо авторизоваться");
            return "redirect:/auth";
        }
        if("admin".equals(session.getAttribute("roleName"))) {
            return "cr_Com";
        }
        else {
            return "redirect:/";
        }
    }
}
