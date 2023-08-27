package com.company.gamestore.controller;

import com.company.gamestore.service.InvoiceServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceServiceLayer invoiceServiceLayer;

    // Create
    @PostMapping(path = "/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {
        return invoiceServiceLayer.saveInvoice(invoiceViewModel);
    }

    // Read
    @GetMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return invoiceServiceLayer.getInvoiceById(id);
    }

    // Read All
    @GetMapping("/invoice")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return invoiceServiceLayer.getAllInvoices();
    }

    // Find By Customer Name
    @GetMapping("invoice/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> findInvoicesByCustomerName(@PathVariable String name) {
        return invoiceServiceLayer.findInvoicesByCustomerName(name);
    }

}
