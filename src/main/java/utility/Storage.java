package utility;

import tasks.DeadLine;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** A class to store the Task data */
public class Storage {
    private final String storagePath;

    /**
     * Creates an instance of a Storage object.
     * @param storagePath Where data is stored.
     */
    public Storage(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Saves a list of tasks to local hard disk.
     * @param tasks Is tasks to be saved.
     * @throws IOException When save fails.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File saveFile = new File(storagePath);
        if (!saveFile.exists()) {
            File dir = new File("./data");
            boolean dirCreated = dir.mkdir();
            if (dirCreated && !saveFile.createNewFile()) {
                throw new FileNotFoundException("Could not create file " + saveFile.getAbsolutePath());
            }
        }
        assert (saveFile.exists());

        FileWriter fw = new FileWriter(storagePath);
        for (Task task : tasks) {
            fw.write(task.getSaveFormat() + "\n");
        }
        fw.close();
    }

    /**
     * Loads saved tasks from local hard disk.
     * @return A list of tasks.
     * @throws IOException When load fails.
     */
    public ArrayList<Task> load() throws IOException {
        File saveFile = new File(storagePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!saveFile.exists()) {
            return tasks;
        }
        Scanner sc = new Scanner(saveFile);

        String line;
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            switch (line.charAt(0)) {
            case 'T':
                tasks.add(Todo.load(line));
                break;
            case 'D':
                tasks.add(DeadLine.load(line));
                break;
            case 'E':
                tasks.add(Event.load(line));
                break;
            }
        }
        sc.close();
        return tasks;
    }
}
