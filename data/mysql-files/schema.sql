DROP TABLE IF EXISTS `BUS_ROUTE`;
CREATE TABLE `BUS_ROUTE`
(
    `route_id`           BIGINT(32)   NOT NULL PRIMARY KEY COMMENT '교통 API 상 식별자 (UNIQUE)',
    `route_name`         VARCHAR(100) NOT NULL COMMENT '노선 번호',
    `route_type_cd`      INT          NOT NULL COMMENT '노선 유형 코드',
    `route_type_name`    VARCHAR(50)  NOT NULL COMMENT '노선 유형명',
    `company_id`         VARCHAR(20)  NOT NULL COMMENT '운수업체 아이디',
    `company_name`       VARCHAR(50)  NOT NULL COMMENT '운수업체명',
    `company_tel`        VARCHAR(15) COMMENT '운수업체 전화번호',
    `district_cd`        INT          NOT NULL COMMENT '관할 지역 코드',
    `up_first_time`      VARCHAR(20)  NOT NULL COMMENT '평일 기점 첫차 시간',
    `up_last_time`       VARCHAR(20)  NOT NULL COMMENT '평일 기점 막차 시간',
    `down_first_time`    VARCHAR(20)  NOT NULL COMMENT '평일 종점 첫차 시간',
    `down_last_time`     VARCHAR(20)  NOT NULL COMMENT '평일 종점 막차 시간',
    `start_mobile_no`    VARCHAR(10) COMMENT '기점 정류소 번호',
    `start_station_id`   BIGINT(32)   NOT NULL COMMENT '기점 정류소 아이디',
    `start_station_name` VARCHAR(100) NOT NULL COMMENT '기점 정류소명',
    `end_station_id`     BIGINT(32)   NOT NULL COMMENT '종점 정류소 아이디',
    `end_station_name`   VARCHAR(100) NOT NULL COMMENT '종점 정류소명',
    `region_name`        VARCHAR(100) COMMENT '지역명',
    `peek_alloc`         INT          NOT NULL COMMENT '평일 최소 배차 시간',
    `n_peek_alloc`       INT          NOT NULL COMMENT '평일 최대 배차 시간',
    `created_at`         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '데이터 생성일자'
);


DROP TABLE IF EXISTS `BUS_LOCATION`;
CREATE TABLE `BUS_LOCATION`
(
    `bus_location_id`   BIGINT(32)  NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '단순 식별자',
    `route_id`          BIGINT(32)  NOT NULL COMMENT '교통 API 상 버스 노선 ID',
    `station_id`        BIGINT(32)  NOT NULL COMMENT '교통 API 상 버스 정류장 ID',
    `station_seq`       INT         NOT NULL COMMENT '기점으로부터의 정류장 순서',
    `end_bus`           VARCHAR(10) NOT NULL DEFAULT '0' COMMENT '0: 일반, 1: 막차',
    `low_plate`         VARCHAR(10) NOT NULL DEFAULT '0' COMMENT '0: 일반버스, 1: 저상버스',
    `plate_no`          VARCHAR(32) NOT NULL COMMENT '운행 차량 번호',
    `plate_type`        VARCHAR(10) NOT NULL DEFAULT '0' COMMENT '0: 정보없음, 1: 소형승합차, 2: 중형승합차, 3: 대형승합차, 4: 2층버스',
    `remain_seat_count` INT         NOT NULL DEFAULT -1 COMMENT '-1 : 정보 없음',
    `created_at`        TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);


DROP TABLE IF EXISTS `BUS_ROUTE_STATION`;
CREATE TABLE `BUS_ROUTE_STATION`
(
    `bus_route_station_id` BIGINT(32)      NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '단순 식별자',
    `station_id`           BIGINT(32)      NOT NULL COMMENT '정류소 아이디',
    `route_id`             BIGINT(32)      NOT NULL COMMENT '노선 아이디',
    `station_seq`          INT             NOT NULL COMMENT '정류소 순번',
    `station_name`         VARCHAR(100)    NOT NULL COMMENT '정류소 명칭',
    `region_name`          VARCHAR(30) COMMENT '정류소 위치 지역명',
    `district_cd`          VARCHAR(30)     NOT NULL COMMENT '노선 관할 지역 코드',
    `center_yn`            VARCHAR(30)              DEFAULT 'N' COMMENT '중앙차로 여부 (N: 일반, Y: 중앙차로)',
    `turn_yn`              VARCHAR(30)              DEFAULT 'N' COMMENT '회차점 여부 (N: 일반, Y: 회차점)',
    `longitude`            DECIMAL(17, 14) NOT NULL COMMENT '정류소 경도',
    `latitude`             DECIMAL(17, 14) NOT NULL COMMENT '정류소 위도',
    `created_at`           TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS `USER_COLLECT_REQUEST`;
CREATE TABLE `USER_COLLECT_REQUEST`
(
    `user_collect_request_id` BIGINT(32)   NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '단순 식별자',
    `route_id`                BIGINT(32)   NOT NULL COMMENT '노선 ID',
    `status`                  VARCHAR(255) NOT NULL,
    `created_at`              TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);
