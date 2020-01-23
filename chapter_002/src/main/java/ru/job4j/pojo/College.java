package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student ronald = new Student();
        ronald.setFullName("Рональд Макдональд");
        ronald.setGroup(404);
        ronald.setAdmission(new Date());
        System.out.printf("Студент %s поступил в группу №%s. Дата поступления - %s", ronald.getFullName(),
                ronald.getGroup(), ronald.getAdmission());
    }
}
