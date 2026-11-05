package com.craft;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Student {

    private int id;
    private String name;
    private int age;

    // Object dependency
    private Address address;

    // Collection dependency
    private List<Subject> subjects;

    public void getDetails() {

        System.out.println("Student Details:");
        System.out.println("-------------------------");

        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        System.out.println("Address: " + address);

        System.out.println("\nStudent Subjects:");

        int index = 1;

        for (Subject subject : subjects) {

            System.out.println(
                    "Subject " + index + ": "
                            + subject.getSubjectName()
            );

            index++;
        }
    }
}