package com.hemebiotech.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class contains reader and writer which are reading symptoms from files and converting it into a map with symptoms and their occurrences.
 * It is then sorted and will be used in main method to output the result in a file.
 */
public class AnalyticsCounter {
	ISymptomReader reader;
	ISymptomWriter writer;
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer){
		this.reader = reader;
		this.writer = writer;
	}

	/**
	 * This method read the symptoms defined in a file and returns it as a list of String
	 * @return a list of symptoms from file defined as parameter in ReadSymptomDataFromFile reader instance
	 */
	List<String> getSymptoms(){
		return reader.getSymptoms();
	}

	/**
	 *
	 * @param symptoms which is a list of symptoms as String
	 * @return a map with symptoms as key and count which is the occurrence of a symptom in the file
	 */
	public Map<String, Integer>	countSymptoms(List<String> symptoms) {
		Map <String, Integer> symptomAndCount = new HashMap<>();
        for (String symptom : symptoms) {
            if (symptomAndCount.containsKey(symptom)) {
                Integer newValue = symptomAndCount.get(symptom) + 1;
                symptomAndCount.put(symptom, newValue);
            } else {
                symptomAndCount.put(symptom, 1);
            }
        }
        return symptomAndCount;
	}

	/**
	 *
	 * @param symptomWithCounts which is a map of symptoms and their occurrence as count
	 * @return the same map but sorted by key
	 */
	Map<String, Integer> sortSymptoms(Map<String, Integer> symptomWithCounts){
		return new TreeMap<>(symptomWithCounts);
	}

	
	public static void main(String args[]) throws Exception {

		ISymptomReader reader = new ReadSymptomDataFromFile("Project02Eclipse\\symptoms.txt");
		ISymptomWriter writer = new WriteSymptomDataToFile();

		AnalyticsCounter analyticsCounter = new AnalyticsCounter(reader, writer);
		List<String> symptomLists = analyticsCounter.getSymptoms();
		Map<String, Integer> symptomWithCounts = analyticsCounter.countSymptoms(symptomLists);
		Map<String, Integer> sortedSymptomsWithCounts = analyticsCounter.sortSymptoms(symptomWithCounts);

		writer.writeSymptoms(sortedSymptomsWithCounts, "result.out");
		

	}
}
