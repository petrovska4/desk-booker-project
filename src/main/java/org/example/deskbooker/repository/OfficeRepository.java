package org.example.deskbooker.repository;

import org.example.deskbooker.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {
}
