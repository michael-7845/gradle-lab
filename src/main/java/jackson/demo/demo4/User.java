package jackson.demo.demo4;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by I340951 on 8/15/2017.
 */
public class User {
    private String id; //标识
    private String name;    //姓名
    private int age;    //年龄
    private double pay; //工资
    private boolean valid;  //是否有效
    private char one; //一个字符
    private Date birthday;  //生日

    private Link link; //联系方式，自定义

    private Map map; //测试用
    private List list; //测试用
    private Set set; //测试用
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getAge()
    {
        return age;
    }
    public void setAge(int age)
    {
        this.age = age;
    }
    public double getPay()
    {
        return pay;
    }
    public void setPay(double pay)
    {
        this.pay = pay;
    }
    public boolean isValid()
    {
        return valid;
    }
    public void setValid(boolean valid)
    {
        this.valid = valid;
    }
    public char getOne()
    {
        return one;
    }
    public void setOne(char one)
    {
        this.one = one;
    }
    public Date getBirthday()
    {
        return birthday;
    }
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }
    public Link getLink()
    {
        return link;
    }
    public void setLink(Link link)
    {
        this.link = link;
    }
    public Map getMap()
    {
        return map;
    }
    public void setMap(Map map)
    {
        this.map = map;
    }
    public List getList()
    {
        return list;
    }
    public void setList(List list)
    {
        this.list = list;
    }
    public Set getSet()
    {
        return set;
    }
    public void setSet(Set set)
    {
        this.set = set;
    }
}

class Link
{
    private String phone; //移动电话
    private String address; //地址
    private String qq; //QQ

    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getQq()
    {
        return qq;
    }
    public void setQq(String qq)
    {
        this.qq = qq;
    }
}