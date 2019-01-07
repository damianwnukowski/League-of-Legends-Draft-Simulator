package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import com.damian.wnukowski.League.of.Legends.Draft.Simulator.champions.Champions;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.*;

public class Draft {
    public final long CHOOSING_TIME = 27000; //time to choose for each turn (assuming it will always be equal for each turn)
    public final long DELAY_TIME = 500; //timer will lock for us X seconds after last possible second user could inpact in order to make some room which ensures us that timer will run only if user didn't pick anything
    public final int[] TURN_MAPPING = {1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 1, 2, 2, 1, 2, 1, 2, 1, 1,2};
    //1 indicates player1
    //2 indicates player2


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

    private List<String> choices;

    public Draft(String team1, String team2, UUID draftUUID, String matchName){
        this.isTeam1Ready = false;
        this.isTeam2Ready = false;

        this.team1 = team1;
        this.team2 = team2;
        this.draftUUID = draftUUID;
        this.matchName = matchName;

        team1captainUUID = UUID.randomUUID();
        team2captainUUID = UUID.randomUUID();

        choices = new ArrayList<>(); // 10 picks for each team, 1-3 are bans 4-6 picks 7-8 bans 9-10 picks
        for(int i =0 ; i<20; i++){
            choices.add(null);
        }
        //fill with null in order to make set() work

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

    public String choose(UUID captainUUID, int turn, String champion, Champions champions){
        if(turn<0 || turn>20)
            return "Turn must be between 0 and 20"; //to prevent incorrect turn number that would throw ArrayOutOfBoundsException
        if(this.turn!= turn)
            return "It is not your turn";

        //error will be thrown if we ha are at picking phase or ban phase that is not "" and champ name is incorrect
        if ((turn > 6 && (turn < 13 || turn > 16)) || !champion.equals("")) {
            if ((choices.contains(champion)) || !champions.exists(champion))
                return "Wrong champion name";
        }

        if(TURN_MAPPING[turn-1] == 1){
            if(!team1captainUUID.equals(captainUUID))
                return "team1 captain ids don't match";
        }else {
            if (!team2captainUUID.equals(captainUUID))
                return "team2 captain ids don't match";
        }
        if((System.currentTimeMillis()-lastRefersh) < CHOOSING_TIME){
            //And if we are set to go we can stop our timer that will perform action automatically
            timer.cancel();
            timer = new Timer(); //need to make new timer in order to use it again
            choices.set(turn-1, champion);

            this.turn++;
            if(this.turn<=20){
                timer.schedule(new DoOnChoosingNothing(this, champions), CHOOSING_TIME+DELAY_TIME);
            }
            setLastRefersh(System.currentTimeMillis());
        }else{
            return "You were too late";
        }

        return "Success";
    }

    String whichTeam(UUID captainUUID){
        if(captainUUID.equals(team1captainUUID))
            return "team1";
        if(captainUUID.equals(team2captainUUID))
            return "team2";
        return "none";
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

    @JsonIgnore
    DraftUrl getDraftUrl(){
        return new DraftUrl(); //gets URL of a draft based on a draft instance it is called from
    }

    @JsonIgnore
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

    public List<String> getChoices() {
        return choices;
    }

    public boolean isTeam1Ready() {
        return isTeam1Ready;
    }

    public boolean isTeam2Ready() {
        return isTeam2Ready;
    }

}
