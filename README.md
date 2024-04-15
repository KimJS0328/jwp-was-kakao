# 웹 애플리케이션 서버
## 진행 방법
* 웹 애플리케이션 서버 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 요구사항
- `/` 경로에서 `"Hello, World!"` 응답하기
- GET 파일 응답 처리하기
- CSS 지원하기
- Query String -> Post 방식으로 유저 회원가입 기능 구현하기
- 유저 회원가입 완료 후 Redirect 응답하기

## 기능
- [x] Request Header에서 Path를 추출한다.
- [x] Request Header에서 query parameter를 추출한다.
- [x] Path에 해당하는 파일 경로를 알아낸다.
- [x] 파일에 맞는 Content-Type을 알아낸다.
- [x] Path에 해당하는 파일이 없을 경우 404 status code를 반환한다.
- [x] POST body를 읽어낸다.
- [x] 유저 생성 API 요청을 처리하고 302 Redirect를 반환한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)
