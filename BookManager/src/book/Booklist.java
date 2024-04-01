package book;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Booklist {
    private ArrayList<Book> books=new ArrayList<Book>();
    public Booklist() {
        books.add(new Book("海底两万里","儒勒.凡尔纳",20,"长篇小说",false));
        books.add(new Book("红楼梦","曹雪芹",25,"小说",false));
        books.add(new Book("活着","余华",35,"长篇小说",false));
        books.add(new Book("百年孤独","加西亚，马尔克斯",22,"小说",false));
        books.add(new Book("哈姆雷特","威廉.莎士比亚",18,"戏剧",false));
        books.add(new Book("我与地坛","史铁生",28,"散文",false));
        books.add(new Book("圆圈正义","罗翔",35,"随笔",false));
        books.add(new Book("格列佛游记","乔纳森·斯威夫特",20,"长篇小说",false));
 
    }
    public Book getBook(int pos){ //获取某个位置上的书籍
        return books.get(pos);
    }
    public void setBook(int pos,Book book){ //设置某个位置上的书籍
        books.set(pos,book);
    }
    public ArrayList<Book> getBooks(){  //获取书架，便于后面的Operation的使用
        return books;
    }
}