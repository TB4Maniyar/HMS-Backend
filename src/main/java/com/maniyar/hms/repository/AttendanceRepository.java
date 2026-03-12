package com.maniyar.hms.repository;

import com.maniyar.hms.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    Optional<Attendance>
    findTopByStaffIdAndExitTimeIsNullOrderByEntryTimeDesc(Long staffId);

    List<Attendance> findByEntryTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Attendance> findByExitTimeIsNull();

    long countByEntryTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Attendance> findByStaffId(Long staffId);


}
