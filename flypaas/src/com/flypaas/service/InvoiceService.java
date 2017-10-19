package com.flypaas.service;

import com.flypaas.entity.Invoice;
import com.flypaas.entity.InvoiceAddresslist;
import com.flypaas.entity.vo.PageContainer;

public interface InvoiceService {

	public PageContainer getInvoiceList(PageContainer page);
	
	public int getAllInvoiceMoney(String sid);
	
	public Invoice getInvoice(String sid);
	
	public void add(Invoice invoice);
	
	public void update(Invoice invoice,InvoiceAddresslist addr);
	
	public Invoice getInvoiceBySidAndId(Invoice invoice);
}
