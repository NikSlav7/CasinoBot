package com.example.CasinoDiscord;

import com.example.CasinoDiscord.Game.CheckRoulleteWinners;
import com.example.CasinoDiscord.Game.Roulette;
import com.example.CasinoDiscord.Game.RouletteBetDeterminator;
import com.example.CasinoDiscord.JDBCS.JDBCRoulette;
import com.example.CasinoDiscord.MessageControllers.*;
import com.example.CasinoDiscord.MessageEditors.MessageEditor;
import com.example.CasinoDiscord.messageSender.MessageSender;
import com.example.CasinoDiscord.repositories.ChanelUserRepo;
import com.example.CasinoDiscord.repositories.ChanelsRepo;
import com.example.CasinoDiscord.JDBCS.JDBCChannels;
import com.example.CasinoDiscord.repositories.MessageChannelRepo;
import com.example.CasinoDiscord.repositories.UsersRepo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
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
	public static String MessageStartingKey = "$";
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

		try {
			JDA jda = JDABuilder.createDefault
							("OTcyMzkwNDU1NzU1MDI2NTAy.GnNZjw.Un8JTpw88rIIWGJ7Yy4l0Ro33Ea3p27XtCyHEI",  GatewayIntent.GUILD_MEMBERS)

					.addEventListeners(new CreateChannelTableManager(jdbcChannels))
					.addEventListeners(new InfoEventManager(jdbcChannels, application.getBean(ChanelsRepo.class), application.getBean(ChanelUserRepo.class)))
					.addEventListeners(new AddMoneyManager(jdbcChannels, messageEditor))
//					.addEventListeners(new CheckIfChannelExistsManager(jdbcChannels))
//					.addEventListeners(new CheckIfChannelUserExists(application.getBean(ChanelsRepo.class), jdbcChannels))
					.addEventListeners(new Roulette(usersRepo, jdbcRoulette, jdbcChannels, betDeterminator, chanelUserRepo, chanelsRepo, rouletteMessages))
					.addEventListeners(new MoneyTransactionManager(messageEditor, jdbcChannels))
									.build();

			System.out.println(jda.getToken());
		} catch (LoginException e) {

		}
		CheckRoulleteWinners checkRoulleteWinners = new CheckRoulleteWinners(messageChannelRepo, messageEditor);
		checkRoulleteWinners.checkExpiredGames();

	}





}
