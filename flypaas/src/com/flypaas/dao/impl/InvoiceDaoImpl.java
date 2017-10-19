package com.flypaas.dao.impl;

import org.springframework.stereotype.Repository;

import com.flypaas.dao.InvoiceDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.entity.Invoice;
import com.flypaas.entity.vo.PageContainer;
@Repository
public class InvoiceDaoImpl extends MyBatisDao implements InvoiceDao {
	
	private static final String GETINVOICELIST="getInvoiceList"; 
	private static final String GETINVOICELISTCOUNT="getInvoiceListCount"; 
	private static final String GETINVOICEMONEY="getInvoiceMoney";
	private static final String GETINVOICE="getInvoice";
	private static final String ADD="addInvoice";
	private static final String UPDATE="updateInvoice";
	private static final String GETINVOICEBYSIDANDID="getInvoiceBySidAndId";
	
	public PageContainer getInvoiceList(PageContainer page) {
		return getSearchPage(GETINVOICELIST, GETINVOICELISTCOUNT, page);
	}

	public int getAllInvoiceMoney(String sid) {
		return sqlSessionTemplate.selectOne(GETINVOICEMONEY,sid);
	}

	public Invoice getInvoice(String sid) {
		return sqlSessionTemplate.selectOne(GETINVOICE,sid);
	}

	public void add(Invoice invoice) {
		sqlSessionTemplate.insert(ADD, invoice);
	}

	public void update(Invoice invoice) {
		sqlSessionTemplate.update(UPDATE, invoice);
	}

	public Invoice getInvoiceBySidAndId(Invoice invoice) {
		return sqlSessionTemplate.selectOne(GETINVOICEBYSIDANDID, invoice);
	}

}
