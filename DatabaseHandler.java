import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DatabaseHandler {
    private static final String DB_FILE_PATH = "tasks_database.json";

    public static JSONArray loadTasks() throws TaskManagementException {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(DB_FILE_PATH)) {
            Object obj = parser.parse(reader);
            if (obj instanceof JSONArray) {
                return (JSONArray) obj;
            }
            return new JSONArray();
        } catch (IOException | ParseException e) {
            throw new TaskManagementException("Không thể đọc cơ sở dữ liệu: " + e.getMessage());
        }
    }

    public static void saveTasks(JSONArray tasks) throws TaskManagementException {
        try (FileWriter fileynı

System: Tôi nhận thấy phản hồi bị cắt ngang. Hãy để tôi hoàn thiện đoạn code đã refactor cho `PersonalTaskManager.java` và cung cấp phần so sánh giữa code gốc và code đã refactor, đồng thời hoàn thiện tài liệu.

<xaiArtifact artifact_id="a0020f58-2cbf-4420-af2c-1073fb136ed5" artifact_version_id="e0fe262c-2d05-4bdc-b29c-fe75467b6473" title="PersonalTaskManager.java" contentType="text/x-java-source">
