package com.ashfaque.jdbc_callable_statement.Controller;

import com.ashfaque.jdbc_callable_statement.dao.UserDao;
import com.ashfaque.jdbc_callable_statement.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserController {
    public static void main(String[] args) {
        UserDao dao=new UserDao();
        Scanner sc=new Scanner(System.in);
        char ch;
        do {
            System.out.println("1.Insert\n2.Delete\n3.Update\n4.DisplayById\n5.DisplayAll");
            int choice=sc.nextInt();
            switch (choice){
                case 1:{
                    System.out.println("Enter id:");
                    int id=sc.nextInt();
                    System.out.println("Enter name:");
                    String name=sc.next();
                    System.out.println("Enter email:");
                    String email=sc.next();
                    System.out.println("Enter address:");
                    String address=sc.next();
                    System.out.println("Enter dob:");
                    String dob= sc.next();
                    User user=new User(id,name,email,address, LocalDate.parse(dob));
                    User u=dao.insertUserDao(user);
                    if (u!=null){
                        System.out.println("Data Inserted");
                        System.out.println(u);
                    }
                    else
                        System.out.println("Something went wrong!!!");
                }break;

                case 2:{
                    System.out.println("Enter id:");
                    int id=sc.nextInt();
                    boolean b=dao.deleteUserDao(id);
                    if (b){
                        System.out.println("Data Deleted");
                    }
                    else
                        System.out.println("Something went wrong!!");
                }break;

                case 3:{
                    System.out.println("Enter id:");
                    int id=sc.nextInt();
                    System.out.println("Enter new name");
                    String name=sc.next();
                    boolean b= dao.updateUserDao(id,name);
                    if (b)
                        System.out.println("Data Updated");
                    else
                        System.out.println("something went wrong!!!!");
                }break;

                case 4: {
                    System.out.println("Enter id to display:");
                    int id1=sc.nextInt();
                    ResultSet rs= dao.displayUserByIdDao(id1);
                    try {
                        if (rs.next()){
                            int id=rs.getInt("id");
                            String name=rs.getString("name");
                            String email=rs.getString("email");
                            String address=rs.getString("address");
                            String dob=rs.getString("dob");
                            User u=new User(id,name,email,address,LocalDate.parse(dob));
                            System.out.println(u);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Something went wrong!!!");
                    }
                }break;

                case 5:{
                    List<User> user=dao.displayAllUser();
                    for (User users:user){
                        System.out.println(users);
                    }
                }break;

                default:{
                    System.out.println("Invalid Option");
                }
            }
            System.out.println("Press y/Y to try again");
            ch=sc.next().charAt(0);
        }while (ch=='y'|| ch=='Y');
    }
}
