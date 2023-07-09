/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dell
 */
public class JobType {
    public static final String[] JOB_TYPES = {
        "Flexible",
        "Full-time",
        "Part-time",
        "Temporary",
        "Contract",
        "Freelance"
    };
    
    public static int findJobTypeIndex(String input) {
        for (int i = 0; i < JOB_TYPES.length; i++) {
            if (JOB_TYPES[i].equalsIgnoreCase(input)) {
                return i;
            }
        }
        return -1;  // Value not found in the array
    }
}
