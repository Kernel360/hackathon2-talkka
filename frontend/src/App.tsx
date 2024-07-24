import {createBrowserRouter, RouterProvider} from "react-router-dom";
import {ErrorPage} from "./ErrorPage";

const router = createBrowserRouter([
  /**
   * 메인 화면
   */
  {
    path: "/",
    element: (
        <div>
          Home
        </div>
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