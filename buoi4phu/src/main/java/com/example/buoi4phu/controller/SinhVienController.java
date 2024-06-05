package com.example.buoi4phu.controller;

import com.example.buoi4phu.entity.MonHoc;
import com.example.buoi4phu.entity.SinhVien;
import com.example.buoi4phu.services.MonHocService;
import com.example.buoi4phu.services.SinhVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {
    @Autowired
    private SinhVienService sinhVienService;

    @GetMapping
    public String showAllSinhVien(Model model){
        List<SinhVien> dsSinhVien = sinhVienService.getAllSinhVien();
        model.addAttribute("dsSinhVien", dsSinhVien);
        return "sinhvien/list";
    }

    @GetMapping("/add")
    public String showAddFrom(Model model){
        model.addAttribute("sinhVien", new SinhVien());
        return "sinhvien/add";
    }

    @PostMapping("/add")
    public String addSinhVien(@Valid  @ModelAttribute("sinhVien") SinhVien sinhVien, BindingResult result){
        if(result.hasErrors()){

            // trả về view thông tin lỗi
            return "sinhvien/add";
        }
        sinhVienService.addSinhVien(sinhVien);
        return "redirect:/sinhvien";
    }

    @GetMapping("/delete/{mssv}")
    public String deleteSinhVien(@PathVariable("mssv") Long mssv){
        sinhVienService.deleteSinhVien(mssv);
        return "redirect:/sinhvien";
    }

    @GetMapping("/edit/{mssv}")
    public String showEditFrom(@PathVariable("mssv") Long mssv, Model model){
        SinhVien sinhVien = sinhVienService.getSinhVienById(mssv);
        if (sinhVien != null){
            model.addAttribute("sinhVien", sinhVien);
            return "sinhvien/edit";
        }
        return "redirect:/sinhvien";
    }

    @PostMapping("/edit/{mssv}")
    public String updateSinhVien(@Valid @PathVariable("mssv") Long mssv, @ModelAttribute("sinhVien") SinhVien sinhVien, BindingResult result){
        if(result.hasErrors()){

            // trả về view thông tin lỗi
            return "sinhvien/edit";
        }
        SinhVien sinhVienDetails = sinhVienService.getSinhVienById(mssv);

        if (sinhVienDetails != null){
            sinhVienDetails.setHoTen(sinhVien.getHoTen());
            sinhVienDetails.setNgaySinh(sinhVien.getNgaySinh());
            sinhVienDetails.setLop(sinhVien.getLop());
            sinhVienDetails.setEmail(sinhVien.getEmail());
            sinhVienDetails.setMonHocs(sinhVien.getMonHocs());
            sinhVienService.updateSinhVien(sinhVienDetails);
        }


        return "redirect:/sinhvien";
    }
}

