package dao;

import java.sql.*;
import java.util.*;

import domain.Stu;

public class StuDAOImp implements StuDAO {
  String url = "jdbc:hsqldb:hsql://localhost";
  String driver = "org.hsqldb.jdbcDriver";
  String user = "sa";
  String pass = "";

  @Override
  public boolean check(String username) throws Exception {
    Class.forName(driver);
    String sql = "select * from stu where username =?";
    boolean isHave = false;
    try (Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement pstmt = con.prepareStatement(sql);) {
      pstmt.setString(1, username);
      try (ResultSet rs = pstmt.executeQuery();) {
        isHave = rs.next();
      }
    }
    return isHave;
  }

  /**
   * �õ�����ѧ�� select * from stu last,getRow��ǰʱ�ڼ���,beforeFirst List<String[]>
   */
  // List<Stu>
  @Override
  public List<String[]> getAllStus() throws Exception {
    List<String[]> stus = new ArrayList<>();
    Class.forName(driver);
    String sql = "select * from stu";
    try (Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement pstmt = con.prepareStatement(sql);) {
      try (ResultSet rs = pstmt.executeQuery();) {
        while (rs.next()) {// ����ÿ�е�����
          String[] row = new String[2];
          row[0] = rs.getString("id");
          row[1] = rs.getString("username");
          stus.add(row);
        }
      }
    }
    return stus;
  }

  @Override
  public List<Stu> getAllStusByObj() throws Exception {
    List<Stu> stus = new ArrayList<>();
    Class.forName(driver);
    String sql = "select * from stu";
    try (Connection con = DriverManager.getConnection(url, user, pass);
        PreparedStatement pstmt = con.prepareStatement(sql);) {
      try (ResultSet rs = pstmt.executeQuery();) {
        while (rs.next()) {// ����ÿ�е�����
          Stu stu = new Stu();
          stu.setId(rs.getLong("id"));
          stu.setUsername(rs.getString("username"));
          stus.add(stu);
        }
      }
      return stus;
    }
  }
}