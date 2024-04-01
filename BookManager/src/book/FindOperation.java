package book;

import java.util.Scanner;
//查找功能的实现
public class FindOperation implements IOperation{
  @Override
  public void work(Booklist bookList) throws OperationException {
      Scanner scan=new Scanner(System.in);
      System.out.println("请输入你想要查找的书名:");
      String name=scan.next();
      boolean flag=true;
      for(int i=0;i<bookList.getBooks().size();i++){
          if(bookList.getBooks().get(i).getName().equals(name)){
              flag=false;
              System.out.println("找到了!   信息如下:");
              System.out.println(bookList.getBooks().get(i));
          }
      }
      if(flag){
          throw new OperationException("没有找到该书");
      }
  }
}