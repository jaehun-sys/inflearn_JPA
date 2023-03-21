package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] main){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();   //쉽게 이해하자면 DB Connection을 하나 만든 것. 새로 실행하면서 영속성 컨텍스트가 새로 생성 됨.
        //엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.

        EntityTransaction tx = em.getTransaction(); //트랜잭션
        tx.begin(); //트랜잭션 시작

        // try-catch로 정석적인 예외 처리를 함. -> 이 역할은 이제 Spring이 해줌.
        try {
            //비영속
            Member memberA = new Member();
            memberA.setId(100L);

            Member memberB = new Member();
            memberB.setId(100L);

            //영속
            em.persist(memberA);
            em.persist(memberB);    // 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.

            // 커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
            tx.commit();    // 트랜잭션 커밋
        } catch (Exception e) {
            tx.rollback();  //문제가 생겼을 시 롤백.
        } finally {
            em.close();
        }

        emf.close();

    }
}
