# hackathon2-gyeonggiBus

## 프로젝트 소개

"빨간 버스... 이 시간에는 탈 수 있을까?"

경기도민은 늘 버스를 기다리며 하루를 허비합니다...

이 프로젝트는 과거의 버스 좌석 기록을 사용자에게 제공하여 보다 더 나은 출퇴근을 도와주는 서비스를 제공합니다.

### 프로젝트 목표

- 해커톤 목표
    - [x] 버스 노선 / 정류장 / 시간에 따른 좌석 수 집계
    - [x] 간편한 UI 제공
- 추가 목표
    - [ ] 버스 노선 / 정류장 / 시간에 따른 혼잡도 예측
    - [ ] 다양한 대중교통에 대한 혼잡도 정보 제공

### 프로젝트 기술 스택

- Backend : `Spring Boot 3.3.2` (`Java 17`)
- Frontend : `React.js` (`TypeScript`, `Shadcn ui`)
- DB : `MySQL 8.0`
- Infra: `Docker`, `Docker Compose`, `photogrammer.me home-server`

## 프로젝트 개발 가이드

### 팀 개발 환경 가이드

- JDK : Corretto 17
- IntelliJ 설정
    - Code Formatter 설정
        - ./.idea 에 위치한 `naver-intellij-formatter.xml` 파일을 이용하여 설정
        - Settings -> CodeStyle -> Java -> Import Scheme
    - CheckStyle 설정
        - Plugin CheckStyle 설치
        - ./.idea 에 위치한 `naver-checkstyle-rules.xml` 파일을 이용하여 설정
        - suppression file 의 경우 `./.idea/naver-checkstyle-suppresssions.xml` 지정
    - EnvFile 설정
        - `.env.sample` 토대로 `.env` 파일 생성
        - Plugin 설치 (EnvFile)
        - Run -> Edit Configurations -> EnvFile 활성화 / `.env` 파일 지정

### 프로젝트 실행

- 주의: JPA DDL 활성화하지 않음. 반드시 아래 DB 설정을 수행할 것.

```shell
$ cd ./bus-seat
$ ./gradlew clean build --exclude-task test
$ cd ..
$ docker compose up -d
```

### DB 설정

- 기본 DB 설정 script
    - 실제 정보 (7800 번의 24/07/24 14:30 ~ 23:59) 의 데이터를 바탕으로 구성된 데이터를 로드합니다.

```shell
$ docker exec -it bus-mysql sh
# docker container 안의 작업입니다.
$ mysql -u root -p
# 비밀번호 입력
$ source /var/lib/mysql-files/init_db.sql
# schema 초기화 확인 
$ show tabels;
```
