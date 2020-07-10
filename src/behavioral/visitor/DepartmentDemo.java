package structural.visitor;

/**
* Title: 部门员工管理（访问者模式Demo）
* Desc: 在该OA系统中包含一个员工信息管理子系统，该银行员工包括正式员工和临时工，每周人力资源部和财务部等部门需要对员工数据进行汇总，
汇总数据包括员工工作时间、员工工资等。该公司基本制度如下：
       (1) 正式员工(Full time  Employee)每周工作时间为40小时，不同级别、不同部门的员工每周基本工资不同；
       如果超过40小时，超出部分按照100元/小时作为加班费；
       如果少于40小时，所缺时间按照请假处理，请假所扣工资以80元/小时计算，直到基本工资扣除到零为止。
       除了记录实际工作时间外，人力资源部需记录加班时长或请假时长，作为员工平时表现的一项依据。
       (2) 临时工(Part time  Employee)每周工作时间不固定，
       基本工资按小时计算，不同岗位的临时工小时工资不同。人力资源部只需记录实际工作时间。
       人力资源部和财务部工作人员可以根据各自的需要对员工数据进行汇总处理，
       人力资源部负责汇总每周员工工作时间，而财务部负责计算每周员工工资。
* Created by Myth on 12/14/2019 in VSCode
*/
import java.util.*;
 
// 员工类：抽象元素类
interface Employee {
	public void accept(Department handler); // 接受一个抽象访问者访问
}

// 全职员工类：具体元素类
class FulltimeEmployee implements Employee {
	private String name;
	private double weeklyWage;
	private int workTime;
 
	public FulltimeEmployee(String name,double weeklyWage,int workTime) {
		this.name = name;
		this.weeklyWage = weeklyWage;
		this.workTime = workTime;
	}	
    public void accept(Department handler) {
		handler.visit(this); //调用访问者的访问方法
	}
	public void setName(String name) {
		this.name = name; 
	}
 
	public void setWeeklyWage(double weeklyWage) {
		this.weeklyWage = weeklyWage; 
	}
 
	public void setWorkTime(int workTime) {
		this.workTime = workTime; 
	}
 
	public String getName() {
		return (this.name); 
	}
 
	public double getWeeklyWage() {
		return (this.weeklyWage); 
	}
 
	public int getWorkTime() {
		return (this.workTime); 
	}
}
 
//兼职员工类：具体元素类
class ParttimeEmployee implements Employee {
	private String name;
	private double hourWage;  // 小时工资
	private int workTime;
 
	public ParttimeEmployee(String name,double hourWage,int workTime) {
		this.name = name;
		this.hourWage = hourWage;
		this.workTime = workTime;
	}	
    public void accept(Department handler) {
		handler.visit(this); // 调用访问者的访问方法
	}
	public void setName(String name) {
		this.name = name; 
	}
 
	public void setHourWage(double hourWage) {
		this.hourWage = hourWage; 
	}
 
	public void setWorkTime(int workTime) {
		this.workTime = workTime; 
	}
 
	public String getName() {
		return (this.name); 
	}
 
	public double getHourWage() {
		return (this.hourWage); 
	}
 
	public int getWorkTime() {
		return (this.workTime); 
	}
}
 
//部门类：抽象访问者类
abstract class Department {
    //声明一组重载的访问方法，用于访问不同类型的具体元素
	public abstract void visit(FulltimeEmployee employee);
	public abstract void visit(ParttimeEmployee employee);	
}
 
//财务部类：具体访问者类
class FADepartment extends Department {
    // 实现财务部对全职员工的访问
	public void visit(FulltimeEmployee employee) {
		int workTime = employee.getWorkTime();
		double weekWage = employee.getWeeklyWage();
		if(workTime > 40) {
			weekWage = weekWage + (workTime - 40) * 100;
		}
		else if(workTime < 40) {
			weekWage = weekWage - (40 - workTime) * 80;
			if(weekWage < 0) {
				weekWage = 0;
			}
		}
		System.out.println("正式员工" + employee.getName() + "实际工资为：" + weekWage + "元。");			
	}
 
    // 实现财务部对兼职员工的访问
	public void visit(ParttimeEmployee employee) {
		int workTime = employee.getWorkTime();
		double hourWage = employee.getHourWage();
		System.out.println("临时工" + employee.getName() + "实际工资为：" + workTime * hourWage + "元。");		
	}		
}
 
// 人力资源部类：具体访问者类
class HRDepartment extends Department
{
    //实现人力资源部对全职员工的访问
	public void visit(FulltimeEmployee employee) {
		int workTime = employee.getWorkTime();
		System.out.println("正式员工" + employee.getName() + "实际工作时间为：" + workTime + "小时。");
		if(workTime > 40) {
			System.out.println("正式员工" + employee.getName() + "加班时间为：" + (workTime - 40) + "小时。");
		}
		else if(workTime < 40) {
			System.out.println("正式员工" + employee.getName() + "请假时间为：" + (40 - workTime) + "小时。");
		}						
	}
 
    //实现人力资源部对兼职员工的访问
	public void visit(ParttimeEmployee employee) {
		int workTime = employee.getWorkTime();
		System.out.println("临时工" + employee.getName() + "实际工作时间为：" + workTime + "小时。");
	}		
}
 
// 员工列表类：对象结构
class EmployeeList {
    //定义一个集合用于存储员工对象
	private ArrayList<Employee> list = new ArrayList<Employee>();
 
	public void addEmployee(Employee employee) {
		list.add(employee);
	}
 
    // 遍历访问员工集合中的每一个员工对象
	public void accept(Department handler) {
		for(Object obj : list) {
			((Employee)obj).accept(handler);
		}
	}
}

public class DepartmentDemo {
    // 客户端
    public static void main(String[] args) {
        EmployeeList employeeList = new EmployeeList();
        Employee employee1 = new FulltimeEmployee("name1", 2000, 50);
        Employee employee2 = new FulltimeEmployee("name2", 2000, 40);
        Employee employee3 = new FulltimeEmployee("name3", 2000, 20);
        Employee employee4 = new FulltimeEmployee("name4", 2000, 80);
        Employee employee5 = new ParttimeEmployee("name5", 20, 5);
        Employee employee6 = new ParttimeEmployee("name5", 20, 20);
        employeeList.addEmployee(employee1);
        employeeList.addEmployee(employee2);
        employeeList.addEmployee(employee3);
        employeeList.addEmployee(employee4);
        employeeList.addEmployee(employee5);
        employeeList.addEmployee(employee6);
        Department department = new HRDepartment();
        employeeList.accept(department);
        // 配置文件中修改 Department
        department = new FADepartment();
        employeeList.accept(department);
    }
}
/**
 * 如果要在系统中增加一种新的访问者，无须修改源代码，只要增加一个新的具体访问者类即可，
 * 在该具体访问者中封装了新的操作元素对象的方法。从增加新的访问者的角度来看，访问者模式符合“开闭原则”。
如果要在系统中增加一种新的具体元素，例如增加一种新的员工类型为“退休人员”，由于原有系统并未提供相应的访问接口
（在抽象访问者中没有声明任何访问“退休人员”的方法），因此必须对原有系统进行修改，在原有的抽象访问者类和具体访问者类中增加相应的访问方法。
从增加新的元素的角度来看，访问者模式违背了“开闭原则”。
综上所述，访问者模式与抽象工厂模式类似，对“开闭原则”的支持具有倾斜性，可以很方便地添加新的访问者，但是添加新的元素较为麻烦。
 */