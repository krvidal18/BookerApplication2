package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Image;
import com.example.demo.model.TravelPackage;
import com.example.demo.model.TravelService;
import com.example.demo.repository.TravelPackageRepository;

public class TravelPackageService {
	TravelPackageRepository travelPackageRepository;
	TravelServiceService travelServiceService;
	ImageService imageService;

	public TravelPackageService(TravelPackageRepository travelPackageRepository, ImageService imageService) {
		this.travelPackageRepository = travelPackageRepository;
		this.imageService = imageService;
	}
	
	@Autowired
	public void setTravelServiceService(TravelServiceService travelServiceService) {
		this.travelServiceService = travelServiceService;
	}



	@Transactional
	public List<TravelPackage> getAllTravelPackages() {
		return (List<TravelPackage>) travelPackageRepository.findAll();
	}

	@Transactional
	public List<TravelPackage> createTravelPackages(List<TravelPackage> travelPackages) {
		List<TravelPackage> travelPackageList = (List<TravelPackage>) travelPackageRepository.saveAll(travelPackages);
		for (TravelPackage travelPackage : travelPackageList) {
			List<TravelService> travelServices = travelPackage.getAvailableServiceList();
			for (TravelService travelService : travelServices) {
				travelService.setTravelPackage(travelPackage);
			}
			travelServiceService.createServices(travelServices);
			List<Image> images = travelPackage.getImages();
			for (Image image : images) {
				image.setTravelPackage(travelPackage);
			}
			imageService.createImages(images);
		}
		return travelPackageList;
	}

	@Transactional
	public void deleteTravelPackages(int[] travelPackageIds) {
		for (int id : travelPackageIds) {
			travelPackageRepository.deleteById(id);
		}
	}

	@Transactional
	public List<TravelPackage> updateTravelPackages(List<TravelPackage> travelPackages) {
		return createTravelPackages(travelPackages);
		/*
		 * List<TravelPackage> updatedTravelPackages = (List<TravelPackage>)
		 * travelPackageRepository.saveAll(travelPackages); for (TravelPackage
		 * travelPackage: updatedTravelPackages) {
		 * travelServiceService.updateServices(travelPackage.getAvailableServiceList());
		 * imageService.updateImages(travelPackage.getImages()); } return
		 * updatedTravelPackages;
		 */
	}

	@Transactional
	public TravelPackage getTravelPackageById(int travelPackageId) {
		return travelPackageRepository.findById(travelPackageId).get();
	}

	@Transactional
	public TravelPackage updateTravelPackageById(int travelPackageId) {
		return travelPackageRepository.save(travelPackageRepository.findById(travelPackageId).get());
	}

	@Transactional
	public void deleteTravelPackageById(int travelPackageId) {
		travelPackageRepository.delete(travelPackageRepository.findById(travelPackageId).get());
	}
}
