package com.flypaas.dao;

import com.flypaas.entity.Invoice;
import com.flypaas.entity.vo.PageContainer;

public interface InvoiceDao {
	
	public PageContainer getInvoiceList(PageContainer page);
	
	public int getAllInvoiceMoney(String sid);
	
	public Invoice getInvoice(String sid);
	
	public void add(Invoice invoice);
	
	public void update(Invoice invoice);
	
	public Invoice getInvoiceBySidAndId(Invoice invoice);
}
