package com.example.CasinoDiscord.messageSender;


import com.example.CasinoDiscord.RussianRoulette.RussianRouletteBet;
import com.example.CasinoDiscord.dataSaving.SaveMessage;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Component
public class MessageSender {


    public static void rouletteGameNewBet(MessageChannel messageChannel, float amount, String bet, String name) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.CYAN);
        builder.setTitle("NEW BET");
        builder.addField("Bet summary", name + " has put " + amount +  " on " + bet.toUpperCase(Locale.ROOT), true);
        messageChannel.sendMessageEmbeds(builder.build()).queue();
    }

    public static void rouletteGameResults(MessageChannel messageChannel, HashMap<String, Float> winners, String textResult) {
        EmbedBuilder builder = new EmbedBuilder();
        StringBuilder names = new StringBuilder();
        StringBuilder money = new StringBuilder();
        for(String key : winners.keySet()) {
            names.append(key + "\n");
            money.append(winners.get(key) + "\n");
        }
        builder.setTitle("Roulette Results");
        builder.addField("Bet result", textResult, false);
        builder.addField("Names", names.toString(), true);
        builder.addField("Prizes", money.toString(), true);
        builder.setColor(Color.CYAN);

        messageChannel.sendMessageEmbeds(builder.build()).queue();

    }
    public static void insufficientBalance(MessageChannel messageChannel, float moneySet, float moneyHave) {
        EmbedBuilder builder = new EmbedBuilder();
        builder
                .setColor(Color.RED)
                .setTitle("You have insufficient Balance to make this command")
                .addField("Info", "Money put " + moneySet + " | " + moneyHave, false);

        messageChannel.sendMessageEmbeds(builder.build()).queue();
    }
    public static void coinFlipWon(MessageChannel messageChannel, String picUrl, float bet, String playerName, int newWinChance){
        EmbedBuilder builder = new EmbedBuilder();
        builder
                .setColor(Color.GREEN)
                .setTitle("You WON!")
                .setImage(picUrl)
                .setFooter("Your winning chance is now " + newWinChance +"%")
                .addField("Info", playerName + " has won " + bet * 2, false);
        messageChannel.sendMessageEmbeds(builder.build()).queue();
    }
    public static void coinFlipLost(MessageChannel messageChannel, String picUrl, float bet, String playerName, int newWinChance){
        EmbedBuilder builder = new EmbedBuilder();
        builder
                .setColor(Color.RED)
                .setTitle("You LOST!")
                .setImage(picUrl)
                .setFooter("Your winning chance is now " + newWinChance +"%")
                .addField("Info", playerName + " has lost " + bet, false);
        messageChannel.sendMessageEmbeds(builder.build()).queue();
    }
    public static void chanelUserMessagesInfo(MessageChannel messageChannel, List<SaveMessage> saveMessages, String username) {
        EmbedBuilder builder = new EmbedBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm.ss DD/MM/yyyy");

        builder
                .setColor(Color.CYAN)
                .setTitle("Info about "  + username.toUpperCase(Locale.ROOT));
        for (SaveMessage message : saveMessages) {
            builder.addField("Message on time " + formatter.format(message.getSendTime()), message.getMessageText(), false);
        }
        messageChannel.sendMessageEmbeds(builder.build()).queue();
    }


    public static void russianRouletteGameEnding(MessageChannel messageChannel, List<RussianRouletteBet> betList, int placeLost) throws InterruptedException {
        for (int i = 0; i < betList.size(); i++) {
           EmbedBuilder builder = new EmbedBuilder();
           builder.setColor(Color.CYAN);
           builder.setTitle("Player Result: ");
           if (i == placeLost) {
               betList.remove(i);
               i--;
               builder.addField( "Info", "Player " + betList.get(i).getUsername() + " has been KILLED", false);
           }
           else {
               builder.addField("Info" ,"Player " + betList.get(i).getUsername() + " has SURVIVED", false);
           }
           messageChannel.sendMessageEmbeds(builder.build()).queue();
           Thread.sleep(1000);
        }

    }
    public static void russianRouletteWrongBetAmount(MessageChannel messageChannel, float moneyPut, float moneyNeeded) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.CYAN);
        builder.setTitle("Wrong bet amount");
        builder.addField("Error message", "Money put: " + moneyPut + "| " + "Money needed: " + moneyNeeded ,false);
        messageChannel.sendMessageEmbeds(builder.build()).queue();
    }
}
