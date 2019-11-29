package structural.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: 过滤器模式（很简单，就是实现一个过滤器）
 * Desc: 定义Person和两个过滤器，区分男女
 * Created by Myth-Lab on 11/29/2019
 */
class Person {

    private String name;
    private String gender;
    private String maritalStatus;

    public Person(String name,String gender,String maritalStatus){
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
}
// 过滤器的接口
interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
// 过滤女的，只保留男的
class CriteriaMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("MALE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
// 过滤 男，只保留女的
class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("FEMALE")){
                femalePersons.add(person);
            }
        }
        return femalePersons;
    }
}
public class FilterDemo {

}
