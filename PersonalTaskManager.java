import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PersonalTaskManager {
    public JSONObject addNewTask(String title, String description, String dueDateStr, String priority)
            throws TaskManagementException {
        TaskValidator.validateTaskInput(title, dueDateStr, priority);
        LocalDate dueDate = LocalDate.parse(dueDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        JSONArray tasks = DatabaseHandler.loadTasks();

        // Kiểm tra trùng lặp
        for (Object obj : tasks) {
            JSONObject existingTask = (JSONObject) obj;
            if (existingTask.get("title").toString().equalsIgnoreCase(title) &&
                existingTask.get("due_date").toString().equals(dueDateStr)) {
                throw new TaskManagementException(String.format("Nhiệm vụ '%s' đã tồn tại với cùng ngày đến hạn.", title));
            }
        }

        // Tạo ID đơn giản
        int newId = tasks.size() + 1;
        Task task = new Task(newId, title, description, dueDate, priority);
        JSONObject taskJson = task.toJson();
        tasks.add(taskJson);

        DatabaseHandler.saveTasks(tasks);

        System.out.println(String.format("Đã thêm nhiệm vụ mới thành công với ID: %d", newId));
        return taskJson;
    }

    public static void main(String[] args) {
        PersonalTaskManager manager = new PersonalTaskManager();
        try {
            System.out.println("\nThêm nhiệm vụ hợp lệ:");
            manager.addNewTask(
                "Mua sách",
                "Sách Công nghệ phần mềm.",
                "2025-07-20",
                "Cao"
            );

            System.out.println("\nThêm nhiệm vụ trùng lặp:");
            manager.addNewTask(
                "Mua sách",
                "Sách Công nghệ phần mềm.",
                "2025-07-20",
                "Cao"
            );

            System.out.println("\nThêm nhiệm vụ với tiêu đề rỗng:");
            manager.addNewTask(
                "",
                "Nhiệm vụ không có tiêu đề.",
                "2025-07-22",
                "Thấp"
            );
        } catch (TaskManagementException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}