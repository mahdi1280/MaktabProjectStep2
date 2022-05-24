import Header from "../../Components/Header";
import {useState} from "react";
import {post} from '../../http';
import {getUser} from '../../Auth';
export default function Service(){
    const [title,setTitle] = useState('');
    const [error,setError] = useState([]);
    const [id,setId] = useState('');
    function saveService(){
        post("/service",{title:title},{
            headers:{
                "Authorization":getUser().token
            }
        })
            .then(response=>response.data)
            .then((response)=>{
                setId(response.id)
                setError([]);
                setTitle('');
            })
            .catch(response=>setError(response.response.data))
    }


    const errors = error.map(response =>
        <li>{response.defaultMessage ? response.defaultMessage : response.message}</li>);

    return <>
        <Header id={id}/>
        {errors.length !== 0 &&
        <div className="alert alert-danger" role="alert">
            {errors}
        </div>
        }
            <div className="form-row align-items-center">
                <div className="col-auto">
                    <label className="sr-only" htmlFor="inlineFormInput">عنوان</label>
                    <input value={title} onChange={(e)=>setTitle(e.target.value)} type="text" className="form-control mb-2" id="inlineFormInput" placeholder="سرویس یک"/>
                </div>
                <div className="col-auto">
                    <button onClick={saveService} type="submit" className="btn btn-primary mb-2">ذخیره</button>
                </div>
            </div>
    </>
}