package com.spider.jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.spider.entity.IP;
import com.spider.utils.DateUtil;

/**
 * <p>
 * Title: MiPuJDBC
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author liyongqiang
 * @datetime 2018年10月6日 下午2:40:57
 */
public class MiPuJDBC {

	// 数据库属性配置
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/lyq_db";
	private static final String USER = "root";
	private static final String PWD = "root";

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 */
	private static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER); // 加载数据库驱动
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PWD); // 获取连接
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 插入ip的方法
	 * 
	 * @param ip
	 * @return
	 */
	public static int insertIP(IP ip) {
		Connection conn = getConn();
		int i = 0;
		String sql = "insert into ip_list (ip_address, ip_port, ip_type, ip_location, ip_verify_time, ip_create_time) values(?,?,?,?,?)";
		PreparedStatement ps; // 获取预编译对象
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, ip.getIpAddress());
			ps.setString(2, ip.getIpPort());
			ps.setString(3, ip.getIpType());
			ps.setString(4, ip.getIpLocation());
			ps.setString(5, ip.getIpVerifyTime());
			ps.setString(6, DateUtil.getDateTime("yyyy-MM-dd"));
			i = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;

	}

	/**
	 * 根据id删除指定ip
	 * 
	 * @param id
	 * @return
	 */
	public static int deleteIP(int id) {
		Connection conn = getConn();
		int i = 0;
		String sql = "delete from ip_list where id = " + id + "";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return i;
	}

	/**
	 * 获取全部ip
	 * 
	 * @return
	 */
	public static List<IP> getIP() {
		List<IP> list = new ArrayList<IP>();
		Connection conn = getConn();
		String sql = "select * from ip_list limit 0,1000";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				IP ip = new IP();
				ip.setIpAddress(rs.getString("ip_address"));
				ip.setIpPort(rs.getString("ip_port"));
				ip.setId(rs.getInt("id"));
				list.add(ip);
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
