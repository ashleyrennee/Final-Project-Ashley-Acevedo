package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","Handler"})
@Table(name = "invoice")
public class Invoice implements Serializable {
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "You must provide a name")
    @Size(max = 50, message = "Maximum of 50 characters")
    private String name;

    @NotEmpty(message = "You must provide a street")
    @Size(max = 100, message = "Maximum of 100 characters")
    private String street;

    @NotEmpty(message = "You must provide a city")
    @Size(max = 50, message = "Maximum of 50 characters")
    private String city;

    @NotEmpty(message = "You must provide a state")
    @Size(max = 20, message = "Maximum of 20 characters")
    private String state;

    @NotEmpty(message = "You must provide a zipcode")
    @Size(max = 10, message = "Maximum of 10 characters")
    private String zipcode;

    @Column(name = "item_type")
    @NotEmpty(message = "You must provide an item type")
    @Size(max = 50, message = "Maximum of 50 characters")
    private String itemType;

    @Column(name = "item_id")
    @NotNull(message = "You must provide an item id")
    private int itemId; // links to either game, console, or t_shirt ids

    @Column(name = "unit_price")
    @NotNull(message = "You must provide a unit price")
    @DecimalMin(value = "0.0", inclusive = false, message = "Unit Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "You must provide a max of 8 integer digits and 2 fractional digits")
    private BigDecimal unitPrice;

    @NotNull(message = "You must provide a quantity")
    @Min(value = 1, message = "Quantity must be greater than 0")
    private int quantity;

    @DecimalMin(value = "0.0", inclusive = false, message = "Subtotal must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "You must provide a max of 8 integer digits and 2 fractional digits")
    private BigDecimal subtotal;

    @DecimalMin(value = "0.0", inclusive = false, message = "Tax must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "You must provide a max of 8 integer digits and 2 fractional digits")
    private BigDecimal tax;

    @DecimalMin(value = "0.0", inclusive = false, message = "Processing Fee must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "You must provide a max of 8 integer digits and 2 fractional digits")
    @Column(name = "processing_fee")
    private BigDecimal processingFee;

    @DecimalMin(value = "0.0", inclusive = false, message = "Total must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "You must provide a max of 8 integer digits and 2 fractional digits")
    private BigDecimal total;

    public Invoice() {}

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id && itemId == invoice.itemId && quantity == invoice.quantity && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street) && Objects.equals(city, invoice.city) && Objects.equals(state, invoice.state) && Objects.equals(zipcode, invoice.zipcode) && Objects.equals(itemType, invoice.itemType) && Objects.equals(unitPrice, invoice.unitPrice) && Objects.equals(subtotal, invoice.subtotal) && Objects.equals(tax, invoice.tax) && Objects.equals(processingFee, invoice.processingFee) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }

}


