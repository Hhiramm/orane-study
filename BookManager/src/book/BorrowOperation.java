package book;

import java.util.Scanner;

public class BorrowOperation implements IOperation{
    public void work(Booklist bookList)throws OperationException{
//		public void work(Booklist bookList){
        System.out.println("请输入你想要借的书:");
        try (Scanner scan = new Scanner(System.in)) {
			String name=scan.next();
			boolean flag=true;
			for(int i=0;i< bookList.getBooks().size();i++){
			    if(bookList.getBooks().get(i).getName().equals(name)){ //查找到该书籍在图书馆中
			        flag=false;
			        if(bookList.getBooks().get(i).isBorrowed()==true){
//						System.out.println("借书失败");
			            throw new OperationException("借书失败，该书已被借出");
			        }else{ //书未被借出
			            System.out.println("借出成功!");
			            bookList.getBooks().get(i).setBorrowed(true);
			        }
			    }
			}
			//出循环了，仍未找到
			if(flag) {
			    throw new OperationException("未查找到该书");
			}
		}
    }
}