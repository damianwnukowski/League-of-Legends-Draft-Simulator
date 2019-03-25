package com.damian.wnukowski.League.of.Legends.Draft.Simulator.application;

import com.damian.wnukowski.League.of.Legends.Draft.Simulator.champions.Champions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public Champions champions(){
        List<String> champions = new ArrayList<>();
        Resource resource = new ClassPathResource("champions.txt");


        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(resource.getInputStream());
        }catch (Exception ex){
            ex.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(inputStreamReader)) {
            String line;

            while ((line = br.readLine()) != null) {
                champions.add(line);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return new Champions(champions);
    }

}
