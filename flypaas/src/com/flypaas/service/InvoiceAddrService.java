package com.flypaas.service;

import com.flypaas.entity.InvoiceAddresslist;

public interface InvoiceAddrService {

	public void add(InvoiceAddresslist addr);
	
	public void update(InvoiceAddresslist addr);
	
	public InvoiceAddresslist get(long id);
}
