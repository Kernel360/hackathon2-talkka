import { RouteSelection } from "@/components/progessive-Input/cardPerSteps/RouteSelection";
import { StationSelection } from "@/components/progessive-Input/cardPerSteps/StationsSelection";
import { useEffect, useState } from "react";
import useEmblaCarousel from 'embla-carousel-react'
import {
    Carousel,
    CarouselContent,
    CarouselItem,
    CarouselNext,
    CarouselPrevious,
    useCarousel,
} from "@/components/ui/carousel"
import { TimeSelection } from "@/components/progessive-Input/cardPerSteps/TimeSelection";
import { SeatChart } from "@/components/progessive-Input/SeatChartFinal";
import { Route } from "@/api/GET_requestRouteList";
import { Station } from "@/api/GET_requestBusStationList";


function Wrapper({route, station, time, chart}: any) {

    return (
        <CarouselContent>
            <CarouselItem children={route}/>
            <CarouselItem children={station}/>
            <CarouselItem children={time}/>
            <CarouselItem children={chart}/>
        </CarouselContent>
    );
}

/**
 * https://www.embla-carousel.com/examples/predefined/#progress
 * https://www.embla-carousel.com/api/methods/
 */
export function RouteStatisticsPage() {

    const [route, setRoute] = useState<Route>();
    const [station, setStation] = useState<Station>();
    const [time, setTime] = useState<{begin:string, end:string}>();

    // TODO: change later
    const [steps, setSteps] = useState<number>(0);

    return (
        <div className="w-full h-[100vh] flex justify-center">
            <Carousel
                className="flex-1 h-full max-w-3xl content-center"
            >
                <Wrapper
                    route={<RouteSelection setSelection={setRoute} />}
                    station={<StationSelection setSelection={setStation} route={route} />}
                    time={<TimeSelection setSelection={setTime} route={route} station={station} />}
                    chart={<SeatChart route={route} station={station} time={time} />}
                />
            </Carousel>
        </div>
    )
}
