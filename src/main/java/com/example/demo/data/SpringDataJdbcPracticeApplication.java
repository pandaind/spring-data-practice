package com.example.demo.data;

import com.example.demo.data.jdbc.Person;
import com.example.demo.data.jdbc.PersonJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import java.util.Date;

//@SpringBootApplication
public class SpringDataJdbcPracticeApplication implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonJdbcDao dao;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJdbcPracticeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("All Persons -> {}", dao.findAll());
        log.info("Person 100001 -> {}", dao.findById(100001));
        log.info("Delete 100002 -> No of rows deleted {}", dao.deleteById(100002));
        log.info("Insert person4 -> No of rows {}", dao.insert(new Person(100004, "person4", "new york", new Date())));
        log.info("update person3 -> No of rows {}", dao.update(new Person(100003, "person3.1", "london", new Date())));
    }
}
