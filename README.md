# 유어슈 Back-end팀 과제 - ✍🏻블로그 만들기

[API명세 바로가기](API_DOCUMENTATION.md)

## 🚀프로그래밍 요구사항

- Gradle, Java21를 사용한다.
- 상황에 맞게 적절히 에러를 처리해야 한다.
- 테스트 코드를 가능한 많이 작성한다.
- 라이브러리는 자유롭게 사용할 수 있다.


## ⚙구현할 기능 목록

### 🧑회원

#### 회원가입

- 이메일, 이름, 비밀번호를 가지는 회원을 생성한다.
- 비밀번호를 단방향 알고리즘으로 암호화하여 저장한다.
- 회원가입이 완료되면 이메일, 이름을 반환한다.
- 예외처리
  - 입력한 이메일이 올바르지 않은 형식일 경우
  - 이메일, 이름, 비밀번호를 각각 입력하지 않거나 255자를 초과할 경우
  - 입력한 이메일이 이미 회원가입 되었을 경우


#### 인증(로그인)

- 이메일, 비밀번호와 일치하는 회원의 엑세스 토큰을 발급한다.
- 예외처리
  - 입력한 이메일이 올바르지 않은 형식일 경우
  - 비밀번호가 일치하지 않은 경우
  - 이메일, 비밀번호를 각각 입력하지 않거나 255자를 초과할 경우
  - 입력한 이메일에 해당하는 회원이 존재하지 않은 경우

#### 회원탈퇴

- 인가된 회원을 탈퇴한다.
- 해당 회원이 작성한 모든 게시글과 댓글을 삭제한다.
- 게시글에 해당하는 댓글도 모두 삭제한다.
- 예외처리
    - 이미 탈퇴된 회원인 경우


### 📰게시글

#### 게시글 작성

- 제목, 본문을 가지는 게시글을 작성한다.
- 인가된 회원인 경우에만 게시글을 작성한다.
- 게시글 작성이 완료되면 게시글 번호, 이메일, 제목, 본문을 반환한다.
- 예외처리
    - 입력한 제목 또는 본문이 "", " ", null인 경우(JSON)
    - 입력한 제목 또는 본문이 각각 255자를 초과할 경우
    - 인가되지 않은 회원인 경우

#### 게시글 수정

- 게시글 번호에 해당하는 게시글의 제목, 본문을 수정한다.
- 인가된 회원인 경우에만 게시글을 수정한다.
- 게시글 작성자의 요청인 경우에만 게시글을 수정한다.
- 게시글 번호는 경로 매개변수로 받는다.
- 게시글 수정이 완료되면 게시글 번호, 이메일, 제목, 본문을 반환한다.
- 예외처리
    - 입력한 제목 또는 본문이 "", " ", null인 경우(JSON)
    - 입력한 제목 또는 본문이 각각 255자를 초과할 경우
    - 인가되지 않은 회원인 경우
    - 게시글 작성자의 요청이 아닌 경우
    - 존재하지 않는 게시글 번호일 경우

#### 게시글 삭제

- 게시글 번호에 해당하는 게시글을 삭제한다.
- 해당 게시글에 있는 댓글들 또한 모두 삭제한다.
- 인가된 회원인 경우에만 게시글을 수정한다.
- 게시글 작성자의 요청인 경우에만 게시글을 삭제한다.
- 게시글 번호는 경로 매개변수로 받는다.
- 예외처리
    - 인가되지 않은 회원인 경우
    - 게시글 작성자의 요청이 아닌 경우
    - 존재하지 않는 게시글 번호일 경우


### 🗨️댓글

#### 댓글 작성

- 게시글 번호에 해당하는 게시글에 댓글을 작성한다.
- 인가된 회원인 경우에만 댓글을 작성한다.
- 게시글 번호는 경로 매개변수로 받는다.
- 댓글 작성이 완료되면 댓글 번호, 이메일, 댓글을 반환한다.
- 예외처리
    - 입력한 댓글이 "", " ", null인 경우(JSON)
    - 입력한 댓글이 255자를 초과할 경우
    - 인가되지 않은 회원인 겅우
    - 존재하지 않는 게시글 번호일 경우

#### 댓글 수정

- 게시글 번호와 댓글 번호에 해당하는 댓글을 수정한다.
- 인가된 회원인 경우에만 댓글을 수정한다.
- 댓글 작성자의 요청인 경우에만 댓글을 수정한다.
- 게시글 번호와 댓글 번호는 경로 매개변수로 받는다.
- 댓글 수정이 완료되면 댓글 번호, 이메일, 댓글을 반환한다.
- 예외처리
    - 입력한 댓글이 "", " ", null인 경우(JSON)
    - 입력한 댓글이 255자를 초과할 경우
    - 게시글 번호에 해당하지 않은 댓글 번호인 겅우
    - 인가되지 않은 회원인 경우
    - 댓글 작성자의 요청이 아닌 경우
    - 존재하지 않는 게시글 번호일 경우
    - 존재하지 않는 댓글 번호일 경우

#### 댓글 삭제

- 게시글 번호와 댓글 번호에 해당하는 댓글을 삭제한다.
- 인가된 회원인 경우에만 댓글을 삭제한다.
- 댓글 작성자의 요청인 경우에만 댓글을 삭제한다.
- 게시글 번호와 댓글 번호는 경로 매개변수로 받는다.
- 예외처리
    - 게시글 번호에 해당하지 않은 댓글 번호인 경우
    - 인가되지 않은 회원인 경우
    - 댓글 작성자의 요청이 아닌 경우
    - 존재하지 않는 게시글 번호일 경우
    - 존재하지 않는 댓글 번호일 경우
