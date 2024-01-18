package com.hemebiotech.analytics;

import java.util.Map;
import java.util.TreeMap;

/**
 * The interface write data in a file from a Map with key as symptom and value as count
 * return type is void as it writes into an output file
 */

public interface ISymptomWriter {

    public void writeSymptoms(Map<String, Integer> symptoms, String filePath);
}
