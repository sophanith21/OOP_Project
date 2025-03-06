package src.cinema;
import java.util.ArrayList;

public interface DataPersistence {
    void saveData(String fileName);  // For saving a single object
    void loadData(String fileName);  // For loading a single object

    // Default methods for saving/loading multiple objects
    static <T extends DataPersistence> void saveAll(String fileName, ArrayList<T> objects) {
        throw new UnsupportedOperationException("Not implemented");
    }

    static <T extends DataPersistence> ArrayList<T> loadAll(String fileName) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
