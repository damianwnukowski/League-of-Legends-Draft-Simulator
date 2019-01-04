package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = RequestMethod.POST, path="/createDraft")
    public Draft.DraftUrl createDraft(@RequestParam String team1, @RequestParam String team2, @RequestParam String matchName ){
        return draftService.createDraft(team1, team2, matchName);
    }

    @PostMapping(path = "/{draftUUID}/{captainUUID}/ready")
    public ResponseEntity setReady(@PathVariable UUID draftUUID, @PathVariable UUID captainUUID){
        if(draftService.setReady(draftUUID, captainUUID)){
            return new ResponseEntity(HttpStatus.OK);
        }
        else return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping(path = "/{draftUUID}")
    public Draft getDraft(@PathVariable UUID draftUUID){
        return draftService.getDraftState(draftUUID);

    }

    @GetMapping(path = "/showAllDrafts")
    public List<Draft> showAllDrafts(){
        return draftService.showAllDrafts();
    }
}
