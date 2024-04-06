package com.tl.mysql_connector;
import java.util.Scanner;
import java.sql.*;

class Execution
{
	int count = 0;
	public void execute() throws SQLException
	{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_data", "root", "suryaprakash200117@gmail.com");
		Statement stmt = con.createStatement();
		ResultSet rst = stmt.executeQuery("Select * from emp");
		
		while(rst.next())
		{
			int id = rst.getInt("id");
			String name = rst.getString("name");
			int age = rst.getInt("age");
			String gender = rst.getString("gender");
			count++;
			System.out.println(id +" "+ name +" "+ age +" "+ gender);
		}
		con.close();
		System.out.println("Query has been successfully Executed" + " and the row count is " + count);
	}
	
	public void DML() throws SQLException
	{
		System.out.println("What do you want to do insert, update or delete:-> ");
		Scanner sc = new Scanner(System.in);		
		String val = sc.nextLine();
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple_data", "root", "suryaprakash200117@gmail.com");
		
		Statement stmt = con.createStatement();
		
		if(val.equalsIgnoreCase("insert"))
		{
			System.out.println("Give the new data:");
			System.out.println("Give the new id:");
			int id = Integer.parseInt(sc.nextLine());
			System.out.println("Give the new name:");
			String name = sc.nextLine();
			System.out.println("Give the new age:");
			int age = Integer.parseInt(sc.nextLine());
			System.out.println("Give the gender:");
			String gender = sc.nextLine();
			PreparedStatement pst = con.prepareStatement("insert into emp values(?, ?, ?, ?)");
			pst.setInt(1, id);
			pst.setString(2, name);
			pst.setInt(3, age);
			pst.setString(4, gender);
			pst.execute();
			count++;
			execute();
		}
		if(val.equalsIgnoreCase("update"))
		{
			System.out.println("Enter the ID of teh record you want to update");
			int id = sc.nextInt();
			sc.nextLine(); 
			System.out.println("Enter the data has to be update (name or age or gender)->:");
			String data = sc.nextLine();
	
			if(data.equalsIgnoreCase("name"))
			{
				System.out.println("Enter the new name");
				String newName = sc.nextLine();
				PreparedStatement pst = con.prepareStatement("update emp set name = ? where id = ?");
				pst.setString(1, newName);
				pst.setInt(2, id);
				pst.execute();
				System.out.println("Name has been updated successfully");
				execute();
			}
			if(data.equalsIgnoreCase("gender"))
			{
				System.out.println("Enter the new gender");
				String newGender = sc.nextLine();
				PreparedStatement pst = con.prepareStatement("update emp set gender = ? where id = ?");
				pst.setString(1, newGender);
				pst.setInt(2, id);
				pst.execute();
				System.out.println("Gender has been updated successfully");
				execute();
			}
			if(data.equalsIgnoreCase("age"))
			{
				System.out.println("Enter the new age:");
				int newAge = sc.nextInt();
				PreparedStatement pst = con.prepareStatement("update emp set age = ? where id = ?");
				pst.setInt(1, newAge);
				pst.setInt(2, id);
				pst.execute();
				System.out.println("Age has been updated successfully");
				execute();
			}
		}
		if(val.equalsIgnoreCase("delete"))
		{
			System.out.println("Enter the ID number which has been deleted:");
			int id = sc.nextInt();
			PreparedStatement pst = con.prepareStatement("delete from emp where id = ?");
			pst.setInt(1, id);
			int rowsaffect = pst.executeUpdate();
			if (rowsaffect > 0)
			{
		        System.out.println("A row with ID " + id + " has been successfully deleted");
		        count--;
		        System.out.println("The total number of rows now is: " + count);
		    }
			else
			{
		        System.out.println("No rows were deleted. ID does not exist.");
		    }
		}
		con.close();
	 }
}
public class Mysql_DML
{
	public static void main(String[] args) throws SQLException
	{	
		System.out.println("Do you want to see the DataBase (y / n)-> ");
		Scanner sc = new Scanner(System.in);		
		String val = sc.nextLine();
		
		Execution exe = new Execution();
		
		if(val.equalsIgnoreCase("yes") || val.equalsIgnoreCase("y"))
		{
			exe.execute();
			System.out.println();
		}
		else if(val.equalsIgnoreCase("no") || val.equalsIgnoreCase("n"))
		{
			exe.DML();
		}
		else
		{
			System.out.println("Select valid option among yes or no");
		}
	}
}
