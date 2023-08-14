package com.example.demo;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class TestJobTest {

    @InjectMocks
    TestJob testJob;

    @Mock
    TestService service;


    @BeforeEach
    void before() {
        JobRunr.configure()
                .useStorageProvider(new InMemoryStorageProvider())
                .useBackgroundJobServer()
                .useDashboard()
                .initialize();
    }

    @AfterEach
    void after() {
        JobRunr.destroy();
    }


    @Test
    public void setTestJobA() {
        Mockito.when(service.find()).thenReturn(getID());
        testJob.run();
    }

    private Stream<IdProjection> getID() {
        var list = new ArrayList<IdProjection>();

        var factory = new SpelAwareProxyProjectionFactory();

        for (var i = 0L; i <= 100L; i++) {
            var id = factory.createProjection(IdProjection.class);
            id.setId(i);
            list.add(id);
        }

        return list.stream();
    }

}
