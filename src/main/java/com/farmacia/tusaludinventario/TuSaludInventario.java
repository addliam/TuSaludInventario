package com.farmacia.tusaludinventario;

import com.farmacia.backend.User;
import com.farmacia.backend.AuthenticatedUser;

/**
 *
 * @author Liam
 * 
 * DEV SPECIFICATIONS
 * mysql  Ver 8.0.30 for Win64 on x86_64 (MySQL Community Server - GPL)
 * Java v18
 * Netbeans IDE 14
 * 
 */
public class TuSaludInventario {

    public static void main(String[] args) {
        System.out.println("Starting inventario application!");
        
        // Script Prueba de inicio de sesion
        User user = new User();
        AuthenticatedUser authUser = user.authenticate("admin", "admin2022");
        String authorizedUserInformation = authUser.getUserInfo();
        System.out.println("INFORMACION DEL USUARIO LOGGEADO");
        System.out.println(authorizedUserInformation);
    }
}
