package com.example.demo;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.spring.annotations.Recurring;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestJob {
    private final TestService testService;

    TestJob(TestService service) {
        this.testService = service;
    }

    @Job(name = "test")
    @Recurring(id = "test", cron = "${test.cron}")
    @Transactional
    public void run() {
        try (var stream = testService.find()) {
            var idStream = stream.map(IdProjection::getId);
            BackgroundJob.enqueue(idStream, TestService::del);
        }
    }
}
