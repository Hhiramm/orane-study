package book;

public class DisplayOperation implements IOperation{
    //显示功能的实现
    //结合book类的重写进行
    @Override
    public void work(Booklist bookList) throws OperationException {
        for(int i=0;i<bookList.getBooks().size();i++){
            System.out.println(bookList.getBooks().get(i)); //直接输出
        }
    }
}