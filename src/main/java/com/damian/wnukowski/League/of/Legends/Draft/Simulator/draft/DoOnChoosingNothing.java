package com.damian.wnukowski.League.of.Legends.Draft.Simulator.draft;

import com.damian.wnukowski.League.of.Legends.Draft.Simulator.champions.Champions;


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
        String tempChoice;

        int turn = draft.getTurn();

        if(turn<=6 || (turn>=13 && turn<=16))
            tempChoice = "";
        else
            do{
                tempChoice = champions.getRandomChamp();
            }while(draft.getChoices().contains(tempChoice));

        draft.getChoices().set(turn-1, tempChoice);

        draft.setTurn(draft.getTurn()+1);
        if(draft.getTurn()<=20)
            draft.getTimer().schedule(new DoOnChoosingNothing(draft, champions), draft.CHOOSING_TIME+draft.DELAY_TIME);

        draft.setLastRefersh(System.currentTimeMillis());
    }
}
