package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;

    public Student(String trim, int parseInt) {
        name = trim;
        marks = Integer.valueOf(parseInt);
    }


    @Override
    public int compareTo(Student student) {
        return this.marks.compareTo(student.marks);
    }

    public String getName() {
        return name;
    }


    //ADDED
    @Override
    public String toString(){
        return "Student{name=\'"+name+"\', marks="+marks+"}";
    }
}
