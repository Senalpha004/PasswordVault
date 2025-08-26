package com.passwordvault;

//this class shows the logic/process of adding, deleting, loading, saving and searching entries
//CRUD operations

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;

public class VaultManager {
    //creating a list called entries to store the passwords
    private List<PasswordEntry> entries;
    private final String FileName = "vault.json";

    //loads the file as the system starts
    public VaultManager() {
        loadFromFile();
    }

    //loads the entries from the json file
    private void loadFromFile() {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<PasswordEntry>>(){}.getType();
            FileReader reader = new FileReader(FileName);
            entries = gson.fromJson(reader, listType);
            reader.close();
            if (entries == null) {
                entries = new ArrayList<>();
            }
        } catch (Exception e) {
            entries = new ArrayList<>();
        }
    }

    //saving the entries to the json file
    public void saveToFile() {
        try {
            Gson gson = new Gson();
            FileWriter writer = new FileWriter(FileName);
            gson.toJson(entries, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error saving to vault: " + e.getMessage());
        }
    }

    //adding a new password
    public void addEntry(PasswordEntry entry) {
        entries.add(entry);
        saveToFile();
        System.out.println("Successfully added to vault!");
    }

    //viewing all the saved entries
    public void viewAllEntries(){
        if (entries.isEmpty()) {
            System.out.println("No saved entries found :( ");
            return;
        }
        System.out.println("\nEntry/s found: ");
        for (PasswordEntry entry : entries) {
            System.out.println("\n");
            System.out.println(entry);
        }
    }

    //delete the saved entries by the platform name
    public void deleteEntry(String platform){
        boolean removed = entries.removeIf(entry -> entry.getPlatform().equalsIgnoreCase(platform));
        if (removed) {
            saveToFile();
            System.out.println("Entry for platform '" + platform + "' deleted.");
        } else {
            System.out.println("Platform does not exist!");
        }
    }

    //search by the platform's name
    public void searchEntry(String platform){
        boolean found = false;
        for (PasswordEntry entry : entries) {
            if (entry.getPlatform().equalsIgnoreCase(platform)) {
                System.out.println("Found entry/s: ");
                System.out.println(entry);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No details found for " + platform);
        }
    }
}
