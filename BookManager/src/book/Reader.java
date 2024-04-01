package book;

public class Reader extends User{
    @Override
    public void setName(String name) {
        super.setName(name);
        System.out.println("尊敬的读者:"+this.getName()+"\n欢迎来到图书馆");
    }

    @Override
    public String getName() {
        return super.getName();
    }

//    public Reader(String name){
//        super(name);
//    }

    @Override
    public void menu() {
        System.out.println("1.查找图书");
        System.out.println("2.借阅图书");
        System.out.println("3.归还图书");
        System.out.println("0.退出系统");
    }
    public void Operation(int n, Booklist bookList)throws OperationException {
        IOperation iOperation=null;
        switch(n){
            case 0:
                iOperation= new ExitOperation();
                break;
            case 1:
                iOperation=new FindOperation();
                break;
            case 2:
                iOperation=new BorrowOperation();
                break;
            case 3:
                iOperation=new ReturnOperation();
                break;
            default:
                throw new OperationException("输入错误，请重新输入！");
        }
        try {
            iOperation.work(bookList);
        }catch(OperationException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}