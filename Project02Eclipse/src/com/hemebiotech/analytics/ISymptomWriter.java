package com.hemebiotech.analytics;

import java.util.Map;

/**
 * The interface writes data in a file from a Map with symptom as key and the number of occurrence of that symptom as value.
 * return type is void as it writes into an output file defined in parameters.
 */

public interface ISymptomWriter {

    /**
     *
     * @param symptomsWithOccurrence is the Map with symptoms and their occurrence
     * @param filePath is the path of the output file
     */
    public void writeSymptoms(Map<String, Integer> symptomsWithOccurrence, String filePath);
}
