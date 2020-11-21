package application;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Backup {

    /**
     * makes a backup of all the files in the source folder and saves them in the backup folder
     * @param sourceFolder
     * @param backupFolder
     */
    public static void backup(File sourceFolder, File backupFolder) {
        //check if source folder exists
        if (!sourceFolder.exists()) {
            System.out.printf("%s \n", "BACKUP FAILED!");
            System.out.printf("folder at: %s does not exist", sourceFolder.getAbsolutePath());
            return;
        }
        //create backup folder if it does not exist
        if (!backupFolder.exists()) {
            try {
                System.out.printf("creating backup folder at: %s", backupFolder.getAbsolutePath());
                Files.createDirectory(Paths.get(backupFolder.getAbsolutePath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //for every file in the source folder
        for (File sourceFile : sourceFolder.listFiles()) {
            //determine the right backup path
            String backupPath = backupFolder + "\\" + sourceFile.getName();
            try {
                //delete old file if exists
                if (!sourceFile.isDirectory())
                    Files.delete(Paths.get(backupPath));
                //copy the source file into the backup path
                Files.copy(Paths.get(sourceFile.getAbsolutePath()), Paths.get(backupPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //recursive call of method to backup all the files in this directory too
            if (sourceFile.isDirectory()) {
                String newSourceFolder = sourceFolder + "\\" + sourceFile.getName();
                String newBackupFolder = backupFolder + "\\" + sourceFile.getName();
                backup(new File(newSourceFolder), new File(newBackupFolder));
            }
        }
    }
}
