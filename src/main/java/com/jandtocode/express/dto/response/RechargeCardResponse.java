package com.jandtocode.express.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resultado de la recarga finalizada.")
public class RechargeCardResponse {

    @Schema(description = "Indica si la recarga fue exitosa.", example = "true")
    private Boolean success;

    @Schema(description = "Mensaje del resultado.", example = "Recarga realizada correctamente")
    private String message;

    @Schema(description = "Saldo nuevo: saldo anterior + recarga + bono.", example = "2400.0")
    private Double currentBalance;

    @Schema(description = "Fecha de la recarga (fecha de hoy), formato yyyy-MM-dd.", example = "2026-09-30")
    private String lastRecharge;

    @Schema(description = "Total de recargas acumuladas, incluida esta.", example = "1")
    private Integer accumulateRecharges;

    @Schema(description = "Indica si la recarga incluyó bono.", example = "true")
    private boolean applyBonus;

    public RechargeCardResponse(){}

    public RechargeCardResponse(Boolean success, String message, Double currentBalance, String lastRecharge, Integer accumulateRecharges, boolean applyBonus) {
        this.success = success;
        this.message = message;
        this.currentBalance = currentBalance;
        this.lastRecharge = lastRecharge;
        this.accumulateRecharges = accumulateRecharges;
        this.applyBonus = applyBonus;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getLastRecharge() {
        return lastRecharge;
    }

    public void setLastRecharge(String lastRecharge) {
        this.lastRecharge = lastRecharge;
    }

    public Integer getAccumulateRecharges() {
        return accumulateRecharges;
    }

    public void setAccumulateRecharges(Integer accumulateRecharges) {
        this.accumulateRecharges = accumulateRecharges;
    }

    public boolean isApplyBonus() {
        return applyBonus;
    }

    public void setApplyBonus(boolean applyBonus) {
        this.applyBonus = applyBonus;
    }
}
