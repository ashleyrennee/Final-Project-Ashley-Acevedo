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
    public InvoiceViewModel saveInvoice(InvoiceViewModel ivm) {

        Invoice invoice = new Invoice();
        invoice.setName(ivm.getName());
        invoice.setStreet(ivm.getStreet());
        invoice.setCity(ivm.getCity());
        invoice.setState(ivm.getState());
        invoice.setZipcode(ivm.getZipcode());
        invoice.setItemType(ivm.getItemType());
        invoice.setItemId(ivm.getItemId());
        invoice.setQuantity(ivm.getQuantity());

        BigDecimal unitPrice;

        if (invoice.getItemType().equals("Game")) {
            Optional<Game> game = gameRepository.findById(invoice.getItemId());
            unitPrice = game.get().getPrice();
            invoice.setUnitPrice(unitPrice);
        } else if (ivm.getItemType().equals("Console")) {
            Optional<Console> console = consoleRepository.findById(invoice.getItemId());
            unitPrice = console.get().getPrice();
            invoice.setUnitPrice(unitPrice);
        } else if (ivm.getItemType().equals("T-Shirt")) {
            Optional<TShirt> tShirt = tShirtRepository.findById(invoice.getItemId());
            unitPrice = tShirt.get().getPrice();
            invoice.setUnitPrice(unitPrice);
        } else {
            return null;
        }

        BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(invoice.getQuantity()));
        invoice.setSubtotal(subtotal);

        Optional<Tax> taxObject = taxRepository.findByState(invoice.getState());
        BigDecimal taxRate = taxObject.get().getRate();
        BigDecimal tax = unitPrice.multiply(taxRate);
        invoice.setTax(tax);

        Optional<Fee> feeObject = feeRepository.findByProductType(invoice.getItemType());
        BigDecimal processingFee = feeObject.get().getFee();
        if (invoice.getQuantity() > 10) { processingFee = processingFee.add(BigDecimal.valueOf(15.99)); }
        invoice.setProcessingFee(processingFee);

        BigDecimal total = subtotal.add(tax).add(processingFee);
        invoice.setTotal(total);

        invoiceRepository.save(invoice);
        return buildInvoiceViewModel(invoice);
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
        ivm.setUnitPrice(invoice.getUnitPrice());
        ivm.setQuantity(invoice.getQuantity());
        ivm.setSubtotal(invoice.getSubtotal());
        ivm.setTax(invoice.getTax());
        ivm.setProcessingFee(invoice.getProcessingFee());
        ivm.setTotal(invoice.getTotal());
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
