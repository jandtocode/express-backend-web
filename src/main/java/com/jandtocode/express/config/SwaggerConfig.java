package com.jandtocode.express.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Express Travel++ API",
                version = "v1",
                description = """
                        API de práctica para pruebas de automatización QA.

                        ## Autenticación
                        No usa token (Bearer/JWT). La autenticación es por **sesión HTTP**:
                        al hacer login el servidor crea una cookie `JSESSIONID` y los demás
                        endpoints la usan. En Swagger UI el navegador la guarda automáticamente.
                        Si el login no se ha hecho, los endpoints protegidos responden **401**.

                        ## Caminos de uso

                        **1. Usuario nuevo**
                        `POST /api/auth/register` → `POST /api/auth/login`
                        (el registro NO inicia sesión, hay que hacer login aparte)

                        **2. Dashboard por defecto**
                        `POST /api/auth/login` → `GET /api/dashboard`

                        **3. Consulta de saldo**
                        `POST /api/auth/login` → `GET /api/dashboard/user`

                        **4. Recarga**
                        `POST /api/auth/login` → `POST /api/dashboard/recharge/calculate`
                        → `PATCH /api/dashboard/recharge/final` → `GET /api/dashboard/user`
                        (el último paso confirma el saldo actualizado)

                        > En el camino 4, `final` solo funciona si antes se ejecutó
                        > `calculate` con éxito en la misma sesión.
                        """
        )
)
public class SwaggerConfig {
    // Configuración global de Swagger, no requiere código adicional
}