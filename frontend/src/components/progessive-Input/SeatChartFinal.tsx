import * as React from "react"

import { TrendingUp } from "lucide-react"
import { Button } from "@/components/ui/button"
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"

import { Bar, BarChart, CartesianGrid, LabelList, XAxis, YAxis } from "recharts"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { ScrollArea } from "@/components/ui/scroll-area"
import {
  ChartConfig,
  ChartContainer,
  ChartTooltip,
  ChartTooltipContent,
} from "@/components/ui/chart"
import { GET_requestAvailableSeats, TimeData } from "@/api/GET_requestAvailableSeats"
import { Route } from "@/api/GET_requestRouteList"
import { Station } from "@/api/GET_requestBusStationList"

export interface SeatChartProps {
  route?: Route
  station?: Station;
  time?: { begin: string, end: string };
}

type ChartDataListType = Array<{
  timeInterval: string,
  avgSeats: number,
}>;

const MOCK_DATA: ChartDataListType = [
  { timeInterval: "12:00", avgSeats: 45 },
  { timeInterval: "12:05", avgSeats: 35 },
  { timeInterval: "12:10", avgSeats: 25 },
  { timeInterval: "12:15", avgSeats: 15 },
  { timeInterval: "12:20", avgSeats: 5 },
  { timeInterval: "12:25", avgSeats: 2 },
];

const chartConfig = {
  avgSeats: {
    label: "평균 잔여석",
    color: "#222222",
  },
  p0: { // 단계 1
    label: "쾌적해요",
    color: "hsl(var(--chart-1))",
  },
  p1: { // 단계 2
    label: "충분해요",
    color: "hsl(var(--chart-2))",
  },
  p2: { // 단계 3
    label: "적당해요",
    color: "hsl(var(--chart-3))",
  },
  p3: { // 단계 4
    label: "아슬 아슬",
    color: "hsl(var(--chart-4))",
  },
  p4: {
    label: "포기가 편해요",
    color: "hsl(var(--chart-5))",
  },
} satisfies ChartConfig;


// p0 ~ p4
const getPhaseTags = (seats: number) => {
  if (seats <= 5)
    return chartConfig.p4;
  if (seats <= 15)
    return chartConfig.p3;
  if (seats <= 25)
    return chartConfig.p2;
  if (seats <= 35)
    return chartConfig.p1;
  if (seats <= 45)
    return chartConfig.p0;
}


export function SeatChart({
  route,
  station,
  time,
}: SeatChartProps) {

  const [chartDataList, setChartDataList] = React.useState<TimeData[]>([]);

  React.useEffect(() => {
    if (!route || !station || !time) {
    return;
    }
    (async () => {
        const result = await GET_requestAvailableSeats(route, station, time!.begin, time!.end);
        if (result) {
        setChartDataList(result.data.map((data) => {
          const tags = getPhaseTags(data.avgSeats);
          return {
            ...data,
            fill: tags?.color,
            label: tags?.label,
          } as any;
        }));
      }
    })(/* IIFE */);

   

  }, [route, station, time]);

  return (
    <Card>
      <CardHeader>
        <CardTitle>잔여석 기록</CardTitle>
        <CardDescription>{`지역 ${route?.regionName} 노선 ${route?.routeName}`}</CardDescription>
      </CardHeader>
      <CardContent>

        <ScrollArea className="h-72">
          <ChartContainer
            className="h-72 rounded-md border"
            config={chartConfig}
          >
            <BarChart
              accessibilityLayer
              data={chartDataList}
              layout="vertical"
              margin={{
                // left: 10,
                // right: 10,
              }}
            >

              <CartesianGrid vertical={true} />

              <YAxis
                dataKey="timeInterval"
                type="category"
                tickLine={true}
                axisLine={false}
                tickMargin={10}
                minTickGap={40}
                tickFormatter={(value) => {
                  return value;
                }}
              />
              <XAxis
                dataKey="avgSeats"
                type="number"
                hide
              />
              <ChartTooltip
                cursor={true}
                content={
                  <ChartTooltipContent
                    indicator="dashed"
                    color={chartConfig.avgSeats.color}
                  />
                }
              />
              <Bar dataKey="avgSeats" layout="vertical" radius={2} >
                <LabelList
                  dataKey="avgSeats"
                  position="right"
                  offset={15}
                  className="fill-foreground"
                  fontSize={12}
                />
              </Bar>
            </BarChart>
          </ChartContainer>
        </ScrollArea>
      </CardContent>
      <CardFooter className="flex-col items-start gap-2 text-sm">
        <div className=" text-right text-muted-foreground w-full">
          시간 별 잔여 좌석 데이터
        </div>
      </CardFooter>
    </Card>
  )
}
