package com.hemebiotech.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class contains reader from {@link ISymptomReader} and writer from {@link ISymptomWriter} which are reading symptoms from file
 * and converting it into a map with symptoms and their occurrences.
 * One method {@link #countSymptoms(List symptomsList)} will be used to create a Map from a List and another {@link #sortSymptoms(Map symptomsSortedWithOccurrence)} to sort the symptoms by key name
 *
 *
 */
public class AnalyticsCounter {
	ISymptomReader reader;
	ISymptomWriter writer;
	public AnalyticsCounter(ISymptomReader reader, ISymptomWriter writer){
		this.reader = reader;
		this.writer = writer;
	}

	/**
	 * This method read the symptoms defined in a file and returns it as a list of String.
	 *
	 * @return symptomsList which a list of symptoms from file declared in constructor {@link ReadSymptomDataFromFile#ReadSymptomDataFromFile(String filePath)}
	 * and uses method {@link ReadSymptomDataFromFile#getSymptoms()} to convert it into a list.
	 */
	List<String> getSymptoms(){
		return reader.getSymptoms();
	}

	/**
	 * This method is taking a list of symptoms from {@link #getSymptoms()} method and putting symptoms as key and occurrence as value without duplicated symptoms
	 * @param symptomsList which is a list of symptoms as String
	 * @return symptomsWithOccurrence - This a map with symptoms as key and count which is the occurrence of a symptom in the file.
	 */
	public Map<String, Integer>	countSymptoms(List<String> symptomsList) {
		Map <String, Integer> symptomsWithOccurrence = new HashMap<>();
        for (String symptom : symptomsList) {
            if (symptomsWithOccurrence.containsKey(symptom)) {
                Integer newValue = symptomsWithOccurrence.get(symptom) + 1;
				symptomsWithOccurrence.put(symptom, newValue);
            } else {
				symptomsWithOccurrence.put(symptom, 1);
            }
        }
        return symptomsWithOccurrence;
	}

	/**
	 * This method is taking a Map {@link #countSymptoms(List symptomsWithOccurrence)} and sorting it by key name.
	 * @param symptomsWithOccurrence which is a map of symptoms and their occurrence as count.
	 * @return The same map but sorted by key name.
	 */
	Map<String, Integer> sortSymptoms(Map<String, Integer> symptomsWithOccurrence){
		return new TreeMap<>(symptomsWithOccurrence);
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
