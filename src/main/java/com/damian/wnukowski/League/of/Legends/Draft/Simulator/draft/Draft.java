package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import java.util.TimerTask;
import java.util.UUID;

public class Draft {
    private TimerTask timerTask;

    private String team1;
    private String team2;
    private String matchName;

    private UUID draftUUID;
    private UUID team1captainUUID;
    private UUID team2captainUUID;

    private boolean isTeam1Ready;
    private boolean isTeam2Ready;


    private String[] team1Bans;
    private String[] team2Bans;
    private String[] team1Picks;
    private String[] team2Picks;

    public Draft(String team1, String team2, UUID draftUUID, String matchName){
        this.isTeam1Ready = false;
        this.isTeam2Ready = false;

        this.team1 = team1;
        this.team2 = team2;
        this.draftUUID = draftUUID;
        this.matchName = matchName;

        team1captainUUID = UUID.randomUUID();
        team2captainUUID = UUID.randomUUID();

        team1Bans = new String[5];
        team2Bans = new String[5]; // 5 bans for each team

        team1Picks = new String[5];
        team2Picks = new String[5]; // 5 picks for each team

    }

    public class DraftUrl{
        private String team1url, team2url,audienceUrl;

        DraftUrl(){
            team1url = "/api/draft/"+draftUUID+"/"+team1captainUUID;
            team2url = "/api/draft/"+draftUUID+"/"+team2captainUUID;
            audienceUrl = "/api/draft/"+draftUUID;
        }

        public String getTeam1url() {
            return team1url;
        }

        public String getTeam2url() {
            return team2url;
        }

        public String getAudienceUrl() {
            return audienceUrl;
        }
    }

    //GETTERS

    DraftUrl getDraftUrl(){
        return new DraftUrl(); //gets URL of a draft based on a draft instance it is called from
    }

    boolean setReady(UUID captainUUID){
        if(captainUUID.equals(team1captainUUID)) {
            isTeam1Ready = true;
            return true;
        } else if (captainUUID.equals(team2captainUUID)) {
            isTeam2Ready = true;
            return true;
        }
        return false;
    }


    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public UUID getDraftUUID() {
        return draftUUID;
    }

    public String getMatchName() {
        return matchName;
    }

    public String[] getTeam1Bans() {
        return team1Bans;
    }

    public String[] getTeam2Bans() {
        return team2Bans;
    }

    public String[] getTeam1Picks() {
        return team1Picks;
    }

    public String[] getTeam2Picks() {
        return team2Picks;
    }

    public boolean isTeam1Ready() {
        return isTeam1Ready;
    }

    public boolean isTeam2Ready() {
        return isTeam2Ready;
    }
}
