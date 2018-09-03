package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.TravelPackageRepository;
import com.example.demo.repository.TravelServiceRepository;
import com.example.demo.services.ImageService;
import com.example.demo.services.TravelPackageService;
import com.example.demo.services.TravelServiceService;

@Configuration
public class BookerServicesConfig {

	@Bean
	public TravelPackageService travelPackageService(TravelPackageRepository travelPackageRepository, 
			ImageService imageService) {
		return new TravelPackageService(travelPackageRepository, imageService);
	}

	@Bean
	public TravelServiceService travelServiceService(TravelServiceRepository travelServiceRepository,
			ImageService imageService) {
		return new TravelServiceService(travelServiceRepository, imageService);
	}

	@Bean
	public ImageService imageService(ImageRepository imageRepository) {
		return new ImageService(imageRepository);
	}
}
