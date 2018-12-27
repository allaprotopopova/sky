package protopopova.alla.repository;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import protopopova.alla.Application;
import protopopova.alla.model.Collocation;
import protopopova.alla.service.CollocationService;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class CollocationRepositoryImplTest {

    @Autowired
    CollocationService service;

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void get() {
    }

    @Test
    public void getAll() {
        List<Collocation> list = service.getAll();
        System.out.println(list);
    }
}