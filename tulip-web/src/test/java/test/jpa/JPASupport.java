package test.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

@Repository(value = "JPASupport")
public class JPASupport {

    @PersistenceContext(unitName = "core")
    protected EntityManager entityManager;

    @PersistenceContext(unitName = "core_read")
    protected EntityManager readEntityManager;
    
    public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:configurer-beans.xml");
		JPASupport JPASupport = context.getBean("JPASupport", JPASupport.class);
		System.out.println(JPASupport.entityManager);
		System.out.println(JPASupport.readEntityManager);
	}
}
