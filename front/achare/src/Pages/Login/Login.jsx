import Header from "../../Components/Header";
import {Link} from "react-router-dom";
import InputText from "../../Components/InputText";
export default function Login() {
    return (<><Header/>
            <div className="col-sm-5 form">
                <ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                    <li className="nav-item" role="presentation">
                        <Link to={"/login"} className="nav-link active" id="tab-register" data-mdb-toggle="pill"  role="tab"
                              aria-controls="pills-register" aria-selected="true">ورود</Link>
                    </li>
                    <li className="nav-item" role="presentation">
                        <Link to={"/register"} className="nav-link " id="tab-login" data-mdb-toggle="pill" role="tab"
                              aria-controls="pills-login" aria-selected="false">به دنبال متخصص هستم</Link>
                    </li>
                    <li className="nav-item" role="presentation">
                        <Link to={"/registerExpert"} className="nav-link" id="tab-register" data-mdb-toggle="pill" role="tab"
                              aria-controls="pills-register" aria-selected="false">ساخت اکانت متخصص</Link>
                    </li>
                </ul>
                <div className="tab-content">
                    <div className="tab-pane fade show active" id="pills-login" role="tabpanel"
                         aria-labelledby="tab-login">
                        <form>


                            <InputText id={"registerEmail"} type={"email"} value={"ایمیل:"}/>
                            <InputText id={"registerPassword"} type={"password"} value={"گذر واژه:"}/>


                            <div className="row mb-4">

                                <div className="col-md-6 d-flex justify-content-center">
                                    <a>گذرواژه خود را فراموش کرده اید!</a>
                                </div>
                            </div>

                            <button type="submit" className="btn btn-primary btn-block mb-4">ورود</button>

                        </form>
                    </div>
                </div>
            </div>
        </>
    )
}