package com.farmacia.backend;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Liam
 */
public class User {
    private final Connection conn = new DbConfig().getConn();

    public User() {
    }
    
    private String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
                md = MessageDigest.getInstance("SHA-256");
        } 
        catch (NoSuchAlgorithmException e) {		
                e.printStackTrace();
                return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {        
                sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }    
    
    public AuthenticatedUser authenticate(String username,String password){
        // returns and authenticated user obj if success, else null
        String hashedPasswd = convertirSHA256(password);
        AuthenticatedUser user = new AuthenticatedUser();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM usuarios WHERE Usuario_nickname = ? and Usuario_contrasena = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, hashedPasswd);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                user = new AuthenticatedUser(rs.getString("Usuario_nombre"), rs.getString("Usuario_nickname"), rs.getString("Usuario_rol"));
            }else{
                System.out.println("INVALID CREDENTIALS");
            }
        } catch (Exception e) {
            System.out.println("ERROR ON LOGIN ADMIN SQL");
        }
        
        return user;
    }
}