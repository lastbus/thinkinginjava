package com.demo.eclipselink;

/**
 * @author make
 * @creare 08/10/2018
 */
        import com.google.inject.persist.jpa.JpaPersistModule;

        import javax.persistence.EntityManager;
        import javax.persistence.EntityManagerFactory;
        import javax.persistence.Persistence;

public class Main {
	 /*private static final String PERSISTENCE_UNIT_NAME = ;
	    private static EntityManagerFactory factory;  */

    public static void main(String[] args) {
        //第一个需要创建的对象是EntityManagerFactory
        //这个employeeservice是根据配置文件里的persistence-unit name一致。
        //如果我们需要访问多个库的话，在配置文件里也可以定义多个persistence-unit。有了这个factory之后我们再创建一个EntityManager对象。
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager em = factory.createEntityManager();
        //  为了使得对象的创建成为一个事务来提交，我们通过em.getTransaction().begin(); em.getTransaction().commit();这两个方法来完成整个数据插入的过程。
        em.getTransaction().begin();
        PersonInformation person = new PersonInformation();
        person.setId(10);
        person.setAge(61);
        person.setName("fred");
        em.persist(person);
        em.getTransaction().commit();
        em.close();

    }
}


