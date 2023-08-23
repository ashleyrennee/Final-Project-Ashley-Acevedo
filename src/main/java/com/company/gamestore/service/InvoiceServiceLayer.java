package com.company.gamestore.service;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.repository.InvoiceRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Component
public class InvoiceServiceLayer {
    private InvoiceRepository invoiceRepository;

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        // FIXME Needs logic
        return invoice;
    }

    public Invoice getInvoiceById(int id) {
        Optional<Invoice> returnVal = invoiceRepository.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        }
        return null;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> findInvoicesByCustomerName(@PathVariable String name) {
        return invoiceRepository.findByCustomerName(name);
    }
}
