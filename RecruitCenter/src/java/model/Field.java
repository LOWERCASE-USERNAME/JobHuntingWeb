/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dell
 */
public class Field {
    public static final String[] FIELD = {"",
        "Agriculture, Food & Natural Resources",
        "Architecture & Construction",
        "Arts, Audio/Video Technology & Communications",
        "Business Management & Administration",
        "Education & Training",
        "Finance",
        "Government & Public Administration",
        "Health Science",
        "Hospitality & Tourism",
        "Human Services",
        "Information Technology",
        "Law, Public Safety, Corrections & Security",
        "Manufacturing",
        "Marketing",
        "Science, Technology, Engineering & Mathematics",
        "Transportation, Distribution & Logistics"
    };

    public static int findFieldIndex(String input) {
        for (int i = 0; i < FIELD.length; i++) {
            if (FIELD[i].equals(input)) {
                return i + 1; // Adding 1 to match the option value
            }
        }
        return -1;  // Value not found in the array
    }
}
