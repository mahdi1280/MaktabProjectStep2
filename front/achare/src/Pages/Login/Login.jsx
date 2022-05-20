import Header from "../../Components/Header";
import {Link,useHistory} from "react-router-dom";
import InputText from "../../Components/InputText";
import {useState} from "react";
import {post} from '../../http';
import {setUser} from '../../Auth';

export default function Login() {
    const [email,setEmail]=useState('');
    const [password,setPassword]=useState('');
    const [error, setError] = useState([]);
    const history = useHistory();
    const [loading,setLoading] = useState(false);
    function handlerLogin(){
        setLoading(true);
        post("/customer/login",{
            email:email,
            password:password
        })
            .then((response)=>{
                setLoading(false);
                setUser(response.data.token,response.data.id);
                localStorage.setItem("token",response.data.token);
                localStorage.setItem("id",response.data.id);

                history.replace("/");
            }).catch((response)=>{
                setError(response.response.data);
        })
    }

    const errors = error.map(response =>
        <li>{response.defaultMessage ? response.defaultMessage : response.message}</li>);

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
                        {errors.length !== 0 &&
                        <div className="alert alert-danger" role="alert">
                            {errors}
                        </div>
                        }
                            <InputText changeHandler={(e)=>setEmail(e.target.value)} id={"registerEmail"} type={"email"} value={"ایمیل:"}/>
                            <InputText changeHandler={(e)=>setPassword(e.target.value)} id={"registerPassword"} type={"password"} value={"گذر واژه:"}/>
                            <div className="row mb-4">

                                <div className="col-md-6 d-flex justify-content-center">
                                    <a>گذرواژه خود را فراموش کرده اید!</a>
                                </div>
                            </div>
                            <button onClick={handlerLogin} className="btn btn-primary btn-block mb-4">ورود</button>
                    </div>
                </div>
            </div>
        </>
    )
}