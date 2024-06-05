package com.example.buoi4phu.controller;

import com.example.buoi4phu.entity.Lop;
import com.example.buoi4phu.services.LopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lop")
public class LopController {
    @Autowired
    private LopService lopService;

    @GetMapping
    public String showAllLop(Model model){
        List<Lop> dsLop = lopService.getAllLop();
        model.addAttribute("dsLop", dsLop);
        return "lop/list";
    }

    @GetMapping("/add")
    public String showAddFrom(Model model){
        model.addAttribute("lop", new Lop());
        return "lop/add";
    }

    @PostMapping("/add")
    public String addLop(@Valid @ModelAttribute("lop") Lop lop, BindingResult result){
        if(result.hasErrors()){
            // trả về view thông tin lỗi
            return "lop/add";
        }
        lopService.addLop(lop);
        return "redirect:/lop";
    }

    @GetMapping("/delete/{maLop}")
    public String deleteLop(@PathVariable("maLop") Long maLop){
        lopService.deleteLop(maLop);
        return "redirect:/lop";
    }

    @GetMapping("/edit/{maLop}")
    public String showEditFrom(@PathVariable("maLop") Long maLop, Model model){
        Lop lop = lopService.getLopById(maLop);
        if (lop != null){
            model.addAttribute("lop", lop);
            return "lop/edit";
        }
        return "redirect:/lop";
    }

    @PostMapping("/edit/{maLop}")
    public String updateLop(@Valid @PathVariable("maLop") Long maLop, @ModelAttribute("lop") Lop lop, BindingResult result){
        if(result.hasErrors()){
            // trả về view thông tin lỗi
            return "lop/edit";
        }
        Lop lopDetails = lopService.getLopById(maLop);

        if (lopDetails != null){
            lopDetails.setTenLop(lop.getTenLop());
            lopService.updateLop(lopDetails);
        }


        return "redirect:/lop";
    }
}
