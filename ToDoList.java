import java.util.Scanner;
public class ToDoList {
    private int doMonth;
    private String toDo;

    public ToDoList(int doMonth) {
        this.doMonth = doMonth;
        toDo = "";
    }
    public String monthlyDo() {
        if (doMonth < 0) {
            return "";
        }
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in your To Do List. Type in Ctrl Z on Windows to end your To-Do List Input.");
        while(scan.hasNextLine()) {
            toDo += "-" + scan.nextLine() + "\n";
        }
        return toDo;
    }

    public int getDoMonth() {
        return doMonth;
    }
}
