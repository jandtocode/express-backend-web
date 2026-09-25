package com.jandtocode.express.controller;

import com.jandtocode.express.dto.response.BalanceUserResponse;
import com.jandtocode.express.dto.response.DashboardDefaultResponse;
import com.jandtocode.express.service.DashboardService;
import com.jandtocode.express.util.GeneralUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDefaultResponse> getDashboard(HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, GeneralUtils.MSG_ERR_DASHBOARD_DEFAULT);
        }

        DashboardDefaultResponse response = dashboardService.getDashboardDefaultResponse(userId.longValue());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard/user")
    public ResponseEntity<BalanceUserResponse> getUserBalance(HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, GeneralUtils.MSG_ERR_DASHBOARD_DEFAULT);
        }

        BalanceUserResponse response = dashboardService.getBalanceByUserId(userId.longValue());

        return ResponseEntity.ok(response);
    }


}