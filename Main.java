package ecoUser;

import java.io.*;

public class Main {

    public static String[] readWholeFileToStrings(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        StringBuilder sb = new StringBuilder();
        int tmp;
        while ((tmp = fis.read()) != -1) {
            sb.append((char) tmp);
        }
        return sb.toString().split("\n");
    }

    public static void processFile(File data, int maxResourceUse) {
        try {
            String[] persons = readWholeFileToStrings(data);
            File resultFile = new File(data.getParentFile().getPath() + "/result.csv");
            FileOutputStream fos = new FileOutputStream(resultFile);
            fos.write(persons[0].getBytes());
            User user = new User();
            for (int i = 1; i < persons.length; i++) {
                ObjectInputStream ois = new ObjectInputStream(
                        new ByteArrayInputStream(persons[i].getBytes()));
                user.readObject(ois);
                if (user.isEco(maxResourceUse)) {
                    fos.write(persons[i].getBytes());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) { //TODO REFACTOR
        File data = new File(args[0]);
        int maxResourceUse = Integer.parseInt(args[1]);
        processFile(data, maxResourceUse);
    }
}
