package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {  // 회원 서비스 만들기
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        //result.orElseGet(); // 값이 있으면~ 없으면~ 디폴트를 꺼낸다. (안쓰는 방식)
        // 좀 더 간결하게 표현할 수 있음
        /* Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        validateDuplicateMember(member);  // 메서드 추출(중복 회원 검증)

        memberRepository.save(member); //member 객체의 데이터를 저장소에 저장
        return member.getId();    // 저장된 회원의 ID를 반환

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }


    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();   // findAll()의 반환 타입: 리스트 -> 그대로 작성

    }

    public Optional<Member> findOne(Long memberId) { // Long 타입의 memberId를 인자로 받아, 저장소에서 해당 ID를 가진 회원을 찾아 반환
        return memberRepository.findById(memberId);
    }

}
