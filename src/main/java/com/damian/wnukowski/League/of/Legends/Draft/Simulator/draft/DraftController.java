package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("/api/draft/")
public class DraftController {

    private DraftService draftService;

    public DraftController(DraftService draftService){
        this.draftService = draftService;
    }

    @PostMapping(path="/createDraft")
    public Draft.DraftUrl createDraft(@RequestParam String team1, @RequestParam String team2, @RequestParam String matchName ){
        return draftService.createDraft(team1, team2, matchName);
    }

    @PostMapping(path = "/{draftUUID}/{captainUUID}/ready")
    public String setReady(@PathVariable UUID draftUUID, @PathVariable UUID captainUUID){
        if(draftService.setReady(draftUUID, captainUUID))
            return "Ready";
        return "Error";
    }

    @PostMapping(path = "/{draftUUID}/{captainUUID}/choose")
    public String choose(@PathVariable UUID draftUUID, @PathVariable UUID captainUUID, @RequestParam int turn,
                                 @RequestParam String champion){
        return draftService.choose(draftUUID, captainUUID, turn, champion);
    }

    //So frontend can know based on captainUUID which team he is and do actions based on that(like activating buttons)
    @GetMapping(path = "/{draftUUID}/{captainUUID}/whichTeam")
    public String whichTeam(@PathVariable UUID draftUUID, @PathVariable UUID captainUUID){
        return draftService.whichTeam(draftUUID, captainUUID);
    }

    //So frontend can know based on captainUUID which team he is and do actions based on that(like activating buttons)
    @GetMapping(path = "/{draftUUID}/whichTeam")
    public String whichTeam(@PathVariable UUID draftUUID){
        return draftService.whichTeam(draftUUID, null);
    }

    @GetMapping(path = "/{draftUUID}")
    public Draft getDraft(@PathVariable UUID draftUUID){
        return draftService.getDraftState(draftUUID);
    }

    //in case of url with two UUID
    @GetMapping(path = "/{draftUUID}/{captainUUID}")
    public Draft getDraft2(@PathVariable UUID draftUUID, @PathVariable UUID captainUUID){
        return draftService.getDraftState(draftUUID);
    }


    @GetMapping(path = "/showAllDrafts")
    public List<Draft> showAllDrafts(){
        return draftService.showAllDrafts();
    }
}
