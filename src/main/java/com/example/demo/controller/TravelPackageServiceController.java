package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TravelPackage;
import com.example.demo.services.TravelPackageService;
import com.example.demo.services.TravelServiceService;

@RestController
@RequestMapping("/travel-packages")
public class TravelPackageServiceController {
	TravelPackageService travelPackageService;
	TravelServiceService travelServiceService;

	public TravelPackageServiceController(TravelPackageService travelPackageService,
			TravelServiceService travelServiceService) {
		super();
		this.travelPackageService = travelPackageService;
		this.travelServiceService = travelServiceService;
	}

	@GetMapping
	public List<TravelPackage> getTravelPackages() {
		return travelPackageService.getAllTravelPackages();
	}

	@PostMapping
	public List<TravelPackage> createTravelPackages(@RequestBody List<TravelPackage> travelPackages) {
		return travelPackageService.createTravelPackages(travelPackages);
	}

	@DeleteMapping
	public void deleteTravelPackages(@RequestParam int[] travelPackageIds) {
		travelPackageService.deleteTravelPackages(travelPackageIds);
	}

	@PutMapping
	public List<TravelPackage> updateTravelPackages(@RequestBody List<TravelPackage> travelPackages) {
		return travelPackageService.updateTravelPackages(travelPackages);
	}

	@GetMapping("/{travelPackageId}")
	public TravelPackage getTravelPackageById(@PathVariable int travelPackageId) {
		return travelPackageService.getTravelPackageById(travelPackageId);
	}

	@PutMapping("/{travelPackageId")
	public TravelPackage updateTravelPackageById(@PathVariable int travelPackageId) {
		return travelPackageService.updateTravelPackageById(travelPackageId);
	}

	@DeleteMapping("/{travelPackageId}")
	public void deleteTravelPackageById(@PathVariable int travelPackageId) {
		travelPackageService.deleteTravelPackageById(travelPackageId);
	}

	@GetMapping("/travel-packages/{travelPackageId}/services")
	public void getServicesbyTravelPackageId(@PathVariable int travelPackageId) {
		travelServiceService.getServicesByTravelPackageId(travelPackageId);
	}

}
