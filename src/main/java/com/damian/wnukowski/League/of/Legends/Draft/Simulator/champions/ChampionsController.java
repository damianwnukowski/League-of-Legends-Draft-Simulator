package com.damian.wnukowski.League.of.Legends.Draft.Simulator.champions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class ChampionsController {
    private Champions champions;

    public ChampionsController(Champions champions){
        this.champions = champions;
    }

    @GetMapping(path = "/api/champions/")
    public Champions getAllChampions(){
        return this.champions;
    }
}
