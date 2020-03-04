package com.rodrigomarinho.cursomc.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rodrigomarinho.cursomc.services.exceptions.MyFileException;

@Service
public class ImageService {
	
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile)  {
		String extensao = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		
		if (!"png".equals(extensao) && !"jpg".equals(extensao)) {
			throw new MyFileException("Somente imagens com extensão .PNG e .JPG são permitidas.");
		}
		
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(extensao)) {
				bufferedImage = pngToJpg(bufferedImage);
			}
			return bufferedImage;
		} catch (IOException e) {
			throw new MyFileException("Erro ao ler arquivo.");
		}
	}

	public BufferedImage pngToJpg(BufferedImage bufferedImage) {
		BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
		return newBufferedImage;
	}
	
	public InputStream getInputStream(BufferedImage bufferedImage, String extensao) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, extensao, byteArrayOutputStream);
			return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		} catch (IOException e) {
			throw new MyFileException("Erro ao ler arquivo.");
		}
	}
	
	public BufferedImage cropSquare(BufferedImage bufferedImage) {
		int min = (bufferedImage.getHeight() <= bufferedImage.getWidth()) ? bufferedImage.getHeight() : bufferedImage.getWidth();
		return Scalr.crop(bufferedImage, 
				(bufferedImage.getWidth() / 2) - (min / 2), 
				(bufferedImage.getHeight() / 2) - (min / 2),
				min,
				min);
	}
	
	public BufferedImage resize(BufferedImage bufferedImage, int size) {
		return Scalr.resize(bufferedImage, Scalr.Method.ULTRA_QUALITY, size);
	}

}
