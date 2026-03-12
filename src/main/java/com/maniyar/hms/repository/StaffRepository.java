package com.maniyar.hms.repository;

import com.maniyar.hms.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findByQrCode(String qrCode);
    long count();
}