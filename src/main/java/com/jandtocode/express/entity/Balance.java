package com.jandtocode.express.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "balance_info")
public class Balance {

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

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Balance() {
    }

    public Balance(User user, Double currentBalance, Integer accumulatedRecharges, LocalDate lastRecharge) {
        this.user = user;
        this.currentBalance = currentBalance;
        this.accumulatedRecharges = accumulatedRecharges;
        this.lastRecharge = lastRecharge;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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
