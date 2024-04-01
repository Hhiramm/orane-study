package book;

import java.util.Scanner;

public class AdminextendsUser extends User{
    @Override
//    Scanner scanner = new Scanner(System.in);
    public void setName(String name) {
//        System.out.println("请输入你的姓名：");
        super.setName(name);
        System.out.println("尊敬的管理员:"+this.getName()+"\n欢迎来到图书管理系统");
    }

    //    public void Admin(String name){
//
//    }
    public void menu(){
//        System.out.println("尊敬的管理员:"+this.getName()+"\n欢迎来到图书管理系统");
        System.out.println("1.查找图书");
        System.out.println("2.新增图书");
        System.out.println("3.删除图书");
        System.out.println("4.显示图书");
        System.out.println("0.退出系统");
    }


    //    private String getName() {
//		// TODO Auto-generated method stub
//		return name();
//	}
	public  void Operation(int n,Booklist bookList)throws OperationException{
        IOperation iOperation=null;
        switch(n){
            case 0:
                iOperation= new ExitOperation();
                break;
            case 1:
                iOperation=new FindOperation();
                break;
            case 2:
                iOperation=new AddOperation();
                break;
            case 3:
                iOperation=new DelOperation();
                break;
            case 4:
                iOperation= new DisplayOperation();
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