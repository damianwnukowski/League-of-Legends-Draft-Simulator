package com.damian.wnukowski.League.of.Legends.Draft.Simulator.application;

import com.damian.wnukowski.League.of.Legends.Draft.Simulator.champions.Champions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public Champions champions(){
        List<String> champions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/champions.txt"))) {
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
