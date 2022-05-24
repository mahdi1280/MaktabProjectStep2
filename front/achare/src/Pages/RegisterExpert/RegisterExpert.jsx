import Header from "../../Components/Header";
import {Link, useHistory} from "react-router-dom";
import InputText from '../../Components/InputText';
import {useState} from "react";
import {post} from '../../http';
import {setUser} from '../../Auth';

export default function RegisterExpert() {
    const [firstname,setFirstname] = useState();
    const [lastname,setLastname] = useState();
    const [email,setEmail] = useState();
    const [password,setPassword] = useState();
    const [rePassword,setRePassword] = useState();
    const [image,setImage] = useState();
    const [error,setError] = useState([]);
    const [verifyForm,setVerifyForm] = useState('');
    const [loading,setLoading] =useState(false);
    const [verifyCode,setVerifyCode] = useState();
    const history = useHistory();

    function saveExpert(){
        setLoading(true);
        setError([])
        post("/expert",{
            firstname:firstname,
            lastname:lastname,
            email:email,
            password:password,
            rePassword:rePassword,
            multipartFile:image
        },{headers:{"Content-Type":"multipart/form-data"}})
            .then((response)=>response.data)
            .then((response)=>{
                setVerifyForm(response);
                setLoading(false);
            }).catch((response)=>{ setLoading(false);
                setError(response.response.data);});
    }

    function verifyUser() {
        setLoading(true);
        setError([])
        post("/customer/verify", {
            "id": verifyForm.id,
            "email": verifyForm.email,
            "verifyCode": verifyCode
        }).then((response) => response.data)
            .then((response)=>{
                setUser(response.token,response.id)
                setLoading(false);
                localStorage.setItem("token",response.token);
                localStorage.setItem("id",response.id);
                history.replace("/")
            }).catch(response => {
            setError(response.response.data);
            setLoading(false);
        })
    }


    const errors = error.map(response =>
        <li>{response.defaultMessage ? response.defaultMessage : response.message}</li>);


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
            {errors.length !== 0 &&
            <div className="alert alert-danger" role="alert">
                {errors}
            </div>
            }
            {loading ? <img className="loading" src="https://upload.wikimedia.org/wikipedia/commons/c/c7/Loading_2.gif?20170503175831" alt="loading"/>:
                <>
                <div className="tab-content">
                    <div className="tab-pane fade show active" id="pills-login" role="tabpanel"
                         aria-labelledby="tab-login">
                        {verifyForm ==='' ? <>
                                <InputText changeHandler={(e) => setFirstname(e.target.value)} id={"registerName"}
                                           type={"text"}
                                           value={"نام:"}/>
                                <InputText changeHandler={(e) => setLastname(e.target.value)} id={"registerName"}
                                           type={"text"} value={"نام خانوادگی:"}/>
                                <InputText changeHandler={(e) => setEmail(e.target.value)} id={"registerEmail"}
                                           type={"email"} value={"ایمیل:"}/>
                                <InputText changeHandler={(e) => setPassword(e.target.value)} id={"registerPassword"}
                                           type={"password"} value={"گذر واژه:"}/>
                                <InputText changeHandler={(e) => setRePassword(e.target.value)} id={"registerPassword"}
                                           type={"password"} value={"تکرار گذر واژه:"}/>
                                <div className="form-outline mb-4">
                                    <label className="form-label" htmlFor="registerRepeatPassword">عکس پرسنلی</label>
                                    <input onChange={(e) => setImage(e.target.files[0])} type="file"
                                           id="registerRepeatPassword" className="form-control"/>
                                </div>
                                <button onClick={saveExpert} className="btn btn-primary btn-block mb-3">ساخت حساب</button>
                            </>
                            :
                            <>
                                <span>ایمیل: {verifyForm.email} </span>
                                <InputText changeHandler={(e) => setVerifyCode(e.target.value)} id={"registerCode"}
                                           type={"text"} value={"کد تایید:"}/>
                                <p>زمان باقی مانده: 3 دقیقه</p>
                                <button onClick={verifyUser} className="btn btn-primary btn-block mb-3">تایید
                                </button>
                            </>
                        }
                    </div>
                </div>
                </>
            }
        </div>
    </>
}