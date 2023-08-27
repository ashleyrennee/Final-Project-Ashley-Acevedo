package com.company.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tax")
public class Tax implements Serializable {

    @Id
    @NotEmpty(message = "You must provide a state")
    @Size(min=2, max = 2, message = "State must be 2 characters in length")
    @Column(name = "state")
    private String state;

    @Column(name = "rate")
    @NotNull(message = "You must provide a rate")
    @DecimalMin(value = "0.0", inclusive = false, message = "You must provide a max of 8 integer digits and 2 fractional digits")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal rate;

    public Tax() {}

    public Tax(String state, BigDecimal rate) {
        this.state = state;
        this.rate = rate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tax)) return false;
        Tax tax = (Tax) o;
        return Objects.equals(state, tax.state) && Objects.equals(rate, tax.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }

    @Override
    public String toString() {
        return "Tax{" +
                "state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }

}
