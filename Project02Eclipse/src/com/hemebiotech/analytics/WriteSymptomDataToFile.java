package com.hemebiotech.analytics;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class is an implementation of ISymptomWriter interface
 * The implementation of the method is writing symptom and its count in an output file hardcoded in the method
 */
public class WriteSymptomDataToFile implements ISymptomWriter {

    /**
     *
     * @param symptoms this is a Map with symptom(String type) as key and count(Integer type) as value
     * the output file will be sorted by symptom name
     */
    @Override
    public void writeSymptoms(Map<String, Integer> symptoms) {
        TreeMap<String, Integer> sortedMap = new TreeMap<>(symptoms);
        try(FileWriter writer = new FileWriter ("result.out")){
            sortedMap.forEach((symptom, count)->{
                try {
                    writer.write(symptom+":"+count);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }catch (Exception ex){
            System.out.println(ex.printStackTrace());

        }

    }
}
