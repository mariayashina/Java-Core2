import java.util.List;

public class Persons implements Student {

    String name;
    public List<Course> courses;

    public Persons(String name, List<Course> courses) {
        this.name = name;
        this.courses = courses;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override

    public List<Course> getAllCourses() {

        return courses;
    }

    @Override
    public String toString() {
        return name + " ходит на курс(ы): " + courses;
    }
}