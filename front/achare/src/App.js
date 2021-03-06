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
import UserList from "./Pages/UserList";
import Service from "./Pages/Service";
import UnderService from "./Pages/UnderService";
import UnderServiceExpert from "./Pages/UnderServiceExpert";
import UnderServiceExpertAdmin from "./Pages/UnderServiceExpertAdmin";
import Order from "./Pages/Order";
import Offer from "./Pages/Offer";
import MyOrder from "./Pages/MyOrder";
import MyOffer from "./Pages/MyOffer";
import CommentSave from "./Pages/CommentSave/CommentSave";
import ExpertOffer from "./Pages/ExpertOffer";
import PayPal from "./Pages/PaypalPage";


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
        <Route path={"/user-list"}>
          <UserList/>
        </Route>
        <Route path={"/service"}>
          <Service/>
        </Route>
        <Route path={"/under-service"}>
          <UnderService/>
        </Route>
        <Route path={"/under-service-expert"}>
          <UnderServiceExpert/>
        </Route>
        <Route path={"/under-service-expert-admin"}>
          <UnderServiceExpertAdmin/>
        </Route>
        <Route path={"/save-order"}>
          <Order/>
        </Route>
        <Route path={"/offer/:postId"}>
          <Offer/>
        </Route>
        <Route path={"/myOrder"}>
          <MyOrder/>
        </Route>
        <Route path={"/myOffer/:postId"}>
          <MyOffer/>
        </Route>
        <Route path={"/save-comment/:offerId"}>
          <CommentSave/>
        </Route>
        <Route path={"/expert-offer"}>
          <ExpertOffer/>
        </Route>
        <Route path={"/paypal-page/:id/:price"}>
          <PayPal/>
        </Route>
      </Switch>
    </div>
  );
}

