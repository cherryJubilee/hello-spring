package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
/*    MemberService memberService = new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();*/

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void BeforeEach(){   // memberRepository와 memberService 객체를 생성하고 초기화
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");   //회원 객체의 이름 설정

        //when
        // 회원 가입 서비스를 호출하고, 결과로 회원 ID를 저장
        Long saveId = memberService.join(member);

        //then
        // 저장된 회원 ID를 사용하여 회원 정보를 조회
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 중복_회원_예외
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //방법2 ,뒤에 로직이 실패 하면 , 앞부분의 예외가 실행되어야 함
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try {   // 방법1 try-catch
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then

    }



    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}