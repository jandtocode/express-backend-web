package com.jandtocode.express.service.impl;

import com.jandtocode.express.dto.response.BalanceUserResponse;
import com.jandtocode.express.dto.response.DashboardDefaultResponse;
import com.jandtocode.express.repository.BalanceUserRepository;
import com.jandtocode.express.service.DashboardService;
import com.jandtocode.express.util.GeneralUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private BalanceUserRepository balanceUserRepository;

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

}
