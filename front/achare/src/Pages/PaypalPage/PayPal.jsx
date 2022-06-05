import Header from "../../Components/Header";
import {useHistory, useParams} from 'react-router-dom';
import {get, post} from '../../http';
import {useEffect, useState} from "react";
import {getUser} from '../../Auth';

export default function PayPal() {

    const history = useHistory();
    const {id, price} = useParams();
    const [captchaImage, setCaptchaImage] = useState();
    const [captchaId, setCaptchaId] = useState();
    const [captchaCode, setCaptchaCode] = useState('');
    const [error, setError] = useState([]);
    const [cridet,setCredit] = useState();
    function  getMe(){
        get("/user/me",{
            headers: {
                "Authorization": getUser().token
            }
        })
            .then(response => {
                setCredit(response.data.credit);
            }).catch(console.log);
    }

    function getCaptcha() {
        get("http://localhost:8081/captcha")
            .then(response => {
                setCaptchaId(response.data.id);
                setCaptchaImage(response.data.captchaImage);
            }).catch(console.log);
    }

    function payByCridit(){
        post("/offer/pay", {
            captchaId: captchaId,
            captchaParam: captchaCode,
            offerId: id,
            redirect: "http://localhost:3000"
        }, {
            headers: {
                "Authorization": getUser().token
            }
        })
            .then(() => {
                alert("pay success full");
                window.location.replace("/")
            })
            .catch((response) => {
                setError(response.response.data);
            })
    }


    function paymentGateway() {
        post("/offer/pay/buy", {
            captchaId: captchaId,
            captchaParam: captchaCode,
            offerId: id,
            redirect: "http://localhost:3000"
        }, {
            headers: {
                "Authorization": getUser().token
            }
        })
            .then((response) => {
                alert("pay success full");
                window.location.replace(response.data.link)
            })
            .catch((response) => {
                setError(response.response.data);
            })
    }

    useEffect(() => {
        getCaptcha();
        getMe();
    }, [])

    const errors = error.map((response,index) =>
        <li key={index}>{response.defaultMessage ? response.defaultMessage : response.message}</li>);

    return <>
        <Header/>
        <div className={"form form-control form-select-lg"}>
            {errors.length !== 0 &&
            <div className="alert alert-danger" role="alert">
                {errors}
            </div>
            }
            <p> اعتبار شما<span>{cridet}</span></p>
            <p> <span>{price}</span> قیمت قابل پرداخت </p>
            <br/>
            {captchaImage && <img src={captchaImage} alt={"captcha"}/>}
            <button className={"btn btn-outline-info"} onClick={() => getCaptcha()}>refresh</button>
            <br/>
            <input value={captchaCode} onChange={(e) => setCaptchaCode(e.target.value)} type="text"
                   className={"text-input"}/>
            <br/>
            <button onClick={paymentGateway} type={"button"} className={"btn btn-info"}>تکمیل پرداخت</button>
            <br/>
            <button onClick={payByCridit} type={"button"} className={"btn btn-info"}>پرداخت از طریق اعتبار</button>
        </div>
    </>
}