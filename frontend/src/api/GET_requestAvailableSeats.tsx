import { ZodStringDef } from "zod";
import { API_URL } from "./API_URL";
import { Route } from "./GET_requestRouteList";
import { Station } from "./GET_requestBusStationList";

export interface GET_requestFormat {
  route_name: string,
  station_name: string,
  time_begin: string,
  time_end: string,
}

export interface TimeData {
  timeInterval: string; // HH:MM
  avgSeats: number;
}

export interface GET_responseFormat {
  data: TimeData[];
}

export async function GET_requestAvailableSeats(
  route: Route,
  station: Station,
  timeBegin: string,
  timeEnd: string,
) {

  let requestUrl = `${API_URL}/route/${route.routeId}/seats?stationId=${station.station_id}`;

  const getAvailableSeatsReponse = await fetch(requestUrl, {
    method: 'GET',
  });

  // on error
  if (!getAvailableSeatsReponse.ok) {
    const errorData = await getAvailableSeatsReponse.json();
    alert(errorData);
    return null;
  }

  const body: GET_responseFormat = await getAvailableSeatsReponse.json();
  return body;
}