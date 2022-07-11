package com.example.CasinoDiscord.RussianRoulette;

import com.example.CasinoDiscord.domains.Chanel;
import com.example.CasinoDiscord.domains.ChanelUser;
import com.example.CasinoDiscord.domains.User;
import com.example.CasinoDiscord.domains.UserChanelId;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PayRussianRoulettePrices {

    private final ChanelUserRepo chanelUserRepo;

    private final UsersRepo usersRepo;

    private final ChanelsRepo chanelsRepo;

    public PayRussianRoulettePrices(ChanelUserRepo chanelUserRepo, UsersRepo usersRepo, ChanelsRepo chanelsRepo) {
        this.chanelUserRepo = chanelUserRepo;
        this.usersRepo = usersRepo;
        this.chanelsRepo = chanelsRepo;
    }


    public void payPrizes(List<RussianRouletteBet> rouletteBetList, float betSize) {
        float betToPay = betSize * (rouletteBetList.size() + 1) / rouletteBetList.size();
        List<ChanelUser> chanelUserList = new ArrayList<>();

        for (RussianRouletteBet rouletteBet : rouletteBetList) {
            User user = usersRepo.findUserByUserId(rouletteBet.getRussianRouletteBetId().getUser().getUserId()).get();
            Chanel chanel = chanelsRepo.findChanelByChannelId(rouletteBet.getRussianRouletteBetId().getRussianRouletteTable().getRussianRouletteTableId().getChanel().getChannelId()).get();
            chanelUserList.add(chanelUserRepo.findChanelUserByUserChanelId(new UserChanelId(user, chanel)).get());
        }
        for (ChanelUser chanelUser : chanelUserList) {
            chanelUser.setMoney(chanelUser.getMoney() + betToPay);
        }
        chanelUserRepo.saveAll(chanelUserList);
    }
}
