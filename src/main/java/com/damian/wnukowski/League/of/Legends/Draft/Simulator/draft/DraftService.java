package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.UUID;

@Service
public class DraftService {
    private HashMap<UUID,Draft> drafts;

    public DraftService(){
        drafts = new HashMap<>();
    }

    public Draft.DraftUrl createDraft(String team1, String team2, String matchName){
        UUID randomId = UUID.randomUUID();
        Draft draft = new Draft(team1, team2, randomId , matchName);
        drafts.put(randomId, draft);
        return draft.getDraftUrl();
    }
}
