import Header from "../../Components/Header";
import {Link} from "react-router-dom";
import InputText from '../../Components/InputText';

export default function RegisterExpert() {
    return <>
        <Header/>
        <div className="col-sm-5 form">
            <ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                <li className="nav-item" role="presentation">
                    <Link to={"/login"} className="nav-link" id="tab-register" data-mdb-toggle="pill" role="tab"
                          aria-controls="pills-register" aria-selected="false">ورود</Link>
                </li>
                <li className="nav-item" role="presentation">
                    <Link to={"/register"} className="nav-link " id="tab-login" data-mdb-toggle="pill" role="tab"
                          aria-controls="pills-login" aria-selected="true">به دنبال متخصص هستم</Link>
                </li>
                <li className="nav-item" role="presentation">
                    <Link to={"/registerExpert"} className="nav-link active" id="tab-register" data-mdb-toggle="pill" role="tab"
                          aria-controls="pills-register" aria-selected="false">ساخت اکانت متخصص</Link>
                </li>
            </ul>
            <div className="tab-content">
                <div className="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                    <form>
                        <InputText id={"registerName"} type={"text"} value={"نام:"}/>
                        <InputText id={"registerName"} type={"text"} value={"نام خانوادگی:"}/>
                        <InputText id={"registerEmail"} type={"email"} value={"ایمیل:"}/>
                        <InputText id={"registerPassword"} type={"password"} value={"گذر واژه:"}/>
                        <InputText id={"registerPassword"} type={"password"} value={"تکرار گذر واژه:"}/>
                        <div className="form-outline mb-4">
                            <label className="form-label" htmlFor="registerRepeatPassword">عکس پرسنلی</label>
                            <input type="file" id="registerRepeatPassword" className="form-control"/>
                        </div>
                        <button type="submit" className="btn btn-primary btn-block mb-3">ساخت حساب</button>
                    </form>
                </div>
            </div>
        </div>
    </>
}