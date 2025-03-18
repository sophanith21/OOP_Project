package src.DataControl;
import java.util.ArrayList;

public interface DataPersistence {
    void saveData();  // For saving a single object
    void loadData();  // For loading a single object

    // Default methods for saving/loading multiple objects
    static <T extends DataPersistence> void saveAll( ArrayList<T> objects) {
        throw new UnsupportedOperationException("Not implemented");
    }

    static <T extends DataPersistence> ArrayList<T> loadAll() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
