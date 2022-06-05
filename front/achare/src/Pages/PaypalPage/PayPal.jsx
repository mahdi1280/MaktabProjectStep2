import Header from "../../Components/Header";
import {useParams} from 'react-router-dom';
import {get, post} from '../../http';
import {useEffect, useState} from "react";

export default function PayPal() {
    const {id,price} = useParams();
    const [captchaImage, setCaptchaImage] = useState();
    const [captchaId, setCaptchaId] = useState();
    const [captchaCode, setCaptchaCode] = useState('');
    const [error, setError] = useState([]);

    function getCaptcha() {
        get("http://localhost:8081/captcha")
            .then(response => {
                setCaptchaId(response.data.id);
                setCaptchaImage(response.data.captchaImage);
            }).catch(console.log);
    }

    function paymentGateway() {
        post()
            .then(response=>{

            })
            .catch((response) => {
                setError(response.response.response.data);
            })
    }


    useEffect(() => {
        getCaptcha();
    }, [])

    const errors = error.map(response =>
        <li>{response.defaultMessage ? response.defaultMessage : response.message}</li>);

    return <>
        <Header/>
        <div className={"form form-control form-select-lg"}>
            {errors.length !== 0 &&
            <div className="alert alert-danger" role="alert">
                {errors}
            </div>
            }

            <p> <span>{price}</span> قیمت قابل پرداخت </p>
            <br/>
            {captchaImage && <img src={captchaImage} alt={"captcha"}/>}
            <button className={"btn btn-outline-info"} onClick={() => getCaptcha()}>refresh</button>
            <br/>
            <input value={captchaCode} onChange={(e) => setCaptchaCode(e.target.value)} type="text"
                   className={"text-input"}/>
            <br/>
            <button type={"button"} className={"btn btn-info"}>تکمیل پرداخت</button>
            <br/>
            <button type={"button"} className={"btn btn-info"}>پرداخت از طریق اعتبار</button>
        </div>
    </>
}