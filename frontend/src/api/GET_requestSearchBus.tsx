import { AArrowDown } from "lucide-react";
import { API_URL } from "./API_URL";

export interface GET_routeRouteSearch {
  data: Array<RouteBusXXX>,
}

export interface RouteBusXXX {
    route_id: number;
    route_name: string;
    region_name: string;
}

interface ResponseData {
    data: RouteBusXXX[];
}

export async function GET_requestSearchBus(keyword: string) {

  let requestUrl = `${API_URL}/route/search/${keyword}`;


  const addRouteResponse = await fetch(requestUrl, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  });

  const a = await addRouteResponse.json();
  // on error
  // if (!addRouteResponse.ok) {
    // const errorData = await addRouteResponse.json();
    // alert(errorData);
  // }

  return a;
  // no response body
}