package book;

import java.util.Scanner;
public class AddOperation implements IOperation{
    @Override
    public void work(Booklist bookList) {
        System.out.println("新增图书!");
        Scanner scan=new Scanner(System.in);
        System.out.println("请输入要新增图书的名字:");
        String name=scan.next();
        System.out.println("请输入作者的名字:");
        String author=scan.next();
        System.out.println("请输入图书的价格:");
        int price=scan.nextInt();
        System.out.println("请输入图书的类型:");
        String type=scan.next();
        Book book=new Book(name,author,price,type); //新建书籍
        bookList.getBooks().add(book); //使用ArrayList的add来进行图书的添加
        System.out.println("添加成功!");
    }
}