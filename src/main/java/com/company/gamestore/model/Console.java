package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class Console implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "console_id")
    private Integer id;
    @NotEmpty(message = "You must provide a model for this console")
    @Size(max = 50, message = "Maximum of 50 characters")
    private String model;
    @NotEmpty(message = "You must provide a manufacturer")
    @Size(max = 50, message = "Maximum of 50 characters")
    private String manufacturer;

    @Column(name = "memory_count")
    @NotEmpty(message = "You must provide a memory count")
    @Size(max = 20, message = "Maximum of 20 characters")
    private String memoryCount;
    @NotEmpty(message = "You must provide a processor")
    @Size(max = 20, message = "Maximum of 20 characters")
    private String processor;

    @NotEmpty(message = "You must provide a processor")
    @Size(min = 2, max = 5, message = "Price must be between 2 and 5 characters")
    private BigDecimal price;
    @NotEmpty(message = "You must provide a processor")
    private int quantity;

    public Console(){}
    public Console(String model, String manufacturer, String memoryCount,
                   String processor, BigDecimal price, int quantity) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.memoryCount = memoryCount;
        this.processor = processor;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryCount() {
        return memoryCount;
    }

    public void setMemoryCount(String memoryCount) {
        this.memoryCount = memoryCount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Console console = (Console) o;
        return getQuantity() == console.getQuantity() && Objects.equals(getId(), console.getId()) && Objects.equals(getModel(), console.getModel()) && Objects.equals(getManufacturer(), console.getManufacturer()) && Objects.equals(getMemoryCount(), console.getMemoryCount()) && Objects.equals(getProcessor(), console.getProcessor()) && Objects.equals(getPrice(), console.getPrice());
    }

    public int hashCode() {
        return Objects.hash(getId(), getModel(), getManufacturer(), getMemoryCount(), getProcessor(), getPrice(), getQuantity());
    }

    public String toString() {
        return "Console{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", memoryCount='" + memoryCount + '\'' +
                ", processor='" + processor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
