package com.example.buoi4phu.services;

import com.example.buoi4phu.entity.SinhVien;
import com.example.buoi4phu.repository.ISinhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinhVienService {
    @Autowired
    private ISinhVienRepository sinhVienRepository;

    public List<SinhVien> getAllSinhVien(){
        return sinhVienRepository.findAll();
    }

    public SinhVien getSinhVienById(Long id){
        return sinhVienRepository.findById(id).orElse(null);
    }

    public void addSinhVien(SinhVien sinhVien){
        sinhVienRepository.save(sinhVien);
    }

    public void deleteSinhVien(Long id){
        sinhVienRepository.deleteById(id);
    }

    public void updateSinhVien(SinhVien sinhVien){
        sinhVienRepository.save(sinhVien);
    }

}
