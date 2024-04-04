package com.mjc.school;

import com.mjc.school.config.ApplicationContext;
import com.mjc.school.controller.view.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        org.springframework.context.ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationContext.class);
        Menu menu = context.getBean(Menu.class);
        menu.start();
    }
}
