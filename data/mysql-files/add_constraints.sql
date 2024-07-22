ALTER TABLE `bus_route_station`
    ADD CONSTRAINT `FK_bus_route_TO_bus_route_station_1` FOREIGN KEY (`route_id`)
        REFERENCES `bus_route` (`route_id`);

ALTER TABLE `bus_route_station`
    ADD CONSTRAINT `FK_bus_station_TO_bus_route_station_1` FOREIGN KEY (`station_id`)
        REFERENCES `bus_station` (`station_id`);

ALTER TABLE `user_collect_request`
    ADD CONSTRAINT `FK_bus_route_TO_user_collect_request_1` FOREIGN KEY (`route_id`)
        REFERENCES `bus_route` (`route_id`);