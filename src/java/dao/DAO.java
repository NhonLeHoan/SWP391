/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Airplanes;
import dto.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Zane
 */
public class DAO {

    private static final String LOGIN = "SELECT userID, fullName, roleID FROM tblUsers WHERE userID = ? AND password= ?";
    private static final String SEARCH = "SELECT [id], [destination] ,[departureTime],[isDelay] FROM [dbo].[tblAirplanes] WHERE [id] LIKE ?";
    private static final String UPDATE = "UPDATE [dbo].[tblAirplanes] SET [destination] = ?, [departureTime] = ?, [isDelay] = ? WHERE [id] LIKE ?";
    private static final String DELETE = "DELETE [dbo].[tblAirplanes] WHERE [id] = ?";
    private static final String USER_LIST = "SELECT userID,fullName,roleID FROM tblUsers WHERE userID LIKE ? AND status = 1   ";
    private static final String DELETE_USER = "UPDATE tblUsers SET status = 0  WHERE userID = ?";
    private static final String INSERT = "INSERT INTO tblAirplanes(id,destination,departureTime,isDelay) VALUES(?,?,?,?)";

    public Users checkLogin(String userID, String password) throws SQLException {
        Users user = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(LOGIN);
                ps.setString(1, userID);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    user = new Users(userID, fullName, "", roleID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                rs.close();
            }
            if (con != null) {
                rs.close();
            }
        }
        return user;
    }

    public List<Airplanes> getPlaneList(String search) throws SQLException, ClassNotFoundException {
        List<Airplanes> airList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(SEARCH);
                ps.setString(1, '%' + search + '%');
                rs = ps.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("id");
                    String destination = rs.getString("destination");
                    String departureTime = rs.getString("departureTime");
                    boolean isDelay = rs.getBoolean("isDelay");
                    airList.add(new Airplanes(id, destination, departureTime, isDelay));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                rs.close();
            }
            if (con != null) {
                rs.close();
            }
        }
        return airList;
    }

    public boolean update(Airplanes air) throws SQLException {
        boolean checkUpdate = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(UPDATE);
                ps.setString(1, air.getDestination());
                ps.setString(2, air.getDepartureTime());
                ps.setBoolean(3, air.isIsDelay());
                ps.setString(4, air.getId());
                checkUpdate = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return checkUpdate;
    }

    public boolean delete(String id) throws ClassNotFoundException, SQLException {
        boolean checkDelete = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ps = con.prepareStatement(DELETE);
                ps.setString(1, id);
                checkDelete = ps.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return checkDelete;
    }

    public List<Users> getListCustomer(String searchPattern) throws SQLException {

        List<Users> listCustomer = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {

                ptm = conn.prepareStatement(USER_LIST);
                ptm.setString(1, "%" + searchPattern + "%");

                rs = ptm.executeQuery();

                while (rs.next()) {

                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");

                    listCustomer.add(new Users(userID, fullName, "", roleID));
                }
            }

        } catch (Exception e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return listCustomer;
    }

    public boolean deleteUser(String userID) throws SQLException {

        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {

            conn = DBUtils.getConnection();

            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_USER);
                ptm.setString(1, userID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public boolean checkInsert(Airplanes ap) throws SQLException {
        boolean checkInsert = false;

        Connection conn = null;
        PreparedStatement ptm = null;

        try {

            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);

                ptm.setString(1,ap.getId());
                ptm.setString(2, ap.getDestination());
                ptm.setString(3, ap.getDepartureTime());
                ptm.setString(4, Boolean.toString(ap.isIsDelay()));

                checkInsert = ptm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return checkInsert;
    }

}
