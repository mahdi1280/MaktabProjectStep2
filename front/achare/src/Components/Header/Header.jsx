import {get} from '../../http/http';
import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
export default function Header(){
    const [service,setService]=useState([])

    useEffect(()=>{
        get("/service")
            .then((response)=>response.data)
            .then((response=>setService(response)));
    },[])


    const menu= service.map(response=>{
        if(response.underServiceResponse.length!==0){
            return <li key={response.id} className="nav-item dropdown">
                <span className="nav-link dropdown-toggle" id="navbarDropdownMenuLink" role="button"
                   data-bs-toggle="dropdown" aria-expanded="false">
                    {response.title}
                </span>
                <ul className="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                    {response.underServiceResponse.map((underService)=>
                       <li><Link to={"/post/"+underService.id} className="dropdown-item" href="#">{underService.title}</Link></li>
                    )}
                </ul>
            </li>
        }else {
            return <li key={response.id} className="nav-item">
                <span className="nav-link">{response.title}</span>
            </li>
        }
    });

    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
                <a className="navbar-brand" href="#">{process.env.REACT_APP_NAME}</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                        aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul className="navbar-nav">
                        {menu}
                    </ul>
                </div>
                <form className="d-flex">
                    <input className="form-control me-2" type="search" placeholder="جست و جو" aria-label="Search"/>
                    <button className="btn btn-outline-success" type="submit">search</button>
                    <button className="btn btn-sm btn-info">ورود ثبت نام</button>
                </form>
            </div>
        </nav>
    );
}