-- 기본 DB / Schema 생성
CREATE DATABASE IF NOT EXISTS `BUS_DB`;
USE `BUS_DB`;
SOURCE /var/lib/mysql-files/schema.sql;
-- BULK INSERT LOCATIONS
SOURCE /var/lib/mysql-files/bulk_insert_routes.sql;
SOURCE /var/lib/mysql-files/bulk_insert_stations.sql;
SOURCE /var/lib/mysql-files/bulk_insert_locations.sql;
SOURCE /var/lib/mysql-files/bulk_insert_user_requests.sql;
-- BULK INSERT 이후 DB 의 FK CONSTRAINT 를 잡아줌
SOURCE /var/lib/mysql-files/add_constraints.sql;