package com.jandtocode.express.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para calcular una recarga. El usuario se toma de la sesión, no se envía.")
public class RechargeCardCalculateRequest {

    @Schema(description = "Tipo de pago.",
            allowableValues = {"Efectivo", "Tarjeta"},
            example = "Tarjeta",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String typePayment;

    @Schema(description = "Banco. Es obligatorio siempre, sin importar el tipo de pago.",
            allowableValues = {"PiggyBank Pop", "Banco Monedita", "PixelFinance", "CofreFeliz Bank", "CashCoon Bank"},
            example = "PiggyBank Pop",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String bank;

    @Schema(description = "Nombre del titular. El backend no lo valida.", example = "Juan")
    private String name;

    @Schema(description = "Apellido del titular. El backend no lo valida.", example = "Pérez")
    private String lastName;

    @Schema(description = "Valor a recargar. En la primera recarga debe estar entre 1000 y 10000.",
            example = "2000.0",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Double valueRecharge;

    public RechargeCardCalculateRequest () {}

    public RechargeCardCalculateRequest(String typePayment, String bank, String name, String lastName, Double valueRecharge) {
        this.typePayment = typePayment;
        this.bank = bank;
        this.name = name;
        this.lastName = lastName;
        this.valueRecharge = valueRecharge;
    }


    public String getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(String typePayment) {
        this.typePayment = typePayment;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Double getValueRecharge() {
        return valueRecharge;
    }

    public void setValueRecharge(Double valueRecharge) {
        this.valueRecharge = valueRecharge;
    }
}
