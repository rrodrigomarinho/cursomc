package com.rodrigomarinho.cursomc.services;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public void uploadFile(String localFilePatch) {
		try {
			File file = new File(localFilePatch);
			LOG.info("Iniciando upload");
			amazonS3.putObject(new PutObjectRequest(bucketName, "teste", file));
			LOG.info("Finalizando upload");
		} catch (AmazonServiceException e) {
			LOG.info("AmazonServiceException: ".concat(e.getErrorMessage()));
			LOG.info("Status code: ".concat(e.getErrorCode()));
		}
		catch (AmazonClientException e) {
			LOG.info("AmazonClientException: ".concat(e.getMessage()));
		}
	}
}
