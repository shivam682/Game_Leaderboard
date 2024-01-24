package com.backlight.leaderbod;

import com.backlight.leaderbod.config.PlayerCsvRepresentation;
import com.backlight.leaderbod.model.Player;
import com.backlight.leaderbod.repositries.PlayerRepo;
import com.backlight.leaderbod.service.PlayerService;
import com.backlight.leaderbod.service.impl.PlayerServiceImpl;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@SpringBootApplication
public class LeaderbodApplication implements CommandLineRunner {

	@Autowired
	private PlayerRepo playerRepo;


	public static void main(String[] args) {
		SpringApplication.run(LeaderbodApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	private Set<Player> parseCsv(MultipartFile file) throws IOException {
		try(Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			HeaderColumnNameMappingStrategy<PlayerCsvRepresentation> strategy =
					new HeaderColumnNameMappingStrategy<>();
			strategy.setType(PlayerCsvRepresentation.class);
			CsvToBean<PlayerCsvRepresentation> csvToBean =
					new CsvToBeanBuilder<PlayerCsvRepresentation>(reader)
							.withMappingStrategy(strategy)
							.build();

			return csvToBean.parse()
					.stream()
					.map(csvLine -> Player.builder()
							.Name(csvLine.getName())
							.Score(csvLine.getScore())
							.UID(csvLine.getUid())
							.TimeStamp(csvLine.getTimestamp())
							.Country(csvLine.getCountry())
							.build()
					)
					.collect(Collectors.toSet());
		}
	}
	@Override
	public void run(String... args) throws IOException {

//
			ClassPathResource resource = new ClassPathResource("leaderboard_data.csv");

			// Convert CSV file to MultipartFile
			MultipartFile multipartFile = new MockMultipartFile(
					"leaderboard_data.csv",
					resource.getFilename(),
					"text/csv",
					resource.getInputStream()
			);
			System.out.println(multipartFile.getOriginalFilename());
		Set<Player> players = parseCsv(multipartFile);
		this.playerRepo.saveAll(players);

	}

}
