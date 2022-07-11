package com.example.CasinoDiscord;

import com.example.CasinoDiscord.Coinflip.CoinFlip;
import com.example.CasinoDiscord.Roulette.CheckRoulleteWinners;
import com.example.CasinoDiscord.Roulette.Roulette;
import com.example.CasinoDiscord.Roulette.RouletteBetDeterminator;
import com.example.CasinoDiscord.JDBCS.JDBCMessage;
import com.example.CasinoDiscord.JDBCS.JDBCRoulette;
import com.example.CasinoDiscord.JDBCS.JDBCRussianRoulette;
import com.example.CasinoDiscord.MessageControllers.*;
import com.example.CasinoDiscord.MessageEditors.MessageEditor;
import com.example.CasinoDiscord.RussianRoulette.RussianRoulette;
import com.example.CasinoDiscord.messageSender.MessageSender;
import com.example.CasinoDiscord.repositories.*;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class CasinoDiscordApplication {
	public static final String MessageStartingKey = "$";
	public static ApplicationContext application;


	public static void main(String[] args) {
		application = SpringApplication.run(CasinoDiscordApplication.class); //creating application context


		JDBCChannels jdbcChannels = application.getBean(JDBCChannels.class);

		MessageEditor messageEditor = application.getBean(MessageEditor.class);

		JDBCRoulette jdbcRoulette = application.getBean(JDBCRoulette.class);

		RouletteBetDeterminator betDeterminator = application.getBean(RouletteBetDeterminator.class);

		UsersRepo usersRepo = application.getBean(UsersRepo.class);

		ChanelUserRepo chanelUserRepo = application.getBean(ChanelUserRepo.class);

		ChanelsRepo chanelsRepo = application.getBean(ChanelsRepo.class);

		MessageSender rouletteMessages = application.getBean(MessageSender.class);

		MessageChannelRepo messageChannelRepo = application.getBean(MessageChannelRepo.class);

		SavedMessagesRepo savedMessagesRepo = application.getBean(SavedMessagesRepo.class);

		SavedRouletteBetsRepo savedRouletteBetsRepo = application.getBean(SavedRouletteBetsRepo.class);

		JDBCMessage jdbcMessage = application.getBean(JDBCMessage.class);

		RussianRouletteTablesRepo russianRouletteTablesRepo = application.getBean(RussianRouletteTablesRepo.class);

		RussianRouletteBetsRepo russianRouletteBetsRepo = application.getBean(RussianRouletteBetsRepo.class);

		JDBCRussianRoulette jdbcRussianRoulette = application.getBean(JDBCRussianRoulette.class);


		try {
			JDA jda = JDABuilder.createDefault
							("OTcyMzkwNDU1NzU1MDI2NTAy.GnNZjw.Un8JTpw88rIIWGJ7Yy4l0Ro33Ea3p27XtCyHEI")
					.setChunkingFilter(ChunkingFilter.ALL)
					.enableIntents(GatewayIntent.GUILD_MEMBERS)
					.addEventListeners(new CreateChannelTableManager(jdbcChannels))
					.addEventListeners(new InfoEventManager(jdbcChannels, application.getBean(ChanelsRepo.class), application.getBean(ChanelUserRepo.class), usersRepo))
					.addEventListeners(new AddMoneyManager(jdbcChannels, messageEditor))
//					.addEventListeners(new CheckIfChannelExistsManager(jdbcChannels))
//					.addEventListeners(new CheckIfChannelUserExists(application.getBean(ChanelsRepo.class), jdbcChannels))
					.addEventListeners(new Roulette(usersRepo, jdbcRoulette, jdbcChannels, betDeterminator, chanelUserRepo, chanelsRepo, rouletteMessages, savedRouletteBetsRepo))
					.addEventListeners(new MoneyTransactionManager(messageEditor, jdbcChannels))
					.addEventListeners(new SaveNewMessage( savedMessagesRepo, chanelsRepo, usersRepo))
					.addEventListeners(new CoinFlip(usersRepo, chanelsRepo, chanelUserRepo, jdbcChannels))
					.addEventListeners(new GetUserMessagesManager(messageEditor, jdbcMessage))
					.addEventListeners(new RussianRoulette(jdbcRussianRoulette, russianRouletteTablesRepo, russianRouletteBetsRepo, usersRepo, chanelsRepo, jdbcChannels))
									.build();

			System.out.println(jda.getToken());
		} catch (LoginException e) {

		}
		CheckRoulleteWinners checkRoulleteWinners = new CheckRoulleteWinners(messageChannelRepo, messageEditor);
		checkRoulleteWinners.checkExpiredGames();

	}





}
