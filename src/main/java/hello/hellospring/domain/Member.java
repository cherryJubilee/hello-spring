package hello.hellospring.domain;

public class Member {
    private Long id;  // 고객을 구분하기 위한 시스템에서 정하는 id
    private String name;

    public Long getId() {  // cmd+n 으로 getter setter  만들기
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
