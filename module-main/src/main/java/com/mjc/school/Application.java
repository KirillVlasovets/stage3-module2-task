package com.mjc.school;

import com.mjc.school.config.ApplicationConfig;
import com.mjc.school.controller.view.Menu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Menu menu = context.getBean(Menu.class);
        menu.start();
    }
}
