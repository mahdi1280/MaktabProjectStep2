import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap";
import Home from "./Pages/Home";
import {Route,Switch} from "react-router-dom";
import Post from "./Pages/Post";
import './style.css';
import Login from "./Pages/Login";
import Register from "./Pages/Register";
import RegisterExpert from "./Pages/RegisterExpert";

export default function App() {
  return (
    <div className="App">
      <Switch>
        <Route exact path={"/"}>
          <Home/>
        </Route>
        <Route path={"/post/:underServiceId"}>
          <Post/>
        </Route>
        <Route path={"/login"}>
          <Login/>
        </Route>
        <Route path={"/register"}>
          <Register/>
        </Route>
        <Route path={"/registerExpert"}>
          <RegisterExpert/>
        </Route>
      </Switch>

    </div>
  );
}

