package org.kernel360.busseat.schedule.service;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableScheduling
public class ScheduledTask {

	@Scheduled(fixedRate = 60000) // 180000 밀리초 = 3분
	public void executeTasks() {
	}
}