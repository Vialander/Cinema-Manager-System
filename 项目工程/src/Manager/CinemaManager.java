package Manager;

import Entity.User;
import Service.Impl.UserServiceImpl;
import Service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CinemaManager {
    AdministratorManager administratorManager = new AdministratorManager();
    UserManager userManager = new UserManager();
    public void showInfo() {
        System.out.println("*************欢迎使用影院管理系统*************");
    }

    public void startMenu(){
        System.out.println("1.登录账号");
        System.out.println("2.注册账号");
        System.out.println("3.退出系统");
        System.out.print("请选择：");
        Scanner input = new Scanner(System.in);
        int choice = 0;
        try{
            choice = input.nextInt();
        } catch (InputMismatchException e){
            System.out.println("请正确输入！");
            startMenu();
        }
        switch (choice){
            case 1:
                loginMenu();
                break;
            case 2:
                registerMenu();
                break;
            case 3:
                quit();
                break;
            default:
                System.out.println("服务选项错误！");
                startMenu();
        }
    }

    public void loginMenu(){
        Scanner input = new Scanner(System.in);
        UserService userService = new UserServiceImpl();
        System.out.println("请输入用户名：");
        String username = input.next();
        System.out.println("请输入密码：");
        String password = input.next();
        if(username.equals("root")){
            if(password.equals("root")){
                System.out.println("\n欢迎管理员登入！");
                administratorManager.AdministratorStartMenu();
                startMenu();
            }else{
                System.out.println("密码错误！请重新登入！");
                startMenu();
            }
        }else{
            if(!userService.getSameUsername(username)){
                System.out.println("未找到用户！请重新登入！");
                startMenu();
            }else if(userService.getSameUsername(username)&&!userService.login(username,password)){
                System.out.println("密码错误！请重新登入！");
                startMenu();
            }else if(userService.getSameUsername(username)&&userService.login(username,password)){
                System.out.println("\n欢迎用户"+username+"登入！");
                userManager.setThisUser(userService.loginUser(username,password));
                userManager.userStartMenu();
                //进入用户
            }
        }
    }

    public void registerMenu(){
        Scanner input = new Scanner(System.in);
        UserService userService = new UserServiceImpl();
        String username;
        String password;
        double account = 0;

        while (true){
            System.out.println("请输入用户名：");
            username = input.next();
            if(username.equals("root")){
                System.out.println("用户名重复！请重新输入！");
            }
            else if(userService.getSameUsername(username)){
                System.out.println("用户名重复！请重新输入！");
            }else{
                break;
            }
        }

        while (true){
            System.out.println("请输入密码：");
            password = input.next();
            System.out.println("请再次输入密码以确认：");
            String confirmPassword = input.next();
            if(!confirmPassword.equals(password)){
                System.out.println("两次输入的密码不一致！请重新输入！");
            }else{
                break;
            }
        }

        boolean flag = true;
        while (flag){
            try{
                System.out.println("请输入账户余额：");
                account = input.nextDouble();
                if(account < 0){
                    System.out.println("余额不得小于0！");
                }else{
                    flag = false;
                }
            } catch (InputMismatchException e){
                System.out.println("请正确输入！");
            }
        }

        userService.regist(new User(username,password,account));
        System.out.println("注册成功！");
        startMenu();
    }
    public void quit(){
        System.out.println("谢谢使用！");
    }
}
