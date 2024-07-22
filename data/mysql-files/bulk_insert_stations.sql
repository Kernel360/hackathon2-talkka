LOAD DATA INFILE '/var/lib/mysql-files/bus_station.csv'
    INTO TABLE BUS_STATION
    FIELDS TERMINATED BY ',' -- 필드 구분자
    ESCAPED BY '"'
    ENCLOSED BY '"' -- 텍스트 필드가 큰따옴표로 감싸져 있을 경우
    LINES TERMINATED BY '\n' -- 줄 구분자
    IGNORE 1 ROWS -- 첫 번째 행(헤더)을 무시

    (@station_id, @station_name, @station_type, @station_eng_name, @registered_at, @latitude, @longitude)
    SET station_id = @station_id,
        station_name = @station_name,
        station_type = @station_type,
        station_name_en = @station_eng_name,
        registered_at = @registered_at,
        latitude = @latitude,
        longitude = @longitude
;