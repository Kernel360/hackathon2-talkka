LOAD DATA INFILE '/var/lib/mysql-files/bus_route.csv'
    INTO TABLE BUS_ROUTE
    FIELDS TERMINATED BY ',' -- 필드 구분자
    ESCAPED BY '"'
    ENCLOSED BY '"' -- 텍스트 필드가 큰따옴표로 감싸져 있을 경우
    LINES TERMINATED BY '\n' -- 줄 구분자
    IGNORE 1 ROWS -- 첫 번째 행(헤더)을 무시

    (@route_id, @route_name) set route_id = @route_id, route_name = @route_name
;