
import java.io.Serializable;


public class Student implements Serializable
{
    static final long serialVersionUID = 42L;  // delete this if you run into problems  
    private String Name;
    private double GPA;
    
    public Student(String name, double gpa)
    {
        this.Name = name;
        this.GPA = gpa;
    }

    public String getName(){
        return this.Name;
    }
    public void setName(String name){
        this.Name = name;
    }
    public double getGpa(){
        return this.GPA;
    }
    public void setGpa(int gpa){
        this.GPA = gpa;
    }


}
