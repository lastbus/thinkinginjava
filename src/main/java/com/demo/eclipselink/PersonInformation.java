package com.demo.eclipselink;

/**
 * @author make
 * @creare 08/10/2018
 */

        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.Table;
//@Entity表示一个可以序列化映射的的对象 ,如果我们希望这个对象被映射到数据库中的某个表，则必须要加上这个annotation
@Entity
//这里通过table来设定对应的数据库表名字是什么
@Table(name="PersonInformation")
public class PersonInformation {
    //而@Id则表示对应表的主键。我们建一个表要求有对应的主键。
    //这里指定id为主键。如果我们不指定主键的话则运行的时候会出错
    @Id
    //它表示这个主键的值可以自动来生成，而后面的GenerationType.IDENTITY表明它的生成方式是自动增长
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column这个用来设定对应的数据库字段名
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}



