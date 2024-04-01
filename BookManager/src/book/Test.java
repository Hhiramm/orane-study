package book;

import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        System.out.println("请输入你的身份:");
        System.out.println("管理员/普通用户(0/1)");
        try (Scanner scan = new Scanner(System.in)) {
			int choice=scan.nextInt();
//			System.out.println("请输入你的姓名:");
			Object user=null;
			Booklist bookList=new Booklist();
			switch(choice){ //进行身份的选择
			    case 0:
			        user=new AdminextendsUser();
					System.out.println("请输入你的姓名：");
					((AdminextendsUser) user).setName(scan.next());
					do {
						((AdminextendsUser) user).menu();
						System.out.println("请输入你要进行的操作:");
						int input=scan.nextInt();
						if(input==0){
							System.out.println("退出成功");
							return;
						}
						try {
							((AdminextendsUser) user).Operation(input, bookList);
						}catch(OperationException e){
							System.out.println(e.getMessage());
						}
					}while(true);
//			        break;
			    case 1:
			        user=new Reader();
					System.out.println("请输入你的姓名：");
					((Reader) user).setName(scan.next());
					do {
						((Reader) user).menu();
						System.out.println("请输入你要进行的操作:");
						int input=scan.nextInt();
						if(input==0){
							System.out.println("退出成功");
							return;
						}
						try {
							((Reader) user).Operation(input, bookList);
						}catch(OperationException e){
							System.out.println(e.getMessage());
						}
					}while(true);
			}
		}
    }
}