package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] main){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();   //쉽게 이해하자면 DB Connection을 하나 만든 것.

        EntityTransaction tx = em.getTransaction(); //트랜잭션
        tx.begin(); //트랜잭션 시작

        try {
            //영속
            Member member = em.find(Member.class, 1L);
            member.setName("AAAAA");

            //em.close();   //영속성 컨텍스트 종료
            //em.clear();   //영속성 컨텍스트 다 날리기 : 1차캐시 다 지움
            //em.detach(member);  //준영속

            //flush
            //Member memberF = new Member(200L, "member200");
            //em.persist(memberF);
            //em.flush();

            //Member member = new Member();
            //member.setId(2L);
            //member.setName("HelloB");

            //em.persist(member);

            System.out.println("=====================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();  //문제가 생겼을 시 롤백.
        } finally {
            em.close();
        }

        emf.close();

    }
}
