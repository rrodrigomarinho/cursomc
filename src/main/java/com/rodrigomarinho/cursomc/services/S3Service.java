package com.rodrigomarinho.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {

	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream inputStream = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return uploadFile(inputStream, fileName, contentType);
		} catch (IOException e) {
			throw new RuntimeException("Erro de IO: " + e.getMessage());
		}
	}

	public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
		try {
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(contentType);
			LOG.info("Iniciando upload");
			amazonS3.putObject(bucketName, fileName, inputStream, objectMetadata);
			LOG.info("Finalizando upload");
			return amazonS3.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI.");
		}
	}
}
