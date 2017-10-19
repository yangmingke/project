package com.flypaas.dao;

import com.flypaas.entity.InvoiceAddresslist;

public interface InvoiceAddrDao {

	public void add(InvoiceAddresslist addr);
	
	public InvoiceAddresslist get(long id);
	
	public void update(InvoiceAddresslist addr);
}
