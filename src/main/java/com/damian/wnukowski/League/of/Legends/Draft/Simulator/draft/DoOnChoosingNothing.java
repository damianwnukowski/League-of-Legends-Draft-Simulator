package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import com.damian.wnukowski.League.of.Legends.Draft.Simulator.champions.Champions;

import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;

class DoOnChoosingNothing extends TimerTask {
    private Draft draft;
    private Champions champions;


    DoOnChoosingNothing(Draft draft, Champions champions){
        this.draft = draft;
        this.champions = champions;
    }

    @Override
    public void run(){
        String[] choices;
        int turn = draft.getTurn();

        String tempChoice;
        String tempChoice2;
        List<String> tmp;
        List<String> tmp2;

        switch(turn){
            case 1:
                choices = draft.getTeam1Choices();
                choices[0] = "";
                break;
            case 2:
                choices = draft.getTeam2Choices();
                choices[0] = "";
                break;
            case 3:
                choices = draft.getTeam1Choices();
                choices[1] = "";
                break;
            case 4:
                choices = draft.getTeam2Choices();
                choices[1] = "";
                break;
            case 5:
                choices = draft.getTeam1Choices();
                choices[2] = "";
                break;
            case 6:
                choices = draft.getTeam2Choices();
                choices[2] = "";
                break;
            case 7:
                choices = draft.getTeam1Choices();
                tempChoice = champions.getRandomChamp();
                tmp = Arrays.asList(draft.getTeam1Choices());
                tmp2 = Arrays.asList(draft.getTeam2Choices());
                while( tmp.contains(tempChoice) || tmp2.contains(tempChoice) ){
                    tempChoice = champions.getRandomChamp();
                }//choosing randompick until its free
                choices[3] = tempChoice;
                break;
            case 8:
                choices = draft.getTeam2Choices();
                tempChoice = champions.getRandomChamp();
                tempChoice2 = champions.getRandomChamp();
                tmp = Arrays.asList(draft.getTeam1Choices());
                tmp2 = Arrays.asList(draft.getTeam2Choices());
                while( tmp.contains(tempChoice) || tmp2.contains(tempChoice) ) {
                    tempChoice = champions.getRandomChamp();
                }
                while( tmp.contains(tempChoice2) || tmp2.contains(tempChoice2) ) {
                    tempChoice2 = champions.getRandomChamp();
                }
                choices[3] = tempChoice;
                choices[4] = tempChoice2;
                break;
            case 9:
                choices = draft.getTeam1Choices();
                tempChoice = champions.getRandomChamp();
                tempChoice2 = champions.getRandomChamp();
                tmp = Arrays.asList(draft.getTeam1Choices());
                tmp2 = Arrays.asList(draft.getTeam2Choices());
                while( tmp.contains(tempChoice) || tmp2.contains(tempChoice) ) {
                    tempChoice = champions.getRandomChamp();
                }
                while( tmp.contains(tempChoice2) || tmp2.contains(tempChoice2) ) {
                    tempChoice2 = champions.getRandomChamp();
                }
                choices[4] = tempChoice;
                choices[5] = tempChoice2;
                break;
            case 10:
                choices = draft.getTeam2Choices();
                tempChoice = champions.getRandomChamp();
                tmp = Arrays.asList(draft.getTeam1Choices());
                tmp2 = Arrays.asList(draft.getTeam2Choices());
                while( tmp.contains(tempChoice) || tmp2.contains(tempChoice) ){
                    tempChoice = champions.getRandomChamp();
                }//choosing randompick until its free
                choices[5] = tempChoice;
                break;
            case 11:
                choices = draft.getTeam2Choices();
                choices[6] = "";
                break;
            case 12:
                choices = draft.getTeam1Choices();
                choices[6] = "";
                break;
            case 13:
                choices = draft.getTeam2Choices();
                choices[7] = "";
                break;
            case 14:
                choices = draft.getTeam1Choices();
                choices[7] = "";
                break;
            case 15:
                choices = draft.getTeam2Choices();
                tempChoice = champions.getRandomChamp();
                tmp = Arrays.asList(draft.getTeam1Choices());
                tmp2 = Arrays.asList(draft.getTeam2Choices());
                while( tmp.contains(tempChoice) || tmp2.contains(tempChoice) ){
                    tempChoice = champions.getRandomChamp();
                }//choosing randompick until its free
                choices[8] = tempChoice;
                break;
            case 16:
                choices = draft.getTeam1Choices();
                tempChoice = champions.getRandomChamp();
                tempChoice2 = champions.getRandomChamp();
                tmp = Arrays.asList(draft.getTeam1Choices());
                tmp2 = Arrays.asList(draft.getTeam2Choices());
                while( tmp.contains(tempChoice) || tmp2.contains(tempChoice) ) {
                    tempChoice = champions.getRandomChamp();
                }
                while( tmp.contains(tempChoice2) || tmp2.contains(tempChoice2) ) {
                    tempChoice2 = champions.getRandomChamp();
                }
                choices[8] = tempChoice;
                choices[9] = tempChoice2;
                break;
            case 17:
                choices = draft.getTeam2Choices();
                tempChoice = champions.getRandomChamp();
                tmp = Arrays.asList(draft.getTeam1Choices());
                tmp2 = Arrays.asList(draft.getTeam2Choices());
                while( tmp.contains(tempChoice) || tmp2.contains(tempChoice) ){
                    tempChoice = champions.getRandomChamp();
                }//choosing randompick until its free
                choices[9] = tempChoice;
                break;
            default:
                break;
        }

        draft.setTurn(draft.getTurn()+1);
        if(draft.getTurn()<=17)
            draft.getTimer().schedule(new DoOnChoosingNothing(draft, champions), 27000);

        draft.setLastRefersh(System.currentTimeMillis());
    }
}
