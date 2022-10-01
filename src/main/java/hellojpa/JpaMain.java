package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] main){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();   //쉽게 이해하자면 DB Connection을 하나 만든 것.

        EntityTransaction tx = em.getTransaction(); //트랜잭션
        tx.begin(); //트랜잭션 시작

        // try-catch로 정석적인 예외 처리를 함. -> 이 역할은 이제 Spring이 해줌.
        try {
            //영속
            Member findMember = em.find(Member.class, 1L);  // em: 객체를 대신 저장해주는 역할. 첫 번째 파라미터 : 엔터티클래스, 두 번째 파라미터 : PK
            //em.remove : delete 쿼리 나가면서 삭제
            findMember.setName("AAAAA");

            List<Member> result = em.createQuery( "select m  from Member", Member.class)        // JPQL : 직접 쿼리를 칠 수 있다. Member 엔티티를 타겟.
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }

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
