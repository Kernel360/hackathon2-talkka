-- 기본 DB / Schema 생성
CREATE DATABASE IF NOT EXISTS `BUS_DB`;
USE `BUS_DB`;
SOURCE /var/lib/mysql-files/schema.sql;
-- 버스 노선 정보 / 버스 정류장 정보 삽입
SOURCE /var/lib/mysql-files/bulk_insert_routes.sql;
-- BULK INSERT 이후 DB 의 FK CONSTRAINT 를 잡아줌
SOURCE /var/lib/mysql-files/add_constraints.sql;