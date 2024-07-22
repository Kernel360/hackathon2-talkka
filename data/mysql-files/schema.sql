DROP TABLE IF EXISTS `BUS_STATION`;
CREATE TABLE `BUS_STATION`
(
    `station_id`       BIGINT(32)   NOT NULL PRIMARY KEY COMMENT '교통 API 상 식별자 (UNIQUE)',
    `station_name`     VARCHAR(100) NOT NULL COMMENT '정류장 이름',
    `station_location` POINT        NOT NULL COMMENT '정류장 좌표(위도, 경도)',
    `station_name_en`  VARCHAR(200) NOT NULL COMMENT '정류장 영문 이름',
    `station_type`     VARCHAR(10)  NOT NULL COMMENT '정류장 유형',
    `registered_at`    TIMESTAMP    NOT NULL COMMENT '교통 API 상 등록일자',
    `created_at`       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '데이터 생성일자'
);

DROP TABLE IF EXISTS `BUS_ROUTE`;
CREATE TABLE `BUS_ROUTE`
(
    `route_id`   BIGINT(32)   NOT NULL PRIMARY KEY COMMENT '교통 API 상 식별자 (UNIQUE)',
    `route_name` VARCHAR(100) NOT NULL COMMENT '노선 이름',
    `created_at` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '데이터 생성일자'
);


DROP TABLE IF EXISTS `BUS_ROUTE_LOCATION`;
CREATE TABLE `BUS_ROUTE_LOCATION`
(
    `BUS_ROUTE_location_id` BIGINT(32)                     NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '단순 식별자',
    `route_id`              BIGINT(32)                     NOT NULL COMMENT '교통 API 상 버스 노선 ID',
    `station_id`            BIGINT(32)                     NOT NULL COMMENT '교통 API 상 버스 정류장 ID',
    `station_seq`           SMALLINT                       NOT NULL COMMENT '기점으로부터의 정류장 순서',
    `end_bus`               ENUM ('0', '1')                NOT NULL DEFAULT '0' COMMENT '0: 일반, 1: 막차',
    `low_plate`             ENUM ('0', '1')                NOT NULL DEFAULT '0' COMMENT '0: 일반버스, 1: 저상버스',
    `plate_no`              VARCHAR(32)                    NOT NULL COMMENT '운행 차량 번호',
    `plate_type`            ENUM ('0', '1', '2', '3', '4') NOT NULL DEFAULT '0' COMMENT '0: 정보없음, 1: 소형승합차, 2: 중형승합차, 3: 대형승합차, 4: 2층버스',
    `remain_seat_count`     SMALLINT                       NOT NULL DEFAULT -1 COMMENT '-1 : 정보 없음',
    `created_at`            TIMESTAMP                      NOT NULL DEFAULT CURRENT_TIMESTAMP
);


DROP TABLE IF EXISTS `BUS_ROUTE_STATION`;
CREATE TABLE `BUS_ROUTE_STATION`
(
    `BUS_ROUTE_stop_id` BIGINT(32)   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '단순 식별자',
    `route_id`          BIGINT(32)   NOT NULL,
    `station_id`        BIGINT(32)   NOT NULL,
    `station_seq`       SMALLINT     NOT NULL,
    `station_name`      VARCHAR(100) NOT NULL,
    `region_name`       VARCHAR(100) NOT NULL,
    `district_cd`       VARCHAR(100) NOT NULL,
    `created_at`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `USER_COLLECT_REQUEST`;
CREATE TABLE `USER_COLLECT_REQUEST`
(
    `user_collect_request_id` BIGINT(32)   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '단순 식별자',
    `route_id`                BIGINT(32)   NOT NULL COMMENT '노선 ID',
    `route_name`              VARCHAR(255) NOT NULL COMMENT '노선 이름',
    `status`                  VARCHAR(255) NOT NULL,
    `created_at`              TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

