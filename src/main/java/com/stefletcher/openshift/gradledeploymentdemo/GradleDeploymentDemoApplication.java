package com.stefletcher.openshift.gradledeploymentdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Map;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;

@SpringBootApplication
public class GradleDeploymentDemoApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(GradleDeploymentDemoApplication.class, args);
		//create new hazelcast member
		HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<String, String> map = hazelcastInstance.getMap("data");
        


        
	}
}
