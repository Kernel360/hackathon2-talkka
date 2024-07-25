LOAD DATA INFILE '/var/lib/mysql-files/bus_route.csv'
    INTO TABLE bus_route
    FIELDS TERMINATED BY ',' -- 필드 구분자
    ESCAPED BY '"'
    ENCLOSED BY '"' -- 텍스트 필드가 큰따옴표로 감싸져 있을 경우
    LINES TERMINATED BY '\n' -- 줄 구분자
;