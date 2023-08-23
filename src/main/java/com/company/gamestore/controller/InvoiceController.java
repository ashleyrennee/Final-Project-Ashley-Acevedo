package com.company.gamestore.controller;

import com.company.gamestore.model.Invoice;
import com.company.gamestore.service.InvoiceServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceServiceLayer invoiceServiceLayer;

    // Create
    @PostMapping(path = "/invoice")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());
        invoice.setUnitPrice(invoiceViewModel.getUnitPrice());
        invoice.setQuantity(invoiceViewModel.getQuantity());

        return invoiceServiceLayer.saveInvoice(invoice);
    }

    // Read
    @GetMapping("/invoice/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable int id) {
        return invoiceServiceLayer.getInvoiceById(id);
    }

    // Read All
    @GetMapping("/invoice")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceServiceLayer.getAllInvoices();
    }

    // Find By Customer Name
    @GetMapping("invoice/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findInvoicesByCustomerName(@PathVariable String name) {
        return invoiceServiceLayer.findInvoicesByCustomerName(name);
    }
}
