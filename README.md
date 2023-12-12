# library-system
library-system application ver.2

## domain
- http://www.library-system.site:8090/index1
  ![image](https://github.com/ljbok/library-system/assets/149353095/db80c193-b82a-4b5d-9548-ca54d063d2e3)
  ![image](https://github.com/ljbok/library-system/assets/149353095/80ea74f5-51cd-4cf7-aa23-8e338457584f)

## 프로젝트 소개
- 프로젝트 유형 : 개인 프로젝트, 배포
- 프로젝트 명 : library-app / library-system
- 소개 : 사용자와 도서를 등록하고 도서 반납과 대출 기능 제공
<br>

## 🕰️ 개발 소요 기간
- 사전 "학습" 및 설계, 프로젝트 구성, 배포 등 모든 과정 포함하여 약 1~2주 소요
 <br>
 
## 📌  전체 기능 구성
- **(1) 사용자 명, 나이, 연락처로 사용자 정보 등록**
- **(2) 사용자 정보와 도서 정보 수정 (1),(2) ==> CRUD 기능 구성**
- **(3) 도서명, 저자로 도서 정보 등록**
- **(4) 사용자 번호와 도서 번호로 대출 및 반납 기능 구성**
<br>

## ⚙️ 개발 환경
- `Java 11`
- `JDK 11`
- **IDE** : 'IntelliJ(2023.2.3)1'
- **Framework** : 'Spring Boot(2.7.6)'
- **Database** : 'MySQL(8)'
- **ORM** : 'JPA'
- **build tool** : 'Gradle'
- **cloud & Web Hosting** : AWS, Gabia
<br>

## 파일 구성
```
📦 
├─ .gitignore
├─ README.md
├─ build.gradle
├─ gradle
│  └─ wrapper
│     ├─ gradle-wrapper.jar
│     └─ gradle-wrapper.properties
├─ gradlew
├─ gradlew.bat
├─ settings.gradle
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ example
   │  │        └─ refactoringlibraryapp
   │  │           ├─ RefactoringLibraryAppApplication.java
   │  │           ├─ controller
   │  │           │  ├─ book
   │  │           │  │  └─ BookController.java
   │  │           │  ├─ errorhandler
   │  │           │  │  └─ GlobalExceptionHandler.java
   │  │           │  ├─ index
   │  │           │  │  └─ IndexController.java
   │  │           │  ├─ loanhistory
   │  │           │  │  └─ LoanHistoryController.java
   │  │           │  └─ user
   │  │           │     └─ UserController.java
   │  │           ├─ domain
   │  │           │  ├─ book
   │  │           │  │  ├─ Book.java
   │  │           │  │  └─ BookRepository.java
   │  │           │  ├─ loanhistory
   │  │           │  │  ├─ LoanHistory.java
   │  │           │  │  └─ LoanHistoryRepository.java
   │  │           │  └─ user
   │  │           │     ├─ User.java
   │  │           │     └─ UserRepository.java
   │  │           ├─ dto
   │  │           │  ├─ book
   │  │           │  │  ├─ request
   │  │           │  │  │  └─ BookRequest.java
   │  │           │  │  └─ response
   │  │           │  │     └─ BookReponse.java
   │  │           │  ├─ errorhandler
   │  │           │  │  └─ response
   │  │           │  │     └─ ErrorResponse.java
   │  │           │  ├─ loanhistory
   │  │           │  │  ├─ request
   │  │           │  │  │  └─ LoanHistoryRequest.java
   │  │           │  │  └─ response
   │  │           │  │     └─ LoanHistoryResponse.java
   │  │           │  ├─ search
   │  │           │  │  └─ response
   │  │           │  │     ├─ BookSearchResponse.java
   │  │           │  │     └─ UserSearchResponse.java
   │  │           │  └─ user
   │  │           │     ├─ request
   │  │           │     │  └─ UserReqeust.java
   │  │           │     └─ response
   │  │           │        └─ UserResponse.java
   │  │           └─ service
   │  │              ├─ book
   │  │              │  └─ BookService.java
   │  │              ├─ loanhistory
   │  │              │  └─ LoanHistoryService.java
   │  │              └─ user
   │  │                 └─ UserService.java
   │  └─ resources
   │     ├─ application.yml
   │     └─ templates
   │        ├─ book-modify.html
   │        ├─ index1.html
   │        ├─ index2.html
   │        └─ user-modify.html
   └─ test
      └─ java
         └─ com
            └─ example
               └─ refactoringlibraryapp
                  └─ RefactoringLibraryAppApplicationTests.java
```
<br>

## 파일 설명
```
- Dto
  └─ request :사용자로 Body로 받을 객체
  └─ reponse : 사용자에게 JSON 타입으로 전달할 객체
- Controller
  └─ HTTP 요청에 따른 처리를 분기
- Service
  └─ Repository 객체를 생성해서 매개변수를 전달하여 데이터 처리를 진행할 Class
- Entity - domain
  └─  Repository Class에서 JPA로 Data 연산을 진행할 때 SQL 테이블과 연동될 객체
- Repository
  └─ JpaRepository를 상속받아 Service 또는 Entity로부터 매개변수를 전달받아 DB에 Data CRUD 작업을 수행할 Class
- LibraryAppApplication.java
  └─ 서버 시작을 위해 실행될 Class
```
