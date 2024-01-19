package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the implementation of ISymptomReader interface with its method {@link ISymptomReader#getSymptoms()}
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filePath;
	
	/**
	 * 
	 * @param filePath a full or partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filePath) {
		this.filePath = filePath;
	}

	/**
	 * This method is returning a list from a file defined in the constructor {@link ReadSymptomDataFromFile#ReadSymptomDataFromFile(String filePath)}
	 * @return symptomsList - This is the list of symptoms as String values which can be duplicated
	 */
	@Override

    public List<String> getSymptoms() {
		ArrayList<String> symptomsList = new ArrayList<String>();
		
		if (filePath != null) {
			try {
				BufferedReader reader = new BufferedReader (new FileReader(filePath));
				String line = reader.readLine();
				
				while (line != null) {
					symptomsList.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return symptomsList;
	}

}
