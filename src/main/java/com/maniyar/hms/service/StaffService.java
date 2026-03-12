package com.maniyar.hms.service;

import com.maniyar.hms.dto.StaffDTO;
import com.maniyar.hms.entity.Staff;
import com.maniyar.hms.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffDTO createStaff(StaffDTO dto) {

        String qrCode = UUID.randomUUID().toString();   // QR auto generate

        Staff staff = Staff.builder()
                .name(dto.getName())
                .mobile(dto.getMobile())
                .department(dto.getDepartment())
                .qrCode(qrCode)
                .build();

        Staff saved = staffRepository.save(staff);

        return mapToDTO(saved);
    }

    public StaffDTO updateStaff(Long id, StaffDTO dto) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        staff.setName(dto.getName());
        staff.setMobile(dto.getMobile());
        staff.setDepartment(dto.getDepartment());

        Staff updated = staffRepository.save(staff);

        return mapToDTO(updated);
    }

    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }

    public StaffDTO getStaffById(Long id) {

        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        return mapToDTO(staff);
    }

    public List<StaffDTO> getAllStaff() {

        return staffRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private StaffDTO mapToDTO(Staff staff) {

        return StaffDTO.builder()
                .id(staff.getId())
                .name(staff.getName())
                .mobile(staff.getMobile())
                .department(staff.getDepartment())
                .qrCode(staff.getQrCode())
                .build();
    }
}