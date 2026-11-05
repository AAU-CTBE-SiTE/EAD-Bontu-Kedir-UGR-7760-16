package com.craft;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        // Create Spring IoC Container
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beansConfig.xml");

        // Retrieve Student Bean
        Student student =
                context.getBean("student", Student.class);

        // Display Student Details
        student.getDetails();

    }
}