package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import java.util.TimerTask;
import java.util.UUID;

public class Draft {
    private TimerTask timerTask;
    private String team1;
    private String team2;
    private UUID draftUUID;
    private UUID team1captainUUID;
    private UUID team2captainUUID;
    private UUID audienceUUID;
    private String matchName;
    private String[] team1Bans;
    private String[] team2Bans;
    private String[] team1Picks;
    private String[] team2Picks;

    public Draft(String team1, String team2, UUID draftUUID, String matchName){
        this.team1 = team1;
        this.team2 = team2;
        this.draftUUID = draftUUID;
        this.matchName = matchName;

        team1captainUUID = UUID.randomUUID();
        team2captainUUID = UUID.randomUUID();
        audienceUUID = UUID.randomUUID();

        team1Bans = new String[5];
        team2Bans = new String[5]; // 5 bans for each team

        team1Picks = new String[5];
        team2Picks = new String[5]; // 5 picks for each team

    }

    public class DraftUrl{
        private String team1url, team2url,audienceUrl;
        public DraftUrl(){
            team1url = "/api/draft/"+draftUUID+"/"+team1captainUUID;
            team2url = "/api/draft/"+draftUUID+"/"+team2captainUUID;
            audienceUrl = "/api/draft/"+draftUUID+"/"+audienceUUID;
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

    public DraftUrl getDraftUrl(){
        return new DraftUrl();
    }
}
