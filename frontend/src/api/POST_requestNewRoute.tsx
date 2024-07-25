import { API_URL } from "./API_URL";

export interface POST_routeAddRequestFormat {
  route_name: string,
  station_name: string
}

export async function POST_requestNewRoute(routeName: string, stationName: string) {

  let requestUrl = `${API_URL}/route`;

  const requestPayload: POST_routeAddRequestFormat = {
    route_name: routeName,
    station_name: stationName,
  };

  const addRouteResponse = await fetch(requestUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(requestPayload),
  });


  // on error
  if (!addRouteResponse.ok) {
    const errorData = await addRouteResponse.json();
    alert(errorData);
  }

  // no response body
}