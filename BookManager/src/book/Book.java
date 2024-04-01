package book;

public class Book {
    private String name;
    private String author;
    private int price;
    private String type;
    private boolean isBorrowed; //是否被借出
    //构造器
    public Book(){
 
    }

    public Book(String name, String author, int price, String type, boolean isBorrowed) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.type = type;
        this.isBorrowed = isBorrowed;
    }

    public Book(String name, String author, int price, String type){
        this.author=author;
        this.name=name;
        this.price=price;
        this.type=type;
    }
 
    //get、set方法
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getAuthor() {
        return author;
    }
 
    public void setAuthor(String author) {
        this.author = author;
    }
 
    public int getPrice() {
        return price;
    }
 
    public void setPrice(int price) {
        this.price = price;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    public boolean isBorrowed() {
        return isBorrowed;
    }
 
    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
@Override
//重写toString()
    public String toString() {
        return "Book{" +
                "书名:'" + name + '\'' +
                ", 作者:'" + author + '\'' +
                ", 价格:" + price +
                ", 类型:'" + type + '\'' +
                ", 是否借出:" + isBorrowed +
                '}';
    }
}
