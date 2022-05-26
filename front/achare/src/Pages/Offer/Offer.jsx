import Header from "../../Components/Header";
import {useParams} from "react-router-dom";
import {useState} from "react";
import {post} from '../../http';
import {getUser} from '../../Auth';
import {useHistory} from "react-router-dom";
export default function Offer() {
    const {postId} = useParams();
    const [basePrice, setBasePrice] = useState('');
    const [endTime, setEndTime] = useState('');
    const [startTime, setStartTime] = useState('');
    const [error, setError] = useState([]);
    const history = useHistory();


    function saveOffer() {
        post("/offer", {
                periodOfTime: endTime,
                proposedPrice: basePrice,
                startTime: startTime,
                orderId: postId
            }
            ,
            {
                headers: {
                    "Authorization": getUser().token
                }
            }
        ).then(response=>response.data)
            .then(()=>{
                alert("insert success");
                history.replace("/");
            }).catch(response=>{
                console.log(response);
                setError(response.response.data)

        });
    }

    const errors = error.map(response =>
        <li>{response.defaultMessage ? response.defaultMessage : response.message}</li>);


    return <>
        <Header/>
        {errors.length !== 0 &&
        <div className="alert alert-danger" role="alert">
            {errors}
        </div>
        }
        <div className="form-group">
            <label htmlFor="exampleInputEmail1">قیمت پیشنهادی</label>
            <input onChange={e => setBasePrice(e.target.value)} type="number" className="form-control"
                   id="exampleInputEmail1" aria-describedby="emailHelp"
                   placeholder="100"/>
        </div>
        <div className="form-group">
            <label htmlFor="exampleInputPassword1">زمان شروع</label>
            <input onChange={e => setStartTime(e.target.value)} type="datetime-local" className="form-control"
                   id="exampleInputPassword1" placeholder="Password"/>
        </div>
        <div className="form-group">
            <label htmlFor="exampleInputPassword1">زمان پایان</label>
            <input onChange={e => setEndTime(e.target.value)} type="datetime-local" className="form-control"
                   id="exampleInputPassword1" placeholder="Password"/>
        </div>
        <button onClick={saveOffer} className="btn btn-primary">ذخیره</button>
    </>
}