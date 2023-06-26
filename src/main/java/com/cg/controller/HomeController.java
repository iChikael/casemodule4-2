package com.cg.controller;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.BillViewDTO;
import com.cg.service.bill.IBillService;
import com.cg.service.category.ICategoryService;
import com.cg.service.unit.IUnitService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IUnitService iUnitService;

    @Autowired
    IBillService billService;

    @Autowired
    AppUtils appUtils;

    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/list")
    public String showAdminPage(Model model){

        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = role.getCode();

        username = username.substring(0, username.indexOf("@"));
        model.addAttribute("username", username);
        model.addAttribute("roleCode", roleCode);

        return "index";
    }

    @GetMapping("/revenue")
    public String showRevenue(Model model){

        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = role.getCode();

        username = username.substring(0, username.indexOf("@"));
        model.addAttribute("username", username);
        model.addAttribute("roleCode", roleCode);

            return "/billdetails";

    }






    @GetMapping("/products")
    public ModelAndView showHomePage(Model model) {

        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = role.getCode();

        username = username.substring(0, username.indexOf("@"));
        model.addAttribute("username", username);
        model.addAttribute("roleCode", roleCode);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("products");
        List<Category> categories = categoryService.findAll();
        List<Unit> units = iUnitService.findAll();
        modelAndView.addObject("units", units);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showCreateProductForm(Model model) {

        String username = appUtils.getPrincipalUsername();

        Optional<User> userOptional = userService.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new DataInputException("User not valid");
        }

        Role role = userOptional.get().getRole();
        String roleCode = role.getCode();

        username = username.substring(0, username.indexOf("@"));
        model.addAttribute("username", username);
        model.addAttribute("roleCode", roleCode);

        ModelAndView modelAndView = new ModelAndView();
        List<Category> categories = categoryService.findAll();
        List<Unit> units = iUnitService.findAll();
        modelAndView.addObject("units", units);
        modelAndView.addObject("categories", categories);
        modelAndView.setViewName("addproduct");
        return modelAndView;
    }
}
