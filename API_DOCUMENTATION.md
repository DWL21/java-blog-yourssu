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

## 회원 탈퇴

### Request

`DELETE` /api/users

-H `Authorization` Bearer aaa.bbb.ccc

### Response

`204 No Content`

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

## 게시글 삭제하기

### Request

`DELETE` /api/articles/{articleId}

-H `Authorization` Bearer aaa.bbb.ccc

### Response

`204 No Content`

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

## 댓글 삭제하기

### Request

`DELETE` /api/articles/{articleId}/comments/{commentId}

-H `Authorization` Bearer aaa.bbb.ccc

### Response

`204 No Content`
