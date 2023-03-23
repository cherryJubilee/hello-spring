package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //사용자가 적는 아이디 세팅
        store.put(member.getId(), member);  //스토어에 저장되면서 map에도 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //스토어에서 꺼내기 -> 얻은getId 넣기
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(Long name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store.values()는 Member 이다.

    }











}
