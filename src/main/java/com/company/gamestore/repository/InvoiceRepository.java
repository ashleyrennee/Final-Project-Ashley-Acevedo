package com.company.gamestore.repository;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByName(String name);
}
