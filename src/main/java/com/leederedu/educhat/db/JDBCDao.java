package com.leederedu.educhat.db;


import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 * JDBC调用数据库,共享连接池,尽量不使用.
 */
public class JDBCDao {
	MySqlJDBC dao;
	public JDBCDao() {
		dao = new MySqlJDBC();
	}
	
	
	/**
	 * 执行查询
	 * @param sql
	 * @param canUpdateRow 是否支持更新行
	 * @return
	 */
	public ResultSet query(String sql, boolean canUpdateRow) {
		return dao.query(sql, canUpdateRow);
	}	
	
	public ResultSet query(String sql) {
		return dao.qurey(sql);
	}
	
	public boolean execute(String sql) {
		return dao.execute(sql);
	}
	
	public boolean executeBatch(String[] sql) {
		return dao.executeBatch(sql);
	}

	/**
	 * 执行更新操作, 执行一般的更新数据库操作
	 */
	public void updateBatch(String sql){
		dao.updateBatch(sql);
	}
	
	/**
	 * 执行Delete、Insert、Update语句
	 */
	public int update(String sql) {
		return dao.update(sql);
	}

	/**
	 * 执行Insert语句,一次一条
	 */
	public int insert(String sql) {
		return dao.insert(sql);
	}

	public void close() {
		dao.close();
	}
	
	public CallableStatement getCallableStatement(String sql){		
		return dao.getCallableStatement(sql);
	}
}
