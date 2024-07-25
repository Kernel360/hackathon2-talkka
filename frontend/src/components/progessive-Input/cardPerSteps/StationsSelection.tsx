import * as React from "react"

import { Button } from "@/components/ui/button"
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import debounce from "lodash/debounce";

import { ScrollArea } from "@/components/ui/scroll-area"
import { Separator } from "@/components/ui/separator"
import { GET_requestStationsPaginated, Station, StationListType } from "@/api/GET_requestBusStationList";
import { useCarousel } from "@/components/ui/carousel";
import { Route } from "@/api/GET_requestRouteList";


interface MOCK_TYPE {
    stations: Array<{
        name: string,
    }>
}

const MOCK_DATA: MOCK_TYPE = {
    stations: [
        { name: "사당역" },
        { name: "사당역" },
        { name: "사당역" },
        { name: "사당역" },
        { name: "사당역" },
        { name: "사당역" },
        { name: "사당역" },
        { name: "사당역" },
        { name: "사당역" },
        { name: "사당역" },
    ]
}

export interface StationSelectionProps {
    setSelection: Function;
    route?: Route;
}

export function StationSelection({
    setSelection,
    route,
}: StationSelectionProps) {

    const { orientation, scrollNext, canScrollNext } = useCarousel();

    const [ stationList, setStationList ] = React.useState<Station[]>([]);

    const [ pageNumber, setPageNumber ] = React.useState<number>(1);

    const PAGE_SIZE = 5;

    React.useEffect(() => {
        if (!route) {
            return;
        }

        setPageNumber(1);
        (async () => {
            const result = await GET_requestStationsPaginated(pageNumber, PAGE_SIZE, route);
            if (result) {
                setStationList(result.data);
            }
        })(/* IIFE */);

    }, []);

    const StationButtons = () => {
        return stationList.map((station, idx) => (
            <div key={idx}>
                <Button
                    variant="ghost"
                    className=" gap-x-1 py-2 w-full h-12"
                    onClick={(_) => {
                        setSelection(station);
                        scrollNext();
                    }}
                >
                    {station.station_name}
                </Button>
                <Separator className="my-0" />
            </div>
        ))
    }

    return (
        <div>
            <Card className="">
                <CardHeader>
                    <CardTitle>정류장 찾기</CardTitle>
                    <CardDescription>어떤 정류장을 이용하시나요?</CardDescription>
                </CardHeader>
                <ScrollArea className=" m-6 h-72 rounded-md border">
                    <StationButtons />
                </ScrollArea>
            </Card>
        </div>
    )
}
