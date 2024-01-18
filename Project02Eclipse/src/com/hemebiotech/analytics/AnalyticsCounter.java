package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnalyticsCounter {
	private static int headacheCount = 0;
	private static int rashCount = 0;
	private static int pupilCount = 0;
	ISymptomReader reader;
	ISymptomWriter writer;
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer){
		this.reader = reader;
		this.writer = writer;
	}

	List<String> getSymptoms(){
		return reader.getSymptoms();
	}

	public Map<String, Integer>
	countSymptoms(List<String> symptoms) {
		Map <String, Integer> symptomAndCount = new HashMap<>();
		symptoms.forEach(symptom ->{
			if (symptom.equals("headache")) {
				headacheCount++;
			}
			else if (symptom.equals("rash")) {
				rashCount++;
			}
			else if (symptom.contains("dialated pupils")) {
				pupilCount++;
			}
			});
		symptomAndCount.put("headache", headacheCount);
		symptomAndCount.put("rash", rashCount);
		symptomAndCount.put("dialated pupils", pupilCount);
		return symptomAndCount;
	}

	TreeMap<String, Integer> sortSymptoms(Map<String, Integer> symptomWithCounts){
		return new TreeMap<>(symptomWithCounts);
	}

	
	public static void main(String args[]) throws Exception {

		ISymptomReader reader = new ReadSymptomDataFromFile("Project02Eclipse\\symptoms.txt");
		ISymptomWriter writer = new WriteSymptomDataToFile();

		AnalyticsCounter analyticsCounter = new AnalyticsCounter(reader, writer);
		List<String> symptomLists = analyticsCounter.getSymptoms();
		Map<String, Integer> symptomWithCounts = analyticsCounter.countSymptoms(symptomLists);
		TreeMap<String, Integer> sortedSymptomsWithCounts = analyticsCounter.sortSymptoms(symptomWithCounts);

		writer.writeSymptoms(sortedSymptomsWithCounts, "result.out");
		

	}
}
