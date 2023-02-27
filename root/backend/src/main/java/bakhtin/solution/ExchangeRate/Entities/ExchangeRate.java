package bakhtin.solution.ExchangeRate.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class ExchangeRate {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String shortName;

    @Column
    private LocalDateTime validFrom;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private Float move;

    @Column
    private Integer amount;

    @Column
    private Float valBuy;

    @Column
    private Float valSell;

    @Column
    private Float valMid;

    @Column
    private Float currBuy;

    @Column
    private Float currSell;

    @Column
    private Float currMid;

    @Column
    private Integer version;

    @Column
    private Float cnbMid;

    @Column
    private Float ecbMid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Float getMove() {
        return move;
    }

    public void setMove(Float move) {
        this.move = move;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getValBuy() {
        return valBuy;
    }

    public void setValBuy(Float valBuy) {
        this.valBuy = valBuy;
    }

    public Float getValSell() {
        return valSell;
    }

    public void setValSell(Float valSell) {
        this.valSell = valSell;
    }

    public Float getValMid() {
        return valMid;
    }

    public void setValMid(Float valMid) {
        this.valMid = valMid;
    }

    public Float getCurrBuy() {
        return currBuy;
    }

    public void setCurrBuy(Float currBuy) {
        this.currBuy = currBuy;
    }

    public Float getCurrSell() {
        return currSell;
    }

    public void setCurrSell(Float currSell) {
        this.currSell = currSell;
    }

    public Float getCurrMid() {
        return currMid;
    }

    public void setCurrMid(Float currMid) {
        this.currMid = currMid;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Float getCnbMid() {
        return cnbMid;
    }

    public void setCnbMid(Float cnbMid) {
        this.cnbMid = cnbMid;
    }

    public Float getEcbMid() {
        return ecbMid;
    }

    public void setEcbMid(Float ecbMid) {
        this.ecbMid = ecbMid;
    }
}
