LOAD DATA INFILE '/var/lib/mysql-files/bus_location_dummy_data.csv'
    INTO TABLE BUS_ROUTE_LOCATION
    FIELDS TERMINATED BY ',' -- 필드 구분자
    ESCAPED BY '"'
    ENCLOSED BY '"' -- 텍스트 필드가 큰따옴표로 감싸져 있을 경우
    LINES TERMINATED BY '\n' -- 줄 구분자
    IGNORE 1 ROWS -- 첫 번째 행(헤더)을 무시

    (@bus_route_location_id, @route_id, @station_id, @station_seq, @end_bus, @low_plate, @plate_no, @plate_type,
     @remain_seat_count, @created_at)
    SET BUS_ROUTE_location_id = @bus_route_location_id,
        route_id = @route_id,
        station_id = @station_id,
        station_seq = @station_seq,
        end_bus = @end_bus,
        low_plate = @low_plate,
        plate_no = @plate_no,
        plate_type = @plate_type,
        remain_seat_count = @remain_seat_count,
        created_at = @created_at
;