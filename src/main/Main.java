package main;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {
    List<Student> groupStudents =new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.menu();
    }

    private void menu() throws Exception {
        int i=11;
        Scanner reader =new Scanner(System.in);
        while(i>0){
            System.out.println("\n" +
                               "1. Добавить нового студента в список.\n" +
                               "2. Удалить студента из списка.\n" +
                               "3. Сохранить список в файл.\n" +
                               "4. Считать список студентов из файла.\n" +
                               "5. Вывести список студентов факультета.\n" +
                               "6. Вывести список студентов которые родились после конкретного года.\n" +
                               "7. Вывести список групы по алфавиту.\n" +
                               "8. Вывести список студентов по алфавиту названия факультета.\n" +
                               "9. Вывести список всех факультетов, информация " +
                               "про студентов которых есть в программе без повторов\n" +
                               "10. Вывести информацию про количество студентов для " +
                               "всех факультетов, указаных в пункте 9.\n" +
                               "0. Выход.");
            i=reader.nextInt();
            menuProcessing(i);
        }
    }

    private void menuProcessing(int i) throws Exception {
        switch (i) {
            case 0 -> System.exit(0);
            case 1 -> addingStudent();
            case 2 -> removeStudent();
            case 3 -> writeDownTheLisOfStudents();
            case 4 -> countTheListOfStudents();
            case 5 -> facultyStudentList();
            case 6 -> listOfStudentsForSpecificYear();
            case 7 -> alphabeticalList();
            case 8 -> alphabeticalFacultyStudentList();
            case 9 -> withdrawalOfFaculties();
            case 10 -> numberOfStudents();
            default -> System.out.println("Ваш выбор за пределами списка. \n" +
                                          "Выберите номер из списка меню.");
        }


    }

    private void numberOfStudents() {

    }

    private void withdrawalOfFaculties() {
        groupStudents.stream()
                //.filter()
                .forEach(x->System.out.println(x.getFaculty()));
    }


    private void writeDownTheLisOfStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.dat"))) {
            oos.writeObject(groupStudents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void countTheListOfStudents(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file.dat"))) {
            Object object= ois.readObject();
            groupStudents.addAll(new ArrayList<>((List<Student>) object));
        } catch (Exception  e) {
            e.printStackTrace();
        }
    }

    private void alphabeticalFacultyStudentList() {
        groupStudents.stream()
                .sorted(Student::comareToFaculty)
                .forEach(Student::visualization);
    }

    private void alphabeticalList() {
        Scanner reader =new Scanner(System.in);
        int group;
        System.out.println("Укажите номер группы.");
        group=reader.nextInt();
        groupStudents.stream()
                .filter(x->group==x.getGroup())
                .sorted(Student::compareToAlpabetName)
                .forEach(Student::visualization);
    }

    private void listOfStudentsForSpecificYear() throws ParseException {
        Scanner reader =new Scanner(System.in);
        String year;
        Date dataStart;
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
        System.out.println("Укажите год в формате уууу.");
        year="31.12."+reader.next();
        dataStart=ft.parse(year);
        for (Student x:groupStudents){
            if((x.getDate().getTime()-dataStart.getTime())>0){
                x.visualization();
            }
        }
    }

    private void facultyStudentList() {
        Scanner reader =new Scanner(System.in);
        String faculty;
        System.out.println("Введите название факультета.");
        faculty=reader.next();
        System.out.println("На факультете "+faculty+" учатся следующие студенты: ");
        for (Student x: groupStudents){
            if(x.getFaculty().equals(faculty)){
                x.visualization();
            }
        }
    }

    private void removeStudent() {
        Scanner reader =new Scanner(System.in);
        int idStudent;
        System.out.println("Укажите номер студента которого нужно удалить из списка.");
        idStudent=reader.nextInt();
        groupStudents.removeIf(x -> x.getIdStudent() == idStudent);
    }

    private void addingStudent() throws ParseException {
        int idStudent;
        String surname;
        String name;
        String patronymic;
        Date date;
        String address;
        int telephone;
        String faculty;
        int course;
        int group;
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
        Scanner reader =new Scanner(System.in);
        System.out.println("Укажите номер студента.");
        idStudent=reader.nextInt();
        System.out.println("Укажите Фамилию студента.");
        surname=reader.next();
        System.out.println("Укажите Имя студента.");
        name=reader.next();
        System.out.println("Укажите Отчество студента.");
        patronymic=reader.next();
        System.out.println("Укажите Дату рождения студента в формате день.месяц.год.");
        date=ft.parse(reader.next());
        System.out.println("Укажите адресс места проживания студента.");
        address=reader.next();
        System.out.println("Укажите телефон студента.");
        telephone=reader.nextInt();
        System.out.println("Укажите факультет студента.");
        faculty=reader.next();
        System.out.println("Укажите курс студента.");
        course=reader.nextInt();
        System.out.println("Укажите группу студента.");
        group=reader.nextInt();
        groupStudents.add(new Student(idStudent,surname,name,patronymic,date,address,telephone,faculty,course,group));
    }
}
