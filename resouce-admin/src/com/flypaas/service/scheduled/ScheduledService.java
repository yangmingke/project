package com.flypaas.service.scheduled;

import org.springframework.scheduling.annotation.Scheduled;

public interface ScheduledService {
	@Scheduled(cron = "*/60 * * * * ?")
	public void concurrenceMonitoring();
}
