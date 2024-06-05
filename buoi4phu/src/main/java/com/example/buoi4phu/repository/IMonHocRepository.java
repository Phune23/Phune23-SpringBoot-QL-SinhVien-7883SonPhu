package com.example.buoi4phu.repository;

import com.example.buoi4phu.entity.Lop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMonHocRepository extends JpaRepository<Lop, Long> {
}
