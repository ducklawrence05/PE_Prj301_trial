/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class UserDao {
    //-----            your code here   --------------------------------
    private final String CHECK_LOGIN = "SELECT * FROM tblUsers WHERE userID = ? AND password = ?";
    
    public UserDto checkLogin(String userID, String password) throws ClassNotFoundException, SQLException {
        try (Connection conn = DbUtils.getConnection();
                PreparedStatement stm = conn.prepareStatement(CHECK_LOGIN)) {
            stm.setString(1, userID);
            stm.setString(2, password);
            try (ResultSet rs = stm.executeQuery()){
                if(rs.next()) {
                    return new UserDto(
                            rs.getString("userID"),
                            rs.getString("fullName"),
                            rs.getString("roleID"),
                            ""
                    );
                }
            }
        }
        return null;
    }
}
