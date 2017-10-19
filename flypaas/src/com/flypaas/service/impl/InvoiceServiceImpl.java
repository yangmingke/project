package com.flypaas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.daocenter.DaoCenter;
import com.flypaas.entity.Invoice;
import com.flypaas.entity.InvoiceAddresslist;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.service.InvoiceAddrService;
import com.flypaas.service.InvoiceService;
@Service
@Transactional
public class InvoiceServiceImpl extends DaoCenter implements InvoiceService {
	@Autowired
	private InvoiceAddrService invoiceAddrService;

	public PageContainer getInvoiceList(PageContainer page) {
		return invoiceDao.getInvoiceList(page);
	}

	public int getAllInvoiceMoney(String sid) {
		return invoiceDao.getAllInvoiceMoney(sid);
	}

	public Invoice getInvoice(String sid) {
		return invoiceDao.getInvoice(sid);
	}

	public void add(Invoice invoice) {
		invoiceDao.add(invoice);
	}

	public void update(Invoice invoice,InvoiceAddresslist addr) {
		invoiceDao.update(invoice);
		if(addr!=null){
			invoiceAddrService.update(addr);
		}
	}

	public Invoice getInvoiceBySidAndId(Invoice invoice) {
		return invoiceDao.getInvoiceBySidAndId(invoice);
	}

}
