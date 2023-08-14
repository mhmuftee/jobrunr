package com.example.demo;

import org.jobrunr.jobs.states.StateName;
import org.jobrunr.storage.StorageProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties = {"test.cron=*/1 * * * *", "org.jobrunr.background-job-server.worker-count=2"})
public class JobAppIntTests {

    @Autowired
    TestJob testJob;

    @Autowired
    NewEntityRepository newEntityRepository;

    @Autowired
    StorageProvider storageProvider;

    @Test
    public void testB() {


        for(int i=0;i< 1000;i++) {
            var entity = new NewEntity();
            newEntityRepository.save(entity);
        }
        assertEquals(1000, newEntityRepository.findAll().size());

        testJob.run();

        await().atMost(65, TimeUnit.SECONDS).until(() -> storageProvider.recurringJobExists("test", StateName.SUCCEEDED));

        assertEquals( 0, newEntityRepository.findAll().size());

    }
}
