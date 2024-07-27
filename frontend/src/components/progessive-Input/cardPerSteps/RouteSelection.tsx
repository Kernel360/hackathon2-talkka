import * as React from "react"

import {Button} from "@/components/ui/button"
import {MoveHorizontal} from 'lucide-react';
import {Card, CardContent, CardDescription, CardHeader, CardTitle,} from "@/components/ui/card"
import {Input} from "@/components/ui/input"
import {Popover, PopoverContent, PopoverTrigger,} from "@/components/ui/popover"
import debounce from "lodash/debounce";

import {GET_requestRoutesPaginated, Route} from "@/api/GET_requestRouteList"
import {ScrollArea} from "@/components/ui/scroll-area"
import {Separator} from "@/components/ui/separator"
import {useCarousel} from "@/components/ui/carousel";
import { GET_requestSearchBus, RouteBusXXX } from "@/api/GET_requestSearchBus";


interface MOCK_TYPE {
    routes: Array<{
        name: string,
        station_start: string,
        station_end: string,
    }>
}

const MOCK_DATA: MOCK_TYPE = {
    routes: [
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
        {name: "7780", station_start: "경기대", station_end: "사당역"},
    ]
}

export interface RouteSelectionProps {
    setSelection: Function;
}

export function RouteSelection({
                                   setSelection,
                               }: RouteSelectionProps) {

    const {orientation, scrollNext, canScrollNext} = useCarousel();

    const [routeName, setRouteName] = React.useState<string>("");

    const [selected, setSelected] = React.useState<boolean>(false);

    const [routeList, setRouteList] = React.useState<Route[]>([]);

    const [busList, setBusList] = React.useState<RouteBusXXX[]>([]);

    const [pageNumber, setPageNumber] = React.useState<number>(0);

    const PAGE_SIZE = 999;

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setRouteName(e.target.value);
    }

    const RouteButtons = () => {
        return routeList.map((route, idx) => (
            <div key={idx}>
                <Button
                    variant="ghost"
                    className=" gap-x-1 py-2 w-full h-12"
                    onClick={(_) => {
                        setSelection(route);
                        setSelected(true);
                        scrollNext();
                    }}
                >
                    <div
                        className="rounded-full align-middle text-center text-xs font-bold text-white bg-red-500 py-1 mx-2 px-2 mr-3">
                        {route.routeName}
                    </div>
                    <div className=" flex flex-row w-60">
                        {route.stationStart}
                        <MoveHorizontal size={20} color="#888888"/>
                        {route.stationEnd}
                    </div>
                    {`지역-${route.regionName}`}
                </Button>
                <Separator className="my-0"/>
            </div>
        ))
    }

    const RouteButtons222 = () => {
        return routeList.map((route, idx) => (
            <div key={idx}>
                <Button
                    variant="ghost"
                    className=" gap-x-1 py-2 w-full h-12"
                    onClick={(_) => {
                        setSelection(route);
                        setSelected(true);
                        scrollNext();
                    }}
                >
                    <div
                        className="rounded-full align-middle text-center text-xs font-bold text-white bg-red-500 py-1 mx-2 px-2 mr-3">
                        {route.routeName}
                    </div>
                    <div className=" flex flex-row w-60">
                        {route.stationStart}
                        <MoveHorizontal size={20} color="#888888"/>
                        {route.stationEnd}
                    </div>
                    {`지역-${route.regionName}`}
                </Button>
                <Separator className="my-0"/>
            </div>
        ))
    }

    const handleSubmit = () => {
        setPageNumber(0);
        (async () => {
            const result = await GET_requestRoutesPaginated(pageNumber, PAGE_SIZE, routeName);
            if (result) {
                // console.log(result.data);
                setRouteList(result.data);
            }
        })(/* IIFE */)
    };

    const handleAddNewRoute = () => {
            (async () => {
            const result2 = await GET_requestSearchBus(routeName);
            if (result2) {
                console.log(result2.data);
                setBusList([...result2.data]);
                console.log("after")
                console.log(busList);
            }
        })(/* IIFE */)
    }

    return (
        <div className=" min-w-72">
            <Popover
                open={routeList != undefined && routeList.length >= 1 && !selected}
            >
                <PopoverTrigger asChild>
                    <Card className="">
                        <CardHeader>
                            <CardTitle>노선 찾기</CardTitle>
                            <CardDescription>어떤 노선을 이용하시나요?</CardDescription>
                        </CardHeader>
                        <CardContent className=" flex items-center flex-row space-y-1.5">
                            <form>
                                  <Input
                                            id="route_name"
                                            placeholder="버스 노선을 입력해주세요"
                                            onChange={handleInputChange}
                                            value={routeName}
                                            onKeyDown={(event) => {
                                                 if (event.key === "Enter") {
                                                    // Cancel the default action, if needed
                                                    event.preventDefault();
                                                    handleSubmit();
                                                }
                                            }}
                                        />
                            </form>
                            <Button
                                type="submit"
                                className=""
                                onClick={(e) => {
                                    handleSubmit();
                                    setBusList([]);
                                }}
                            >
                            검색하기
                            </Button>
                              {/* <Button
                                type="submit"
                                className=""
                                onClick={(e) => {
                                    console.log(busList);
                                    handleAddNewRoute();
                                    setRouteList([]);
                                }}
                            >
                            추가하기
                            </Button> */}
                        </CardContent>
                    </Card>
                </PopoverTrigger>
                <PopoverContent
                 className=" w-72 border-2"
                 >
                    <ScrollArea className="h-72 rounded-md border">

                        {
                            routeList && (routeList.length > 0) && <RouteButtons/>
                        }
                        {
                            busList && (busList.length > 0) && <RouteButtons222/>
                        }

                    </ScrollArea>
                </PopoverContent>
            </Popover>
        </div>
    )
}
