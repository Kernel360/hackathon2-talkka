# hackathon2-gyeonggiBus

- MySQL 기초 DB 세팅

```shell
$ mysql -u root -p
# 비밀번호 입력
$ source /var/lib/mysql-files/init_db.sql
# schema 초기화 확인 
```

- location 관련 dummy data 주입

```shell
-- MySQL 접속 필요함.
$ source /var/lib/mysql-files/bulk_insert_route_loations_dummy.sql
```