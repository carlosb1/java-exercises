package exercises;

import com.mongodb.DBCursor;
/**
 * @author Shamik Mitra
 *
 */
public class App {
    /**
     *
     */
    public static void main(String[] args) {
        DBCursor result = MongoContext.get().connectDb("test").findByKey("Employee", "age", 32,
                (value) -> new Integer(value));
        while (result.hasNext()) {
            System.out.println(result.next());
        }
    }
}