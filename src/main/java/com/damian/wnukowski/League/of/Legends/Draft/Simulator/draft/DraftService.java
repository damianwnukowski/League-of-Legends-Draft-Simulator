package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import com.damian.wnukowski.League.of.Legends.Draft.Simulator.champions.Champions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DraftService {
    private HashMap<UUID,Draft> drafts;
    private Champions champions;

    @Autowired
    public DraftService(Champions champions){
        drafts = new HashMap<>();
        this.champions = champions;
    }


    public Draft.DraftUrl createDraft(String team1, String team2, String matchName){
        UUID randomId = UUID.randomUUID();
        Draft draft = new Draft(team1, team2, randomId , matchName);
        drafts.put(randomId, draft);
        return draft.getDraftUrl();
    }

    public String choose(UUID draftUUID, UUID captainUUID, int turn, String champion){
        Draft draft = drafts.get(draftUUID);
        if(draft == null) return "No draft with such id";
        if(!draft.isTeam2Ready() || !draft.isTeam1Ready()) return "Teams aren't ready";
        return draft.choose(captainUUID, turn, champion, champions);
    }

    public String whichTeam(UUID draftUUID, UUID captainUUID){
        Draft d = drafts.get(draftUUID);
        if(d==null)
            return "none";
        if(captainUUID == null)
            return "audience";

        return d.whichTeam(captainUUID);
    }

    public List<Draft> showAllDrafts(){
        ArrayList<Draft> result = new ArrayList<>();
        for (Map.Entry<UUID, Draft> draft : drafts.entrySet() ) {
            result.add(draft.getValue());
        }
        return result;
    }

    public Draft getDraftState(UUID draftUUID){
        return drafts.get(draftUUID);
    }

    public boolean setReady(UUID draftUUID, UUID captainUUID){
        Draft draft = drafts.get(draftUUID);
        if(draft == null)
            return false;

        boolean result = draft.setReady(captainUUID); //true if captainUUID matches else false so controller handles it
        if(draft.isTeam1Ready() && draft.isTeam2Ready()){
            startDraft(draft); //check if two teams are ready, if so then start draft
        }
        return result;
    }

    //start draft and activate timer that will do auto choosing if user does nothing
    private void startDraft(Draft draft){
        draft.setTurn(1);
        draft.setTimer(new Timer());
        draft.setLastRefersh(System.currentTimeMillis());
        draft.getTimer().schedule(new DoOnChoosingNothing(draft, champions), draft.CHOOSING_TIME+draft.DELAY_TIME);
    }
}
