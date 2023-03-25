package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {  //다른데서 가져다 쓸게 아님으로 public 지움
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save() {
        Member member = new Member();

    }
}
