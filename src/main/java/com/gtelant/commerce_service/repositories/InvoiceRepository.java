package com.gtelant.commerce_service.repositories;

import com.gtelant.commerce_service.models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoices,Integer> {
}
