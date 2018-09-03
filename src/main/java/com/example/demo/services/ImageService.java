package com.example.demo.services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;

public class ImageService {
	private ImageRepository imageRepository;

	public ImageService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@Transactional
	public List<Image> createImages(List<Image> images) {
		return (List<Image>) imageRepository.saveAll(images);
	}
	
	@Transactional
	public List<Image> updateImages(List<Image> images){
		return (List<Image>) imageRepository.saveAll(images);
	}
}
