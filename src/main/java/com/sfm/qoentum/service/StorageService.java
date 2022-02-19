package com.sfm.qoentum.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.sfm.qoentum.config.Constants;

@Service
public class StorageService {

	private String path = Constants.dossierGeoJSON;
	private String baseURL = Constants.urlAccessGeoJSON;
	
	private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
	private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

	public String uploadResult(String contenu, String fileName) {

		String retour = "";
		FileWriter fileWriter = null;

		try {
			
			fileName = makeSlug(fileName);
			fileName = System.currentTimeMillis()+"_"+ fileName;

			String filePath = path + File.separator + fileName + ".geojson";
			fileWriter = new FileWriter(filePath);
			fileWriter.write(contenu);

			File f = new File(filePath);
			if (f.exists()) {
				f.setReadable(true, false);
			}

			retour = baseURL + fileName + ".geojson";
		} catch (Exception e) {
			e.printStackTrace();
			retour = "ERROR";
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				retour = "ERROR";
			}
		}

		return retour;

	}
	
	public void saveMapFile(String contenu, String fileName) {
		FileWriter fileWriter = null;
		try {
			String filePath = path + File.separator + fileName;
			fileWriter = new FileWriter(filePath);
			fileWriter.write(contenu);

			File f = new File(filePath);
			if (f.exists()) {
				f.setReadable(true, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String makeSlug(String input) {
		if (input == null)
			throw new IllegalArgumentException();

		String nowhitespace = WHITESPACE.matcher(input).replaceAll("_");
		String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
		String slug = NONLATIN.matcher(normalized).replaceAll("");
		return slug.toLowerCase(Locale.ENGLISH);
	}

}
