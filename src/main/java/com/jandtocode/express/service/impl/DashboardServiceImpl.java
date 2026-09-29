package com.jandtocode.express.service.impl;

import com.jandtocode.express.dto.response.BalanceUserResponse;
import com.jandtocode.express.dto.response.DashboardDefaultResponse;
import com.jandtocode.express.dto.response.RechargeCardCalculateResponse;
import com.jandtocode.express.repository.BalanceUserRepository;
import com.jandtocode.express.repository.RechargeCardRepository;
import com.jandtocode.express.service.DashboardService;
import com.jandtocode.express.util.GeneralUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private BalanceUserRepository balanceUserRepository;
    @Autowired
    private RechargeCardRepository rechargeCardRepository;

    @Override
    @Transactional
    public DashboardDefaultResponse getDashboardDefaultResponse(Long userId) {

        DashboardDefaultResponse response = new DashboardDefaultResponse();
        response.setSuccess(true);
        response.setMessage(GeneralUtils.MSG_SCC_DASHBOARD_DEFAULT);

        return response;
    }

    @Override
    @Transactional
    public BalanceUserResponse getBalanceByUserId(Long userId) {

        // Obtiene el nombre del usuario
        Map<String, Object> userInfo = balanceUserRepository.getUserNameById(userId);

        // Si el usuario no existe, lanza excepción
        if (userInfo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, GeneralUtils.MSG_ERR_USER_NOT_FOUND);
        }

        // Obtiene la información del saldo
        Map<String, Object> balanceInfo = balanceUserRepository.getBalanceInfoByUserId(userId);

        // Si no existe balance_info para el usuario, lanza excepción
        if (balanceInfo == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, GeneralUtils.MSG_ERR_DATABASE);
        }

        // Construye la respuesta exitosa
        BalanceUserResponse response = new BalanceUserResponse();
        response.setSuccess(true);
        response.setUserName((String) userInfo.get("name"));
        response.setCurrentBalance((Double) balanceInfo.get("currentBalance"));
        response.setLastRecharge(balanceInfo.get("lastRecharge").toString());
        response.setTotalTrips((Integer) balanceInfo.get("accumulatedRecharges"));

        return response;
    }

    @Override
    @Transactional
    public RechargeCardCalculateResponse getInfoRechargeCardCalculate(Long userId, String typePayment, String bank,
                                                                      String name, String lastName, Double valueRecharge) {

        Map<String, Object> userInfo = rechargeCardRepository.getInfoByUserId(userId.intValue());
        Map<String, Object> requestUserInfo = new HashMap<>();
        requestUserInfo.put("typePayment", typePayment);
        requestUserInfo.put("bank", bank);
        requestUserInfo.put("name", name);
        requestUserInfo.put("lastName", lastName);
        requestUserInfo.put("valueRecharge", valueRecharge);

        System.out.println(userInfo);
        System.out.println(requestUserInfo);

        String lastRechargeDate = "1900-01-01";

        // Bonus 1: Regla si es un usuario nuevo y su primer recargo es mayor a 1000, se le aplica un bono del 20% al 50% dependiendo del monto recargado.
        boolean bonus1 = (userInfo.get("lastRecharge").toString().contains(lastRechargeDate))
                && userInfo.get("accumulatedRecharges").toString().equals("0");

        double bonusValue = 0.0;
        boolean bonusApplied = false;

        if(bonus1) {

            if (valueRecharge < 999.99) {
                bonusApplied = false;
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "The recharge amount is less than the minimum of 1000.");
            } else if (valueRecharge <= 3000.01) {
                // Rango: 1000 a 3000 (Multiplica por 20)
                bonusValue = valueRecharge * 0.20;
                bonusApplied = true;
            } else if (valueRecharge <= 5000.01) {
                // Rango: 3001 a 5000 (Multiplica por 40)
                bonusApplied = true;
                bonusValue = valueRecharge * 0.40;
            } else if (valueRecharge <= 10000.01) {
                // Rango: 5001 a 10000 (Multiplica por 50)
                bonusValue = valueRecharge * 0.50;
                bonusApplied = true;
            } else {
                // Si supera los 10000
                bonusApplied = false;
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "It is not possible to recharge: it exceeds the limit of 10000.");
            }

        }

        System.out.println("Bonus 1: " + bonusValue + bonusApplied);


        // Bonus 2
        boolean bonus2=((Integer) userInfo.get("accumulatedRecharges")) == 10
                || ((Integer) userInfo.get("accumulatedRecharges")) == 20
                || ((Integer) userInfo.get("accumulatedRecharges")) == 30;

        if(((Integer) userInfo.get("accumulatedRecharges")) != 0) {
            if(bonus2){
                bonusValue = 1000.0;
                bonusApplied = true;
            }else{
                bonusValue = 0.0;
                bonusApplied = false;
            }
        }

        System.out.println("Bonus 2: " + bonusValue + bonusApplied);

        double totalToPay = valueRecharge + bonusValue;

        // Actualizar la información del usuario en el dashboard
        rechargeCardRepository.updateUserInfoDashboard(
                userId.intValue(),
                typePayment,
                bank,
                valueRecharge,
                bonusApplied,
                bonusValue
        );

        return new RechargeCardCalculateResponse(
                true,
                "Cálculo de recarga exitoso",
                userId.intValue(),
                typePayment,
                bank,
                (String) userInfo.get("name"),
                (String) userInfo.get("lastName"),
                (Double) userInfo.get("currentBalance"),
                (Integer) userInfo.get("accumulatedRecharges"),
                (LocalDate) userInfo.get("lastRecharge"),
                valueRecharge,
                valueRecharge,
                totalToPay,
                bonusApplied,
                bonusValue
        );
    }
}
