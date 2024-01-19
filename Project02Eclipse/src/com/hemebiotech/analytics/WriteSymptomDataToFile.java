package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * This class is an implementation of ISymptomWriter interface
 * The implementation of the method is writing symptom and its count in an output file defined as parameter in the method
 * <b>writeSymptoms()</b>.
 */
public class WriteSymptomDataToFile implements ISymptomWriter {

    /**
     *
     * @param symptomsWithOccurrence This is a Map with symptom as key and the occurrence of the symptom as value.     *
     * The output file will not be sorted.
     */
    @Override
    public void writeSymptoms(Map<String, Integer> symptomsWithOccurrence, String filePath) {
        ;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            symptomsWithOccurrence.forEach((symptom, count)->{
                try {
                    writer.write(symptom+":"+count+"\n");
                    } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            });
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

    }
}
