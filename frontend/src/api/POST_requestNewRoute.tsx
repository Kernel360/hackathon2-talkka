import { API_URL } from "./API_URL";

export interface POST_routeAddRequestFormat {
  route_id: number,
}

export async function POST_requestNewRoute(routeId: number, stationName: string) {

  let requestUrl = `${API_URL}/collect`;

  const requestPayload: POST_routeAddRequestFormat = {
    route_id: routeId,
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