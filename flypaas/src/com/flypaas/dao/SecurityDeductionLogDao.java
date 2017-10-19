package com.flypaas.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.entity.SecurityDeductionLog;
@Service
@Transactional
public interface SecurityDeductionLogDao {
	public void addSecurityDeductionLog(SecurityDeductionLog log);
}
