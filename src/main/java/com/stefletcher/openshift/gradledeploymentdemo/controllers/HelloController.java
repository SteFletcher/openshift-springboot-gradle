package com.stefletcher.openshift.gradledeploymentdemo.controllers;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map.Entry;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import java.util.Map;

@Controller
@EnableAutoConfiguration
public class HelloController {

    @RequestMapping("/write/{id}")
    @ResponseBody
    String write(@PathVariable("id")String id) {
          //read it using client
          System.out.println("parameter passed = " + id);
		ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        HazelcastInstance hazelcastInstanceClient = HazelcastClient.newHazelcastClient(config);
        Map<String, String> map = hazelcastInstanceClient.getMap("data");
        System.out.println("adding parameter = " + id);
        map.put("Parameter" ,id);
        return "Parameter added " +id;
    }

    @RequestMapping("/read")
    @ResponseBody
    String read() {
          //read it using client
		ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        HazelcastInstance hazelcastInstanceClient = HazelcastClient.newHazelcastClient(config);
        Map<String, String> map = hazelcastInstanceClient.getMap("data");
        return "Param read = " + map.get("Parameter") ;
    }

    @RequestMapping("/hits")
    @ResponseBody
    String hits() {
          //read it using client
		ClientConfig config = new ClientConfig();
        GroupConfig groupConfig = config.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        HazelcastInstance hazelcastInstanceClient = HazelcastClient.newHazelcastClient(config);
        Map<String, String> map = hazelcastInstanceClient.getMap("data");
        String hitsStr = map.get("hits");
        Integer hits = 0;
        if(hitsStr!= null){
            hits=Integer.valueOf(hitsStr);
            hits++;
        } 
        map.put("hits" ,Integer.toString(hits));
        return "hits=" + hits;
       
    }

   
}
