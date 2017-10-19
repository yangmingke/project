package com.flypaas.dao.impl;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.InvoiceAddrDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.InvoiceAddresslist;
@Repository
public class InvoiceAddrDaoImpl extends MyBatisDao implements InvoiceAddrDao {

	private static final String ADD="addInvoiceAddr" ;
	private static final String UPDATE="updateInvoiceAddr" ;
	private static final String GET="getInvoiceAddrById" ;
	
	public void add(InvoiceAddresslist addr) {
		sqlSessionTemplate.insert(ADD, addr);
	}
	public InvoiceAddresslist get(long id) {
		return sqlSessionTemplate.selectOne(GET, id);
	}
	public void update(InvoiceAddresslist addr) {
		sqlSessionTemplate.selectOne(UPDATE, addr);
	}

}
