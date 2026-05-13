package com.crm.demo;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.runtime.Micronaut;
import jakarta.persistence.Entity;

@Introspected(packages = "com.crm.demo.entity", includedAnnotations = Entity.class)
public class Application {

    public static void main(String[] args) {
        // Disable JMX attach to avoid cgroup-related warnings
        System.setProperty("jdk.attach.allowAttachSelf", "false");
        Micronaut.run(Application.class, args);
    }
}
