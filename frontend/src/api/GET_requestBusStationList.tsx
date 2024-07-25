import { API_URL } from "./API_URL";
import { Route } from "./GET_requestRouteList";

export interface Station {
  station_id: number;
  station_name: string;
  station_seq: number;
  turn_yn: string;
  center_yn: string;
  longitude: number;
  latitude: number;
}

export interface GET_paginationResponseFormat {
  pagination: {
    currentPage: number,
    totalPage: number,
  },
  data: Array<Station>
}

export type StationListType = Array<{
  name: string,
}>

export async function GET_requestStationsPaginated(
  pageNumber: number,
  pageSize: number,
  route: Route,
) {

  let requestUrl = `${API_URL}/route/${route.routeId}?size=${pageSize}&page=${pageNumber}`;

  const getStationsReponse = await fetch(requestUrl, {
    method: 'GET',
  });


  // on error
  if (!getStationsReponse.ok) {
    const errorData = await getStationsReponse.json();
    alert(errorData);
    return null;
  }

  const body: GET_paginationResponseFormat = await getStationsReponse.json();
  return body;
}