package book;

import java.util.Scanner;

public class ReturnOperation implements IOperation{
    @Override
    public void work(Booklist bookList) throws Exception {
        try (Scanner scan = new Scanner(System.in)) {
			System.out.println("请输入你要归还的书籍:");
			String name=scan.next();
			boolean flag=true;
			for(int i=0;i<bookList.getBooks().size();i++){
			    if(bookList.getBooks().get(i).getName().equals(name)){
			        flag=false;
			        System.out.println("成功归还!");
			        bookList.getBooks().get(i).setBorrowed(false);
			    }
			}
			if(flag){
			    throw new OperationException("查无此书!");
			}
		}
    }
}