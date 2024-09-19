# 🧑회원

## 회원 가입

### Request

`POST` /api/users

```json
{
    "email" : "email@urssu.com",
    "password" : "password",
    "username" : "username"
}
```

### Response

`201 Created` 

```json
{
    "email" : "email@urssu.com",
    "username" : "username"
}
```

`400 Bad Request`
- 입력한 이메일이 올바르지 않은 형식일 경우
- 이메일, 이름, 비밀번호를 각각 입력하지 않거나 255자를 초과할 경우

`409 Conflict`
- 입력한 이메일이 이미 회원가입 되었을 경우

## 인증(로그인)

### Request

`POST` /api/users/token

```json
{
    "email" : "email@urssu.com",
    "password" : "password"
}
```

### Response

`200 OK`

```json
{
    "token": "Header.Payload.Signature"
}
```

`400 Bad Request`
- 입력한 이메일이 올바르지 않은 형식일 경우
- 비밀번호가 일치하지 않은 경우
- 이메일, 비밀번호를 각각 입력하지 않거나 255자를 초과할 경우

`404 Not Found`
- 입력한 이메일에 해당하는 회원이 존재하지 않은 경우

## 회원 탈퇴

### Request

`DELETE` /api/users

-H `Authorization` Bearer aaa.bbb.ccc

### Response

`204 No Content`

`404 Not Found`
- 이미 탈퇴된 회원인 경우

# 📰게시글

## 게시글 작성

### Request

`POST` /api/articles

-H `Authorization` Bearer aaa.bbb.ccc

```json
{
    "title" : "title",
    "content" : "content"
}
```

### Response

`201 Created`

```json
{
    "articleId" : 1,
    "email" : "email@urssu.com",
    "title" : "title",
    "content" : "content"
}
```

`400 Bad Request`
- 입력한 제목 또는 본문이 "", " ", null인 경우(JSON)
- 입력한 제목 또는 본문이 각각 255자를 초과할 경우

`401 Unauthorized`
- 인가되지 않은 회원인 경우

## 게시글 수정

### Request(제목 또는 본문이 변경된 경우)

`PUT` /api/articles/{articleId}

-H `Authorization` Bearer aaa.bbb.ccc

```json
{
    "title" : "edited-title",
    "content" : "edited-content"
}
```

### Response

`201 Created`

```json
{
    "articleId" : 1,
    "email" : "email@urssu.com",
    "title" : "edited-title",
    "content" : "edited-content"
}
```

### Request(제목 또는 본문이 변경되지 않은 경우)

`PUT` /api/articles/{articleId}

-H `Authorization` Bearer aaa.bbb.ccc

```json
{
    "title" : "title",
    "content" : "content"
}
```

### Response

`200 OK`

```json
{
    "articleId" : 1,
    "email" : "email@urssu.com",
    "title" : "title",
    "content" : "content"
}
```

`400 Bad Request`
- 입력한 제목 또는 본문이 "", " ", null인 경우(JSON)
- 입력한 제목 또는 본문이 각각 255자를 초과할 경우

`401 Unauthorized`
- 인가되지 않은 회원인 경우

`403 Forbidden`
- 게시글 작성자의 요청이 아닌 경우

`404 Not Found`
- 존재하지 않는 게시글 번호일 경우

## 게시글 삭제하기

### Request

`DELETE` /api/articles/{articleId}

-H `Authorization` Bearer aaa.bbb.ccc

### Response

`204 No Content`

`401 Unauthorized`
- 인가되지 않은 회원인 경우

`403 Forbidden`
- 게시글 작성자의 요청이 아닌 경우

`404 Not Found`
- 존재하지 않는 게시글 번호일 경우


# 🗨️댓글

## 댓글 작성하기

### Request

`POST` /api/articles/{articleId}/comments

-H `Authorization` Bearer aaa.bbb.ccc

```json
{
    "content" : "content"
}
```

### Response

`201 Created`

```json
{
    "commentId" : 1,
    "email" : "email@urssu.com",
    "content" : "content"
}
```

`400 Bad Request`
- 입력한 댓글이 "", " ", null인 경우(JSON)
- 입력한 댓글이 각각 255자를 초과할 경우

`401 Unauthorized`
- 인가되지 않은 회원인 경우

`404 Not Found`
- 존재하지 않는 게시글 번호일 경우

## 댓글 수정하기

### Request(내용이 변경된 경우)

`PUT` /api/articles/{articleId}/comments/{commentId}

-H `Authorization` Bearer aaa.bbb.ccc

```json
{
    "content" : "edited-content"
}
```

### Response

`201 Created`

```json
{
    "commentId" : 1,
    "email" : "email@urssu.com",
    "content" : "edited-content"
}
```

### Request(내용이 변경되지 않은 경우)

`PUT` /api/articles/{articleId}/comments/{commentId}

-H `Authorization` Bearer aaa.bbb.ccc

```json
{
    "content" : "content"
}
```

### Response

`200 OK`

```json
{
    "commentId" : 1,
    "email" : "email@urssu.com",
    "content" : "content"
}
```

`400 Bad Request`
- 입력한 댓글이 "", " ", null인 경우(JSON)
- 입력한 댓글이 각각 255자를 초과할 경우
- 게시글 번호에 해당하지 않은 댓글 번호인 겅우

`401 Unauthorized`
- 인가되지 않은 회원인 경우

`403 Forbidden`
- 댓글 작성자의 요청이 아닌 경우

`404 Not Found`
- 존재하지 않는 게시글 번호일 경우
- 존재하지 않는 댓글 번호일 경우

## 댓글 삭제하기

### Request

`DELETE` /api/articles/{articleId}/comments/{commentId}

-H `Authorization` Bearer aaa.bbb.ccc

### Response

`204 No Content`

`400 Bad Request`
- 게시글 번호에 해당하지 않은 댓글 번호인 겅우

`401 Unauthorized`
- 인가되지 않은 회원인 경우

`403 Forbidden`
- 댓글 작성자의 요청이 아닌 경우

`404 Not Found`
- 존재하지 않는 게시글 번호일 경우
- 존재하지 않는 댓글 번호일 경우