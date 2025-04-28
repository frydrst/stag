package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static void printShortestEmails(String department, int count)
    {
        String teachersJson = Api.getTeachersByDepartment(department);
        TeachersList teachers = new Gson().fromJson(teachersJson, TeachersList.class);
        
        teachers.items.stream()
                .map(teacher -> teacher.email)
                .filter(email -> email != null && !email.isEmpty())
                .sorted(Comparator.comparing(String::length))
                .limit(count)
                .forEach(System.out::println);
    }
}