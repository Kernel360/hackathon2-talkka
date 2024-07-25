import * as React from "react"

import { Button } from "@/components/ui/button"
import { Minus } from 'lucide-react';
import {
    Card,
    CardContent,
    CardDescription,
    CardFooter,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import {
    Popover,
    PopoverContent,
    PopoverTrigger,
} from "@/components/ui/popover"
import debounce from "lodash/debounce";

import { GET_requestRoutesPaginated, Route } from "@/api/GET_requestRouteList"
import { ScrollArea } from "@/components/ui/scroll-area"
import { Separator } from "@/components/ui/separator"
import useEmblaCarousel, { UseEmblaCarouselType } from "embla-carousel-react/components/useEmblaCarousel";
import { useCarousel } from "@/components/ui/carousel";
import { Station } from "@/api/GET_requestBusStationList";

export interface RouteSelectionProps {
    setSelection: React.Dispatch<React.SetStateAction<{ begin: string, end: string } | undefined>>;
    route?: Route;
    station?: Station;
}

export function TimeSelection({
    setSelection,
    route,
    station,
}: RouteSelectionProps) {

    const { orientation, scrollNext, canScrollNext } = useCarousel();
    const [beginTime, setBeginTime] = React.useState<string>();
    const [endTime, setEndTime] = React.useState<string>();

    const isInputDone = () => (!beginTime || !endTime);

    return (
        <Card className="">
            <CardHeader>
                <CardTitle>시간 선택</CardTitle>
                <CardDescription>언제가 궁금하세요?</CardDescription>
            </CardHeader>
            <CardContent>
                <form>
                    <div className="flex w-full items-center gap-2 justify-between">
                        <div className="flex flex-col space-y-1.5">
                            <Label htmlFor="time_begin">From</Label>
                            <Input
                                id="time_begin"
                                type="time"
                                step="3600000"
                                onChange={(e) => setBeginTime(e.target.value)}
                            />
                        </div>
                        <div className="flex flex-col space-y-1.5">
                            <Label htmlFor="time_end">To</Label>
                            <Input
                                id="time_end"
                                type="time"
                                step="3600000"
                                onChange={(e) => setEndTime(e.target.value)}
                            />
                        </div>
                    </div>
                </form>
                <Button
                    variant={ isInputDone() ? "outline" : "destructive"}
                    type="submit"
                    className=" w-full mt-5"
                    disabled={ isInputDone() }
                    onClick={(e) => {
                        setSelection({begin: beginTime!, end: endTime!});
                        scrollNext();
                    }}
                >
                    {isInputDone() ? "시간을 설정해주세요" : "조회 하기"}
                </Button>
            </CardContent>
        </Card>
    )
}
