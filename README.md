# hackathon2-gyeonggiBus

- 프로젝트 시작 전 기본 설정
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
    - MySQL 기초 DB 세팅
        - 버스 정류장 / 버스 노선 정보 획득이 필요합니다.
        - 아래 shell 스크립트 설명 참고.

- 기본 DB 설정 script

```shell
$ docker exec -it bus-mysql sh
# docker container 안의 작업입니다.
$ mysql -u root -p
# 비밀번호 입력
$ source /var/lib/mysql-files/init_db.sql
# schema 초기화 확인 
$ show tabels;
# location 관련 dummy data 주입 (FE 작업 테스트 용도, Production 에서는 사용해서는 안됨.)
$ source /var/lib/mysql-files/bulk_insert_route_loations_dummy.sql
```

- JDK : Java 17 사용