package com.interns.webdino.timed;

import org.springframework.stereotype.Component;

@Component
public class FirstTimer {

    //@Scheduled(fixedRate=2500)
    public void sayHello(){
        System.out.println("Hi from WebDino!");
    }

}
