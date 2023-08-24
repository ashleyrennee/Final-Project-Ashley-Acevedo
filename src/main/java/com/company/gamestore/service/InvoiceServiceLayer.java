package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InvoiceServiceLayer {
    private InvoiceRepository invoiceRepository;
    private FeeRepository feeRepository;
    private TaxRepository taxRepository;
    private GameRepository gameRepository;
    private ConsoleRepository consoleRepository;
    private TShirtRepository tShirtRepository;

    @Autowired
    public InvoiceServiceLayer(InvoiceRepository invoiceRepository, FeeRepository feeRepository, TaxRepository taxRepository, GameRepository gameRepository, ConsoleRepository consoleRepository, TShirtRepository tShirtRepository) {
        this.invoiceRepository = invoiceRepository;
        this.feeRepository = feeRepository;
        this.taxRepository = taxRepository;
        this.gameRepository = gameRepository;
        this.consoleRepository = consoleRepository;
        this.tShirtRepository = tShirtRepository;
    }

    @Transactional
    public Invoice saveInvoice(InvoiceViewModel ivm) {

        Invoice invoice = new Invoice();
        invoice.setName(ivm.getName());
        invoice.setStreet(ivm.getStreet());
        invoice.setCity(ivm.getCity());
        invoice.setState(ivm.getState());
        invoice.setZipcode(ivm.getZipcode());
        invoice.setItemType(ivm.getItemType());
        invoice.setItemId(ivm.getItemId());
        invoice.setQuantity(ivm.getQuantity());


        if (invoice.getQuantity() <= 0) { return  null; }                 // Return null if invoice quantity is invalid

        BigDecimal unitPrice;
        Optional<Tax> taxObject;

        if (invoice.getItemType().equals("Game")) {
            Optional<Game> game = gameRepository.findById(invoice.getItemId());
            if (game.isEmpty()) { return null; }                                    // Return null if item is not found
            unitPrice = game.get().getPrice();
            invoice.setUnitPrice(unitPrice);
            int totalQuantity = game.get().getQuantity();
            if (invoice.getQuantity() > totalQuantity) { return null; }           // Return null if quantity is invalid
            Game updatedGame = game.get();
            updatedGame.setQuantity(totalQuantity - invoice.getQuantity());
            taxObject = taxRepository.findByState(invoice.getState());
            if (taxObject.isEmpty()) { return null; }                           // Return null if state code is invalid
            gameRepository.save(updatedGame);
        } else if (ivm.getItemType().equals("Console")) {
            Optional<Console> console = consoleRepository.findById(invoice.getItemId());
            if (console.isEmpty()) { return null; }                                 // Return null if item is not found
            unitPrice = console.get().getPrice();
            invoice.setUnitPrice(unitPrice);
            int totalQuantity = console.get().getQuantity();
            if (invoice.getQuantity() > totalQuantity) { return null; }           // Return null if quantity is invalid
            Console updatedConsole = console.get();
            updatedConsole.setQuantity(totalQuantity - invoice.getQuantity());
            taxObject = taxRepository.findByState(invoice.getState());
            if (taxObject.isEmpty()) { return null; }                           // Return null if state code is invalid
            consoleRepository.save(updatedConsole);
        } else if (ivm.getItemType().equals("T-Shirt")) {
            Optional<TShirt> tShirt = tShirtRepository.findById(invoice.getItemId());
            if (tShirt.isEmpty()) { return null; }                                  // Return null if item is not found
            unitPrice = tShirt.get().getPrice();
            invoice.setUnitPrice(unitPrice);
            int totalQuantity = tShirt.get().getQuantity();
            if (invoice.getQuantity() > totalQuantity) { return null; }           // Return null if quantity is invalid
            TShirt updatedTShirt = tShirt.get();
            updatedTShirt.setQuantity(totalQuantity - invoice.getQuantity());
            taxObject = taxRepository.findByState(invoice.getState());
            if (taxObject.isEmpty()) { return null; }                           // Return null if state code is invalid
            tShirtRepository.save(updatedTShirt);
        } else {
            return null;
        }

        // Calculate the subtotal amount
        BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(invoice.getQuantity()));
        invoice.setSubtotal(subtotal);

        // Calculate the tax
        BigDecimal taxRate = taxObject.get().getRate();
        BigDecimal tax = subtotal.multiply(taxRate);
        invoice.setTax(tax);

        // Calculate the total processing fee
        Optional<Fee> feeObject = feeRepository.findByProductType(invoice.getItemType());
        BigDecimal processingFee = feeObject.get().getFee();
        if (invoice.getQuantity() > 10) { processingFee = processingFee.add(BigDecimal.valueOf(15.99)); }
        invoice.setProcessingFee(processingFee);

        // Calculate the total amount for the invoice
        BigDecimal total = subtotal.add(tax).add(processingFee);
        invoice.setTotal(total);

        return invoiceRepository.save(invoice);
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel ivm = new InvoiceViewModel();
        ivm.setId(invoice.getId());
        ivm.setName(invoice.getName());
        ivm.setStreet(invoice.getStreet());
        ivm.setCity(invoice.getCity());
        ivm.setState(invoice.getState());
        ivm.setZipcode(invoice.getZipcode());
        ivm.setItemType(invoice.getItemType());
        ivm.setItemId(invoice.getItemId());
        ivm.setQuantity(invoice.getQuantity());
        return ivm;
    }

    public InvoiceViewModel getInvoiceById(int id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return invoice.isPresent() ? buildInvoiceViewModel(invoice.get()) : null;
    }

    public List<InvoiceViewModel> getAllInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        List<InvoiceViewModel> ivmList = new ArrayList<>();
        for (Invoice invoice: invoiceList) {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }
        return ivmList;
    }

    public List<InvoiceViewModel> findInvoicesByCustomerName(String name) {
        List<Invoice> invoiceList = invoiceRepository.findByName(name);
        List<InvoiceViewModel> ivmList = new ArrayList<>();
        for (Invoice invoice: invoiceList) {
            InvoiceViewModel ivm = buildInvoiceViewModel(invoice);
            ivmList.add(ivm);
        }
        return ivmList;
    }

}
