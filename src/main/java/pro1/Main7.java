package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.Specialization;
import pro1.apiDataModel.SpecializationsList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main7 {

    public static void main(String[] args) {
        System.out.println(specializationDeadlines(2025));
    }

    public static String specializationDeadlines(int year) {
        String json = Api.getSpecializations(year);
        SpecializationsList specializations = new Gson().fromJson(json, SpecializationsList.class);
        
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d.M.yyyy");
        
        return specializations.items.stream()
                .filter(spec -> spec.deadlineApplication != null && spec.deadlineApplication.value != null)
                .map(spec -> {
                    try {
                        LocalDate date = LocalDate.parse(spec.deadlineApplication.value, inputFormat);
                        return date;
                    } catch (DateTimeParseException e) {
                        return null;
                    }
                })
                .filter(date -> date != null)
                .sorted()
                .map(date -> date.format(outputFormat))
                .distinct()
                .collect(Collectors.joining(","));
    }
} 