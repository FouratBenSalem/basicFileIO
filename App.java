import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;

import java.util.Scanner;
import java.util.ArrayList;


/*
    Object Serialization File I/O
    you need 5 things:
    to read from the file : an object of the file class
        FileInputStream & ObjectInputStream to read the serializeable class

    to write that object to the file:
        FileOutputStream & ObjectOutputStream
*/

public class App
{
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        
        File file = new File("test.txt");

        // Write to the file
        try{
            PrintWriter output = new PrintWriter(file);
            output.println("Ken Adams");
            output.println(28);
            output.close();
        } 
        catch (IOException ex) {
            System.out.printf("Error: %s", ex);
        }

        // read from the file
        try{
            Scanner input = new Scanner(file);
            String name = input.nextLine();  //then scanner goes to next line
            int age = input.nextInt();

            System.out.println("Name : "+name+", age : "+age);
        }
        catch(FileNotFoundException ex){ // in case he file isn't there
            System.out.printf("Error : "+ex);

        }
        

        // SERIALIZATION & DESERIALIZATION ( Binary Files )
        File stdFile = new File("students.txt");
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("Tom", 3.9));
        students.add(new Student("Dave", 4.1));
        students.add(new Student("Bill", 2.0));

        // Serialise the collection of students

        FileOutputStream fo = new FileOutputStream(stdFile); // takes file
        ObjectOutputStream output = new ObjectOutputStream(fo); // takes fileOutputStream
        
        for(Student s : students){
            output.writeObject(s); // put Student object in the file
        }
    
        output.close();
        fo.close();

        //deserialize the file back to the collection, to read from a file

        FileInputStream fi = new FileInputStream(stdFile); // takes file
        ObjectInputStream input = new ObjectInputStream(fi); // takes fileInputStream
        ArrayList<Student> students2 = new ArrayList<Student>();
        try{
            while(true)
            {
                Student s = (Student)input.readObject(); // read Student object and cast to the target type (Student)
                students2.add(s);
            }
        }catch(EOFException ex){ // end of file, can't read anymore students (leave empty)
        }

        for(Student s : students2){ // printing the contents of the arrayList<Student> collection
            System.out.println(s.getName() + " : "+s.getGpa());
        }

        input.close();


    }
}