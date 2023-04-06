package ecoUser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
    int id;
    String name;
    int waterCount;
    int gasCount1;
    int gasCount2;
    int electroCount1;
    int electroCount2;

    User() {
    }

    User(int id, String name, int waterCount, int gasCount1, int gasCount2, int electroCount1, int electroCount2) {
        this.id = id;
        this.name = name;
        this.waterCount = waterCount;
        this.gasCount1 = gasCount1;
        this.gasCount2 = gasCount2;
        this.electroCount1 = electroCount1;
        this.electroCount2 = electroCount2;
    }

    public User readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {

        return new User(ois.readInt(), (String)ois.readObject(), ois.readInt(), ois.readInt(),
                ois.readInt(), ois.readInt(), ois.readInt());
    }

    public void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeInt(id);
        oos.writeObject("|");
        oos.writeObject(name);
        oos.writeObject("|");
        oos.writeInt(waterCount);
        oos.writeObject("|");
        oos.writeInt(gasCount1);
        oos.writeObject("|");
        oos.writeInt(gasCount2);
        oos.writeObject("|");
        oos.writeInt(electroCount1);
        oos.writeObject("|");
        oos.writeInt(electroCount2);
    }

    public boolean isEco(int maxValue) {
        return (maxValue > waterCount &&
                maxValue > gasCount1 &&
                maxValue > gasCount2 &&
                maxValue > electroCount1 &&
                maxValue > electroCount2);
    }
}
