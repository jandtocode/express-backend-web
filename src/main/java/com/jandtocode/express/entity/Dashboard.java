package com.jandtocode.express.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "dashboard_info")
public class Dashboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @Column(name = "current_balance")
    private Double currentBalance;

    @Column(name = "accumulated_recharges")
    private Integer accumulatedRecharges;

    @Column(name = "last_recharge")
    private LocalDate lastRecharge;

    @Column(name = "type_payment", length = 100)
    private String typePayment;

    @Column(name = "entity_payment", length = 100)
    private String entityPayment;

    @Column(name = "value_recharge")
    private Double valueRecharge;

    @Column(name = "apply_bonus")
    private Boolean applyBonus;

    @Column(name = "value_bonus")
    private Double valueBonus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Dashboard() {}

    public Dashboard(Long id, User user, Double currentBalance, Integer accumulatedRecharges, LocalDate lastRecharge,
                     String typePayment, String entityPayment, Double valueRecharge, Boolean applyBonus,
                     Double valueBonus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.currentBalance = currentBalance;
        this.accumulatedRecharges = accumulatedRecharges;
        this.lastRecharge = lastRecharge;
        this.typePayment = typePayment;
        this.entityPayment = entityPayment;
        this.valueRecharge = valueRecharge;
        this.applyBonus = applyBonus;
        this.valueBonus = valueBonus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Integer getAccumulatedRecharges() {
        return accumulatedRecharges;
    }

    public void setAccumulatedRecharges(Integer accumulatedRecharges) {
        this.accumulatedRecharges = accumulatedRecharges;
    }

    public LocalDate getLastRecharge() {
        return lastRecharge;
    }

    public void setLastRecharge(LocalDate lastRecharge) {
        this.lastRecharge = lastRecharge;
    }

    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public String getEntityPayment() {
        return entityPayment;
    }

    public void setEntityPayment(String entityPayment) {
        this.entityPayment = entityPayment;
    }

    public Double getValueRecharge() {
        return valueRecharge;
    }

    public void setValueRecharge(Double valueRecharge) {
        this.valueRecharge = valueRecharge;
    }

    public Boolean getApplyBonus() {
        return applyBonus;
    }

    public void setApplyBonus(Boolean applyBonus) {
        this.applyBonus = applyBonus;
    }

    public Double getValueBonus() {
        return valueBonus;
    }

    public void setValueBonus(Double valueBonus) {
        this.valueBonus = valueBonus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
