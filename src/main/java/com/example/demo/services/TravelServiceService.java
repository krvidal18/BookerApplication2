package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Image;
import com.example.demo.model.TravelPackage;
import com.example.demo.model.TravelService;
import com.example.demo.repository.TravelServiceRepository;

public class TravelServiceService {
	private TravelServiceRepository travelServiceRepository;
	private TravelPackageService travelPackageService;
	private ImageService imageService;

	public TravelServiceService(TravelServiceRepository travelServiceRepository, ImageService imageService) {
		super();
		this.travelServiceRepository = travelServiceRepository;
		this.imageService = imageService;
	}

	@Autowired
	public void setTravelPackageService(TravelPackageService travelPackageService) {
		this.travelPackageService = travelPackageService;
	}

	@Transactional
	public List<TravelService> createServices(List<TravelService> services) {
		List<TravelService> servicesList = (List<TravelService>) travelServiceRepository.saveAll(services);
		for (TravelService service : servicesList) {
			List<Image> images = service.getImages();
			for (Image image : images) {
				image.setTravelService(service);
			}
			imageService.createImages(images);
		}
		return servicesList;
	}

	@Transactional
	public List<TravelService> updateServices(List<TravelService> services) {
		List<TravelService> updatedServices = (List<TravelService>) travelServiceRepository.saveAll(services);
		for (TravelService service : updatedServices) {
			imageService.updateImages(service.getImages());
		}
		return updatedServices;
	}

	@Transactional
	public void getServicesByTravelPackageId(int travelPackageId) {
		TravelPackage travelPackage = travelPackageService.getTravelPackageById(travelPackageId);
		List<TravelService> services = travelPackage.getAvailableServiceList();
		for (TravelService service : services) {
			System.out.println(service.getServiceId());
		}
	}

}
