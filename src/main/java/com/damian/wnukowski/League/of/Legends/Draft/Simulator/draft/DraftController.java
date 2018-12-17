package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/api/draft/")
public class DraftController {

    private DraftService draftService;

    public DraftController(DraftService draftService){
        this.draftService = draftService;
    }

    @RequestMapping(method = RequestMethod.POST, path="/createDraft")
    public Draft.DraftUrl createDraft(@RequestParam String team1, @RequestParam String team2, @RequestParam String matchName ){
        return draftService.createDraft(team1, team2, matchName);
    }
}
