import Header from "../../Components/Header";
import {get, post} from '../../http';
import {useEffect, useState} from "react";
import {getUser} from '../../Auth';

export default function UnderService(){
    const [error,setError] = useState([])
    const [id,setId] = useState('');
    const [service, setService] = useState([]);
    const [basePrice, setBasePrice] = useState('');
    const [title, setTitle] = useState('');
    const [serviceId,setServiceId] = useState('');

    useEffect(()=>{
        get("/service")
            .then(response=>response.data)
            .then((response)=>{
                setService(response);
            })
            .catch(response=>setError(response.response.data))
    },[]);


    function saveUnderService() {
        post("/underService", {
                serviceId:serviceId,
            title:title,
            basePrice:basePrice,
            },
            {
                headers: {
                    "Authorization": getUser().token
                }
            }).then(response=>response.data)
            .catch(response=>setError(response.response.data));
    }

    const errors = error.map(response =>
    {
        if(serviceId!=='')
            setServiceId(response.id);
        return <li key={response.id}>{response.defaultMessage ? response.defaultMessage : response.message}</li>
    });

    const services = service.map((response)=> <option key={response.id} value={response.id}>{response.title}</option>)
    return <>
        <Header id={id}/>
        <form>
            {errors.length !== 0 &&
            <div className="alert alert-danger" role="alert">
                {errors}
            </div>
            }
            <div className="form-row align-items-center">
                <div className="col-auto">
                    <label className="sr-only" htmlFor="inlineFormInput">زیر سرویس</label>
                    <input value={title} onChange={(e) => setTitle(e.target.value)} type="text"
                           className="form-control mb-2" id="inlineFormInput" placeholder="زیر سرویس"/>
                </div>
                <div className="col-auto">
                    <label className="sr-only" htmlFor="inlineFormInput">قیمت پایه</label>
                    <input value={basePrice} onChange={(e) => setBasePrice(e.target.value)} type="number"
                           className="form-control mb-2" id="inlineFormInput" placeholder="قیمت پایه"/>
                </div>
                <div className="col-auto">
                    <label className="sr-only" htmlFor="inlineFormInputGroup">سرویس</label>
                    <select onChange={(e) => setServiceId(e.target.value)} className="form-select  mb-3"
                            aria-label=".form-select-lg example">
                        {services}
                    </select>
                </div>

                <div className="col-auto">
                    <button onClick={saveUnderService} type="submit" className="btn btn-primary mb-2">ذخیره</button>
                </div>
            </div>
        </form>
    </>
}