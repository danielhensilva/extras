package com.oreilly.demo.controllers;

import org.junit.Test;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.Assert.*;

public class HelloWorldControllerUnitTest {

    @Test
    public void sayHello() {
        var controller = new HelloController();
        var model = new BindingAwareModelMap();
        var result = controller.sayHello(model, "world");
        assertEquals("hello", result);
        assertEquals("world", model.asMap().get("user"));
    }

}
