package com.jandtocode.express.controller;

import com.jandtocode.express.dto.request.RechargeCardCalculateRequest;
import com.jandtocode.express.dto.response.BalanceUserResponse;
import com.jandtocode.express.dto.response.DashboardDefaultResponse;
import com.jandtocode.express.dto.response.RechargeCardCalculateResponse;
import com.jandtocode.express.dto.response.RechargeCardResponse;
import com.jandtocode.express.exception.ExceptionResponse;
import com.jandtocode.express.service.DashboardService;
import com.jandtocode.express.util.GeneralUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@Tag(name = "2. Dashboard", description = "Endpoints protegidos: requieren haber hecho login en la misma sesión")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Operation(
            summary = "Dashboard por defecto",
            description = """
                    Devuelve el mensaje de bienvenida del dashboard.
                    Es el **segundo paso del camino 2** (login → dashboard).

                    Requiere sesión activa.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dashboard cargado correctamente.",
                    content = @Content(schema = @Schema(implementation = DashboardDefaultResponse.class))),
            @ApiResponse(responseCode = "401",
                    description = "No hay sesión activa. Primero hay que hacer login.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDefaultResponse> getDashboard(@Parameter(hidden = true) HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, GeneralUtils.MSG_ERR_DASHBOARD_DEFAULT);
        }

        DashboardDefaultResponse response = dashboardService.getDashboardDefaultResponse(userId.longValue());
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Consultar saldo del usuario",
            description = """
                    Devuelve el nombre, el saldo actual, la fecha de la última recarga y el total de viajes.

                    Se usa en dos caminos:
                    - **Camino 3:** segundo paso (login → consulta de saldo).
                    - **Camino 4:** paso final, para **confirmar el saldo actualizado** después de la recarga.

                    Requiere sesión activa.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Saldo consultado correctamente.",
                    content = @Content(schema = @Schema(implementation = BalanceUserResponse.class))),
            @ApiResponse(responseCode = "401",
                    description = "No hay sesión activa. Primero hay que hacer login.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "El usuario no existe o no tiene información de saldo.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/dashboard/user")
    public ResponseEntity<BalanceUserResponse> getUserBalance(@Parameter(hidden = true) HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, GeneralUtils.MSG_ERR_DASHBOARD_DEFAULT);
        }

        BalanceUserResponse response = dashboardService.getBalanceByUserId(userId.longValue());

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Calcular recarga",
            description = """
                    Calcula el bono y el total a pagar de una recarga, y guarda el cálculo.
                    Es el **segundo paso del camino 4** (login → calcular → finalizar → ver saldo).

                    El usuario se toma de la sesión, no se envía en el body.

                    **Reglas del bono:**
                    - **Primera recarga** (usuario nuevo): el monto debe estar entre 1000 y 10000.
                      - 1000 a 3000: bono del 20%
                      - 3001 a 5000: bono del 40%
                      - 5001 a 10000: bono del 50%
                    - **Recargas 10, 20 y 30:** bono fijo de 1000.
                    - **Cualquier otra recarga:** sin bono.

                    Si termina bien, habilita el paso `final`.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cálculo realizado correctamente.",
                    content = @Content(schema = @Schema(implementation = RechargeCardCalculateResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Primera recarga fuera del rango permitido (menor a 1000 o mayor a 10000).",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "401",
                    description = "No hay sesión activa. Primero hay que hacer login.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500",
                    description = "Error interno, por ejemplo si no se envía `valueRecharge`.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/dashboard/recharge/calculate")
    public ResponseEntity<RechargeCardCalculateResponse> calculateRecharge(
            @Parameter(hidden = true) HttpSession session,
            @RequestBody RechargeCardCalculateRequest request) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    GeneralUtils.MSG_ERR_DASHBOARD_DEFAULT
            );
        }

        RechargeCardCalculateResponse response =
                dashboardService.CardCalculate(
                        userId.longValue(),
                        request.getTypePayment(),
                        request.getBank(),
                        request.getName(),
                        request.getLastName(),
                        request.getValueRecharge()
                );

        // Solo se guarda si calculate terminó correctamente
        session.setAttribute("rechargeCalculated", true);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Finalizar recarga",
            description = """
                    Confirma la recarga calculada y actualiza el saldo del usuario.
                    Es el **tercer paso del camino 4** (login → calcular → finalizar → ver saldo).

                    Solo funciona si antes se ejecutó `calculate` con éxito en la misma sesión.
                    Cada `calculate` habilita **un solo** `final`: para recargar de nuevo hay que
                    calcular otra vez.

                    Para confirmar el saldo actualizado, se consulta `GET /api/dashboard/user`.
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Recarga realizada correctamente.",
                    content = @Content(schema = @Schema(implementation = RechargeCardResponse.class))),
            @ApiResponse(responseCode = "400",
                    description = "Primero se debe ejecutar `calculate` correctamente.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "401",
                    description = "No hay sesión activa. Primero hay que hacer login.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404",
                    description = "El usuario no existe.",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PatchMapping("/dashboard/recharge/final")
    public ResponseEntity<RechargeCardResponse> completeRecharge(
            @Parameter(hidden = true) HttpSession session) {

        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    GeneralUtils.MSG_ERR_DASHBOARD_DEFAULT
            );
        }

        Boolean rechargeCalculated =
                (Boolean) session.getAttribute("rechargeCalculated");

        if (!Boolean.TRUE.equals(rechargeCalculated)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Primero debe ejecutar correctamente el servicio " +
                            "/api/dashboard/recharge/calculate"
            );
        }

        RechargeCardResponse response =
                dashboardService.completeRecharge(userId.longValue());

        session.removeAttribute("rechargeCalculated");

        return ResponseEntity.ok(response);
    }

}