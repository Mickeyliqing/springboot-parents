package com.example.springbootthread.config;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import javax.annotation.Resource;

/**
 * 自定义事务管理器
 * @author asus
 *
 */
@Component
public class ManualTransactionManager {
	
	private TransactionStatus transactionStatus;

	@Resource
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	/**
	 * 手动开启事务
	 * @return
	 */
	public TransactionStatus begin(){
		transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
		return transactionStatus;
	}
	
	/**
	 * 提交事务
	 * @param transactionStatus
	 */
	public void commit(TransactionStatus transactionStatus){
		dataSourceTransactionManager.commit(transactionStatus);
	}
	
	/**
	 * 回滚事务
	 */
	public void rollBack(){
		dataSourceTransactionManager.rollback(transactionStatus);
	}
}