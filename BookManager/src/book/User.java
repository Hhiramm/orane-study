package book;

public abstract class User {
    private String name;
    public User(String name){
        this.name=name;
    }
    public User(){
    }
 
    public abstract void menu();
 
    public String getName(){
        return name;
    }
 
    public void setName(String name){
        this.name=name;
    }
    public abstract void Operation(int n, Booklist bookList)throws OperationException;
//子类的Operation会抛出异常来处理异常，父类也需要抛出异常，
//因为父类抛出异常大小要大于子类重写方法抛出的异常
}