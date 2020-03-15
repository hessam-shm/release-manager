package com.hessam.releasemanager.controller;

import com.hessam.releasemanager.ReleaseManagerApplication;
import com.hessam.releasemanager.bean.Deployment;
import com.hessam.releasemanager.bean.MicroService;
import com.hessam.releasemanager.bean.SystemVersion;
import com.hessam.releasemanager.repository.DeploymentDao;
import com.hessam.releasemanager.repository.SystemVersionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ReleaseManagerApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
public class ReleaseControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private SystemVersionDao systemVersionDao;

    @Autowired
    private DeploymentDao deploymentDao;

    @BeforeEach
    void setup(){

        SystemVersion version = new SystemVersion();
        version.setId("systemVersion");
        version.setVersion(2);
        systemVersionDao.create(version);

        MicroService a = new MicroService();
        a.setName("A");
        a.setVersion(1);
        MicroService b = new MicroService();
        b.setName("B");
        b.setVersion(1);

        Deployment deployment1 = new Deployment(1, Arrays.asList(a));
        Deployment deployment2 = new Deployment(2, Arrays.asList(a,b));

        deploymentDao.create(deployment1);
        deploymentDao.create(deployment2);

    }

    @Test
    public void testGetServiceForVersion(){
        assertTrue(testRestTemplate.getForObject("http://localhost:" + port + "services?systemVersion=2", List.class)
                .size() == 2);
    }

    @Test
    public void testDeployNewService(){
        MicroService service = new MicroService();
        service.setName("C");
        service.setVersion(1);

        ResponseEntity<Integer> responseEntity = testRestTemplate
                .postForEntity("http://localhost:" + port + "/deploy",service,Integer.class);
        assertEquals(3,responseEntity.getBody());
    }

    @Test
    public void testDeployRepeatedservice(){
        MicroService service = new MicroService();
        service.setName("A");
        service.setVersion(1);

        ResponseEntity<Integer> responseEntity = testRestTemplate
                .postForEntity("http://localhost:" + port + "/deploy",service,Integer.class);
        assertEquals(2,responseEntity.getBody());

    }
}
