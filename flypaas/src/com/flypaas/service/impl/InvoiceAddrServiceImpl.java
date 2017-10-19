package com.flypaas.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.InvoiceAddresslist;
import com.flypaas.service.InvoiceAddrService;
@Service
@Transactional
public class InvoiceAddrServiceImpl extends DaoCenter implements InvoiceAddrService {

	public void add(InvoiceAddresslist addr) {
		invoiceAddrDao.add(addr);
	}

	public InvoiceAddresslist get(long id) {
		return invoiceAddrDao.get(id);
	}

	public void update(InvoiceAddresslist addr) {
		invoiceAddrDao.update(addr);
	}

}
