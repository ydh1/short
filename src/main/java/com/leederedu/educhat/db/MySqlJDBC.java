package com.leederedu.educhat.db;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.leederedu.educhat.utils.SpringUtils;
import com.leederedu.educhat.utils.MyLog;


public class MySqlJDBC{
	
	//private static DBConnProfile connProfile = Setting.regDB;
	private static DataSource ds;
	
	private Connection conn = null;
	private ResultSet rs = null;
	private Statement stateMent;
    private ArrayList<Statement> stateMents;
    private ArrayList<ResultSet> resultSets;
	
	
    
	/**
	 * 默认打开User数据库
	 */
	public MySqlJDBC() {
		try {
	        conn = getConnection();
	        stateMents = new ArrayList<Statement>();
	        resultSets = new ArrayList<ResultSet>();
	        
		} catch (Exception e) {
			MyLog.error(e);
		}
	}
	
//	public MySqlDao(DBConnProfile profile){
//		try {
//			try {
//				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			connProfile = profile;
//			conn = DriverManager.getConnection(connProfile.getConnString());	
//		} catch (Exception e) {
//			Log.out(e);
//		}
//	}
	
	/**
	 * 打开数据库连接
	 */
	private Connection getConnection() {
		Connection conn = null;
		try {
			if(ds == null){
//				Context initContext = new InitialContext();  
//		        Context envContext  = (Context)initContext.lookup("java:/comp/env");  
//		        ds = (DataSource)envContext.lookup("jdbc/liveline");  
				ds = (DataSource) SpringUtils.getContext().getBean("dataSource");
			}
		
	        conn = ds.getConnection();  
	        conn.setAutoCommit(false);
		} catch (Exception e) {
			ds = null;
			MyLog.error(e);
		}
		return conn;
	}

	/**
	 * 执行查询
	 * @param sql
	 * @param canUpdateRow 是否支持更新行
	 * @return
	 */
	public ResultSet query(String sql,boolean canUpdateRow) {
		rs = null;
		try {
			if (conn == null)
				conn = getConnection();
			if(canUpdateRow)
				stateMent = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			else
				stateMent = conn.createStatement();
			
			stateMents.add(stateMent);
			
			stateMent.setQueryTimeout(90);
			rs = stateMent.executeQuery(sql);
		} catch (Exception ex) {
			MyLog.debug(sql);
			MyLog.debug("执行SQL 语句出错: " + ex.getMessage());			
		}
		if(rs != null)
			resultSets.add(rs);
		return rs;
	}	
	
	public ResultSet qurey(String sql) {
		rs = null;
		try {
			if (conn == null)
				conn = getConnection();
			stateMent = conn.createStatement();
			stateMents.add(stateMent);
			
			stateMent.setQueryTimeout(90);
			rs = stateMent.executeQuery(sql);
		} catch (Exception ex) {
			MyLog.debug(sql);
			MyLog.debug("执行SQL语句出错: " + ex.getMessage());				
		}
		if(rs != null)
			resultSets.add(rs);;
		return rs;
	}
	

	/**
	 * 执行更新操作, 执行一般的更新数据库操作
	 */
	public void updateBatch(String sql){
		try {
			if (conn == null)
				conn = getConnection();
			// 设置事务处理
			conn.setAutoCommit(false);
			String procSQL = "begin \n" + sql + " \nend;";
			CallableStatement cstmt = conn.prepareCall(procSQL);
			stateMents.add(cstmt);
			cstmt.execute();
			conn.commit();
			cstmt.close();
		} catch (SQLException ex) {
			MyLog.debug(sql);
			MyLog.debug("执行SQL 语句出错: " + ex.getMessage());			
			try {
				// 事务失败，回滚
				conn.rollback();
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * 执行Delete、Insert、Update语句
	 */
	public int update(String sql) {
		int result = 0;
		try {
			if (conn == null)
				conn = getConnection();
			// 设置事务处理
			conn.setAutoCommit(false);
			stateMent = conn.createStatement();
			stateMents.add(stateMent);
			stateMent.setQueryTimeout(90);
			result = stateMent.executeUpdate(sql);
			// stmt.close();
			conn.commit();
			// conn.close();
		} catch (SQLException ex) {
			MyLog.debug(sql);
			MyLog.debug("执行SQL 语句出错: " + ex.getMessage());			
			try {
				// 事务失败，回滚
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 执行Insert语句,一次一条
	 */
	public int insert(String sql) {
		int result = -1;
		try {
			if (conn == null)
				conn = getConnection();
			// 设置事务处理
			conn.setAutoCommit(false);
			stateMent = conn.createStatement();
			stateMents.add(stateMent);
			stateMent.setQueryTimeout(90);
			result = stateMent.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stateMent.getGeneratedKeys();
			if (rs.next()) {
				String key = rs.getString(1);
				result = Integer.parseInt(key);
			}
			rs.close();
			conn.commit();
		} catch (SQLException ex) {
			MyLog.debug(sql);
			MyLog.debug("执行SQL 语句出错: " + ex.getMessage());			
			try {
				// 事务失败，回滚
				conn.rollback();
			} catch (SQLException e) {
			}
		}
		return result;
	}
	
	/**
	 * 提交SQL 语句
	 */
	public boolean execute(String SQL) {
		boolean flag = false;
		try {
			stateMent = conn.createStatement();
			stateMents.add(stateMent);
			stateMent.setQueryTimeout(90);
			flag = stateMent.execute(SQL);
			conn.commit();
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception e) {
			}
			MyLog.debug(SQL.toString());
			MyLog.debug("执行SQL 语句出错: " + ex.getMessage());	
		}
		return flag;
	}
	
	/**
	 * 提交批SQL 语句
	 */
	public boolean executeBatch(String[] SQL) {
		boolean flag = false;
		try {
			conn.setAutoCommit(false);
			stateMent = conn.createStatement();
			stateMents.add(stateMent);
			stateMent.setQueryTimeout(90);
			for (int k = 0; k < SQL.length; k++) {
				if (SQL[k] != null)
					stateMent.addBatch(SQL[k]);
			}
			stateMent.executeBatch();// 提交批SQL 语句
			conn.commit();
			flag = true;
		} catch (Exception ex) {
			try {
				conn.rollback();
			} catch (Exception e) {
			}
			MyLog.debug(SQL.toString());
			MyLog.debug("执行SQL 语句出错: " + ex.getMessage());	
		}
		return flag;
	}

	public void close() {
		try {
			for (ResultSet set : resultSets) {
				try {
					set.close();
				} catch (Exception e) {}
			}			

			for (Statement stmt : stateMents) {
				try {
					stmt.close();
				} catch (Exception e) {}
			}
			
			if (!conn.isClosed()) {
				conn.close();
			}
			
		} catch (Exception e) {
			MyLog.error(e);
		} finally {
			rs = null;
			stateMent = null;
			conn = null;
		}
	}
	
	public CallableStatement getCallableStatement(String sql){
		if (conn == null)
			conn = getConnection();
		try {
			return conn.prepareCall(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
