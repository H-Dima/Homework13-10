package main;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private int idStudent;
    private String surname;
    private String name;
    private String patronymic;
    private Date date;
    private String address;
    private int telephone;
    private String faculty;
    private int course;
    private int group;

    public Student (int idStudent,
                    String surname,
                    String name,
                    String patronymic,
                    Date date,
                    String address,
                    int telephone,
                    String faculty,
                    int course,
                    int group){
    this.idStudent=idStudent;
    this.surname=surname;
    this.name=name;
    this.patronymic=patronymic;
    this.date=date;
    this.address=address;
    this.telephone=telephone;
    this.faculty=faculty;
    this.course=course;
    this.group=group;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getDate() {
        return date;
    }

    public String getAddress() {
        return address;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getFaculty() {
        return faculty;
    }

    public int getCourse() {
        return course;
    }

    public int getGroup() {
        return this.group;
    }

     public void  visualization(){
         System.out.println("Факультет "+faculty+
                            " Группа №"+group+
                            " Студент "+course +" курса " + surname+" "+name+" "+ patronymic);

     }

    public int compareToAlpabetName(Student o) {
        int result;
        return result = this.name.compareTo(o.name);
    }

    public int comareToFaculty(Student o) {
        int result;
        return  result=this.faculty.compareTo(o.faculty);
    }
}
