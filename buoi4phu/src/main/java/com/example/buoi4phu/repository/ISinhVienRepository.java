package com.example.buoi4phu.repository;

import com.example.buoi4phu.entity.Lop;
import com.example.buoi4phu.entity.SinhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISinhVienRepository extends JpaRepository<SinhVien, Long> {
}
