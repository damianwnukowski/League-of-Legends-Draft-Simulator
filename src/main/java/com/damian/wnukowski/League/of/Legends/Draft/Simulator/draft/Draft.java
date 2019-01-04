package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class Draft {
    public final long CHOOSING_TIME = 27000; //time to choose for each turn (assuming it will always be equal for each turn)

    private int turn; //odd numbers for first player, even for second (1-17) where 1-6 are bans 7-10 are picks, 11-14
                    // are bans and 15-17 are picks

    private long lastRefersh; //time in miliseconds when last turn was executed (used to calculate time left)

    private Timer timer;

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
    private String[] team1Choices;
    private String[] team2Choices;

    public Draft(String team1, String team2, UUID draftUUID, String matchName){
        this.isTeam1Ready = false;
        this.isTeam2Ready = false;

        this.team1 = team1;
        this.team2 = team2;
        this.draftUUID = draftUUID;
        this.matchName = matchName;

        team1captainUUID = UUID.randomUUID();
        team2captainUUID = UUID.randomUUID();

        team1Choices = new String[10];
        team2Choices = new String[10]; // 10 picks for each team, 1-3 are bans 4-6 picks 7-8 bans 9-10 picks

        turn = 0;
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


    //SETTERS

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

    void setTurn(int turn){
        this.turn = turn;
    }

    void setTimer(Timer timer){
        this.timer = timer;
    }

    void setLastRefersh(long lastRefersh){
        this.lastRefersh = lastRefersh;
    }

    //GETTERS

    DraftUrl getDraftUrl(){
        return new DraftUrl(); //gets URL of a draft based on a draft instance it is called from
    }

    Timer getTimer(){
        return  timer;
    }

    public double getTimeRemaining(){
        return (27.0 - (( System.currentTimeMillis() - lastRefersh )/1000.0)); //in seconds
    }

    public int getTurn() {
        return turn;
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

    public String[] getTeam1Choices() {
        return team1Choices;
    }

    public String[] getTeam2Choices() {
        return team2Choices;
    }


    public boolean isTeam1Ready() {
        return isTeam1Ready;
    }

    public boolean isTeam2Ready() {
        return isTeam2Ready;
    }

}
