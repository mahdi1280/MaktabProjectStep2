import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap";
import Home from "./Pages/Home";
import {Route,Switch} from "react-router-dom";
import Post from "./Pages/Post";
import './style.css';
import Login from "./Pages/Login";
import RegisterCustomer from "./Pages/RegisterCustomer";
import RegisterExpert from "./Pages/RegisterExpert";
import {setUser} from "./Auth";

export default function App() {
  setUser(localStorage.getItem("token"),localStorage.getItem("id"));
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
          <RegisterCustomer/>
        </Route>
        <Route path={"/registerExpert"}>
          <RegisterExpert/>
        </Route>
      </Switch>

    </div>
  );
}

