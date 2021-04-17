import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Course> listCourses1 = new ArrayList<>();
        listCourses1.add(new Courses("Course 1"));
        listCourses1.add(new Courses("Course 2"));
        listCourses1.add(new Courses("Course 3"));
        listCourses1.add(new Courses("Course 4"));

        List<Course> listCourses2 = new ArrayList<>();
        listCourses2.add(new Courses("Course 1"));
        listCourses2.add(new Courses("Course 4"));
        listCourses2.add(new Courses("Course 6"));

        List<Course> listCourses3 = new ArrayList<>();
        listCourses3.add(new Courses("Course 7"));
        listCourses3.add(new Courses("Course 8"));

        List<Course> listCourses4 = new ArrayList<>();
        listCourses4.add(new Courses("Course 10" ));

        List<Student> studentListArray = new ArrayList<>( Arrays.asList(
                new Persons("Иван Иванов", listCourses1),
                new Persons("Семен Петров", listCourses2),
                new Persons("Анна Демина", listCourses3),
                new Persons("Николай Смирнов", listCourses4)
        ) );
        System.out.println("Все студены и их курсы:");
        studentListArray.forEach(System.out::println);
        System.out.println("***");

        System.out.println("Список уникальных курсов, на которые ходят студенты:");
        Main Functions = new Main();
        Functions.uniqueCourses(studentListArray).forEach(System.out::println);
        System.out.println("***");

        System.out.println("Список из трех самых любознательных студентов:");
        Functions.theMostActive(studentListArray).forEach(System.out::println);
        System.out.println("***");

        System.out.println("Студенты, которые посещают Course 10:");
        Courses courseFullStack = new Courses("Course 10");
        Functions.atLeastOne(studentListArray, courseFullStack).forEach(System.out::println);
        System.out.println("***");

    }

    public List<Course> uniqueCourses(List<Student> studentList) {
        return studentList.stream()
                .map(Student::getAllCourses)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public List<Student> theMostActive(List<Student> studentList) {
        return studentList.stream()
                .sorted((b1, b2) -> b2.getAllCourses().size() - b1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Student> atLeastOne(List<Student> studentList, Courses courseFullStack) {
        return studentList.stream()
                .filter(c -> c.getAllCourses().contains(courseFullStack))
                .collect(Collectors.toList());
    }
}