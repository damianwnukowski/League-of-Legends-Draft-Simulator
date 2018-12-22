package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DraftService {
    private HashMap<UUID,Draft> drafts;

    public DraftService(){
        drafts = new HashMap<>();
    }

    Draft.DraftUrl createDraft(String team1, String team2, String matchName){
        UUID randomId = UUID.randomUUID();
        Draft draft = new Draft(team1, team2, randomId , matchName);
        drafts.put(randomId, draft);
        return draft.getDraftUrl();
    }

    List<Draft> showAllDrafts(){
        ArrayList<Draft> result = new ArrayList<>();
        for (Map.Entry<UUID, Draft> draft : drafts.entrySet() ) {
            result.add(draft.getValue());
        }
        return result;
    }

    Draft getDraftState(UUID draftUUID){
        return drafts.get(draftUUID);
    }

    boolean setReady(UUID draftUUID, UUID captainUUID){
        Draft draft = drafts.get(draftUUID);
        if(draft == null)
            return false;

        return draft.setReady(captainUUID);
    }
}
