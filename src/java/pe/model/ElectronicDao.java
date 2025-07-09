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
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author Computing Fundamental - HCM Campus
 */
public class ElectronicDao {

    //-----            your code here   --------------------------------
    private final String GET_ALL = "SELECT * FROM tblElectronics";
    private final String GET_BY_ID = "SELECT * FROM tblElectronics WHERE id = ?";
    private final String GET_BY_NAME = "SELECT * FROM tblElectronics WHERE name LIKE ?";
    private final String CREATE = "INSERT INTO tblElectronics "
            + "(id, name, description, price, quantity, status) "
            + "VALUES (?,?,?,?,?,?)";
    private final String UPDATE = "UPDATE tblElectronics SET name = ?, description = ?, price = ?, quantity = ?, status = ? "
            + "WHERE id = ?";
    private final String DELETE = "DELETE FROM tblElectronics WHERE id = ?";
    private final String SOFT_DELETE = "UPDATE tblElectronics SET status = 0 "
            + "WHERE id = ?";
    
    public List<ElectronicDto> getAll() throws ClassNotFoundException, SQLException {
        List<ElectronicDto> list = new ArrayList<>();
        try ( Connection conn = DbUtils.getConnection(); 
                PreparedStatement stm = conn.prepareStatement(GET_ALL);
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }
    
    public ElectronicDto getById(String id) throws ClassNotFoundException, SQLException {
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement stm = conn.prepareStatement(GET_BY_ID)) {
            stm.setString(1, id);
            try ( ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
            }
        }
        return null;
    }

    public List<ElectronicDto> getByName(String name) throws ClassNotFoundException, SQLException {
        List<ElectronicDto> list = new ArrayList<>();
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement stm = conn.prepareStatement(GET_BY_NAME)) {
            stm.setString(1, "%" + name + "%");
            try ( ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    list.add(map(rs));
                }
            }
        }
        return list;
    }

    public boolean create(String id, String name, String description, float price, int quantity, boolean status) throws ClassNotFoundException, SQLException {
        try ( Connection conn = DbUtils.getConnection();  PreparedStatement stm = conn.prepareStatement(CREATE)) {
            stm.setString(1, id);
            stm.setString(2, name);
            stm.setString(3, description);
            stm.setFloat(4, price);
            stm.setInt(5, quantity);
            stm.setBoolean(6, status);
            return stm.executeUpdate() > 0;
        }
    }

    public boolean update(String id, String name, String description, float price, int quantity, boolean status) throws ClassNotFoundException, SQLException {
        try ( Connection conn = DbUtils.getConnection();  
                PreparedStatement stm = conn.prepareStatement(UPDATE)) {
            stm.setString(1, name);
            stm.setString(2, description);
            stm.setFloat(3, price);
            stm.setInt(4, quantity);
            stm.setBoolean(5, status);
            stm.setString(6, id);
            return stm.executeUpdate() > 0;
        }
    }
    
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        try ( Connection conn = DbUtils.getConnection();  
                PreparedStatement stm = conn.prepareStatement(DELETE)) {
            stm.setString(1, id);
            return stm.executeUpdate() > 0;
        }
    }
    
    public boolean softDelete(String id) throws ClassNotFoundException, SQLException {
        try ( Connection conn = DbUtils.getConnection();  
                PreparedStatement stm = conn.prepareStatement(SOFT_DELETE)) {
            stm.setString(1, id);
            return stm.executeUpdate() > 0;
        }
    }

    private ElectronicDto map(ResultSet rs) throws SQLException {
        return new ElectronicDto(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getFloat("price"),
                rs.getInt("quantity"),
                rs.getBoolean("status")
        );
    }
}
