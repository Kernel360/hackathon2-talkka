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

    const [pageNumber, setPageNumber] = React.useState<number>(1);

    const PAGE_SIZE = 5;

    const debouncedHandler = debounce(() => {
        setPageNumber(1);
        (async () => {
            const result = await GET_requestRoutesPaginated(1, PAGE_SIZE, routeName);
            if (result) {
                setRouteList(result.data);
            }
        })(/* IIFE */);
    }, 120);

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setRouteName(e.target.value);
        if (routeName.length < 1) {
            return;
        }
        debouncedHandler();
    }

    const RouteButtons = () => {
        return routeList.map((route, idx) => (
            <div key={idx}>
                <Button
                    variant="ghost"
                    className=" gap-x-1 py-2 w-full h-12"
                    onClick={(_) => {
                        setSelection(route.routeName);
                        setSelected(true);
                        scrollNext();
                    }}
                >
                    <div
                        className="rounded-full align-middle text-center text-xs font-bold text-white bg-red-500 py-1 mx-2 px-2 mr-3">
                        {route.routeName}
                    </div>
                    <div>
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

    return (
        <div className="">
            <Popover
                open={(1 < routeName.length) && (false === selected)}
            >
                <PopoverTrigger asChild>
                    <Card className="">
                        <CardHeader>
                            <CardTitle>노선 찾기</CardTitle>
                            <CardDescription>어떤 노선을 이용하시나요?</CardDescription>
                        </CardHeader>
                        <CardContent>
                            <form>
                                <div className="grid w-full items-center gap-4">
                                    <div className="flex flex-row space-y-1.5">
                                        <Input
                                            id="route_name"
                                            placeholder="버스 노선을 입력해주세요"
                                            onChange={handleInputChange}
                                            value={routeName}
                                        />
                                    </div>
                                </div>
                            </form>
                        </CardContent>
                    </Card>
                </PopoverTrigger>
                <PopoverContent onOpenAutoFocus={(e) => e.preventDefault()}>
                    <ScrollArea className="h-72 rounded-md border">
                        <RouteButtons/>
                    </ScrollArea>
                </PopoverContent>
            </Popover>
        </div>
    )
}
