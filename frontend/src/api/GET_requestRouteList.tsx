import { API_URL } from "./API_URL";

export type Route = {
  routeId: string,
  routeName: string,
  stationStart: string,
  stationEnd: string,
  regionName: string,
}

export interface GET_paginationResponseFormat {
  pagination: {
    currentPage: number,
    totalPage: number,
  },
  routes: Route[],
}

export async function GET_requestRoutesPaginated(
  pageNumber: number,
  pageSize: number,
  routeSearchQuery: string,
) {

  // /api/route/registered?search={keyword}&page=0&size=10
  let requestUrl = `${API_URL}/route/registerd?search=${routeSearchQuery}&page=${pageNumber}&size=${pageSize}`;

  const getRoutesReponse = await fetch(requestUrl, {
    method: 'GET',
  });


  // on error
  if (!getRoutesReponse.ok) {
    const errorData = await getRoutesReponse.json();
    alert(errorData);
    return null;
  }

  const body: GET_paginationResponseFormat = await getRoutesReponse.json();
  return body;
}