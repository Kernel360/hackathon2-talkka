import {createBrowserRouter, RouterProvider} from "react-router-dom";
import {ErrorPage} from "./ErrorPage";
import { RouteStatisticsPage } from "./pages/RouteStatisticsPage";

const router = createBrowserRouter([
  /**
   * 메인 화면
   */
  {
    path: "/",
    element: (
      <RouteStatisticsPage/>
    ),
    errorElement: <ErrorPage/>,
  },
]);

function App() {
  return (
      <div className="App">
        <header className="App-header">
          <RouterProvider router={router}/>
        </header>
      </div>
  );
}

export default App;