package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Table(name = "MBR") <- 테이블 이름이 디비와 다를 경우 or 테이블 이름 축약을 따로 해야할 경우
public class Member {

    @Id
    private Long id;
    private String name;

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}