package com.example.buoi4phu.services;

import com.example.buoi4phu.entity.MonHoc;
import com.example.buoi4phu.repository.IMonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonHocService {
    @Autowired
    private IMonHocRepository monHocRepository;

    public List<MonHoc> getAllMonHoc(){
        return monHocRepository.findAll();
    }

    public MonHoc getMonHocById(Long id){
        return monHocRepository.findById(id).orElse(null);
    }

    public void addMonHoc(MonHoc monHoc){
        monHocRepository.save(monHoc);
    }

    public void deleteMonHoc(Long id){
        monHocRepository.deleteById(id);
    }

    public void updateMonHoc(MonHoc monHoc){
        monHocRepository.save(monHoc);
    }

}

