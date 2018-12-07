package com.atguigu;

import com.atguigu.aop.LogAspects;
import com.atguigu.aop.MathCalculator;
import com.atguigu.bean.Person;
import com.atguigu.config.MainConfigOfAOP;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class MainTest {

    public static void main(String[] args) {
        useMainConfigOfAOP();
    }

    private static void useMainConfigOfAOP() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);

        LogAspects logAspects = applicationContext.getBean(LogAspects.class);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        System.out.println(mathCalculator.div(4, 2));

        System.out.println(mathCalculator.test(1, "a", new Person("test", 20)));
    }

}
