package com.example.buoi4phu.controller;

import com.example.buoi4phu.entity.MonHoc;
import com.example.buoi4phu.services.MonHocService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/monhoc")
public class MonHocController {
    @Autowired
    private MonHocService monHocService;

    @GetMapping
    public String showAllMonHoc(Model model){
        List<MonHoc> dsMonHoc = monHocService.getAllMonHoc();
        model.addAttribute("dsMonHoc", dsMonHoc);
        return "monhoc/list";
    }

    @GetMapping("/add")
    public String showAddFrom(Model model){
        model.addAttribute("monHoc", new MonHoc());
        return "monhoc/add";
    }

    @PostMapping("/add")
    public String addMonHoc(@Valid  @ModelAttribute("monHoc") MonHoc monHoc, BindingResult result){
        if(result.hasErrors()){

            // trả về view thông tin lỗi
            return "monhoc/add";
        }
        monHocService.addMonHoc(monHoc);
        return "redirect:/monhoc";
    }

    @GetMapping("/delete/{maMon}")
    public String deleteMonHoc(@PathVariable("maMon") Long maMon){
        monHocService.deleteMonHoc(maMon);
        return "redirect:/monhoc";
    }

    @GetMapping("/edit/{maMon}")
    public String showEditFrom(@PathVariable("maMon") Long maMon, Model model){
        MonHoc monHoc = monHocService.getMonHocById(maMon);
        if (monHoc != null){
            model.addAttribute("monHoc", monHoc);
            return "monhoc/edit";
        }
        return "redirect:/monhoc";
    }

    @PostMapping("/edit/{maMon}")
    public String updateMonHoc(@Valid @PathVariable("maMon") Long maMon, @ModelAttribute("monhoc") MonHoc monHoc, BindingResult result){
        if(result.hasErrors()){

            // trả về view thông tin lỗi
            return "monhoc/edit";
        }
        MonHoc monHocDetails = monHocService.getMonHocById(maMon);

        if (monHocDetails != null){
            monHocDetails.setTenMonHoc(monHoc.getTenMonHoc());
            monHocService.updateMonHoc(monHocDetails);
        }


        return "redirect:/monhoc";
    }
}

