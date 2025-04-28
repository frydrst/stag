package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;

public class Main3 {

    public static void main(String[] args) {
        System.out.println(emailOfBestTeacher("KIKM",2024));
    }

    public static String emailOfBestTeacher(String department, int year)
    {
        String teachersJson = Api.getTeachersByDepartment(department);
        TeachersList teachers = new Gson().fromJson(teachersJson, TeachersList.class);
        
        String actionsJson = Api.getActionsByDepartment(department, year);
        ActionsList actions = new Gson().fromJson(actionsJson, ActionsList.class);
        
        return teachers.items.stream()
                .max(Comparator.comparing(teacher -> TeacherScore(teacher.id, actions)))
                .map(teacher -> teacher.email)
                .orElse("");
    }

    public static long TeacherScore(long teacherId, ActionsList departmentSchedule)
    {
        return departmentSchedule.items.stream()
                .filter(action -> action.teacherId == teacherId)
                .mapToInt(action -> action.personsCount)
                .sum();
    }
}