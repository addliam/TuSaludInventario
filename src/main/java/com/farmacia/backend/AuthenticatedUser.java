package com.farmacia.backend;

/**
 *
 * @author Liam
 */
public class AuthenticatedUser {
    private String name, nickname, rol;

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRol() {
        return rol;
    }

    // en caso no se pasen parametros
    public AuthenticatedUser(){
        this.name = "";
        this.rol = "";
        this.nickname = "";        
    }
    
    public AuthenticatedUser(String name, String nickname, String rol) {
        this.name = name;
        this.nickname = nickname;
        this.rol = rol;
    }
    
    public boolean isAdmin(){
        boolean res = false;
        if (this.rol.equalsIgnoreCase("administrador")){
            res = true;
        }
        return res;
    }
    
    public String getUserInfo(){
        String info = "";
        info += "NAME -> "+ this.name +"\n";
        info += "NICKNAME -> "+ this.nickname +"\n";
        info += "ROL -> "+ this.rol +"\n";
        return info;
    }
}