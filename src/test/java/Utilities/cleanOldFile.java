package Utilities;

import java.io.File;

public class cleanOldFile {
	
	public static void cleanOldFiles(String folderPath) {
        File folder = new File(folderPath);

        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        if (file.delete()) {
                            System.out.println("Deleted old file: " + file.getName());
                        } else {
                            System.out.println("Failed to delete: " + file.getName());
                        }
                    } else if (file.isDirectory()) {
                        cleanOldFiles(file.getAbsolutePath()); // recursive deletion
                        file.delete();
                    }
                }
            }
        } else {
            if (folder.mkdir()) {
                System.out.println("Created folder: " + folderPath);
            }
        }
    }

}
