import Header from "../../Components/Header";
import {get, post} from '../../http';
import {useEffect, useState} from "react";
import {getUser} from '../../Auth';
import {useHistory} from "react-router-dom";

export default function Order() {

    const history = useHistory();
    const [radio, setRadio] = useState(2);
    const [error, setError] = useState([]);
    const [address, setAddress] = useState('');
    const [basePrice, setBasePrice] = useState('');
    const [date, setDate] = useState('');

    const [box, setBox] = useState([]);
    useEffect(() => {
        get("/underService", {
            headers: {
                "Authorization": getUser().token
            }
        })
            .then(response => response.data)
            .then(response => setBox(response))
            .catch(console.log);
    }, [])

    function saveOrder() {
        post("/order", {
            proposedPrice: basePrice,
            address: address,
            workTime: date,
            underServiceId: radio,
        }, {
            headers: {
                "Authorization": getUser().token
            }
        }).then(response => response.data)
            .then(response => {
                alert("insert success");
                history.replace("/");
            })
            .catch(response => setError(response.response.data));
    }

    const errors = error.map(response =>
        <li>{response.defaultMessage ? response.defaultMessage : response.message}</li>);

    const boxes = box.map((response,index) => <td key={index}>{response.title}<input
        onChange={(e) => setRadio(e.target.value)} name="radio" type="radio" value={response.id}/><span>قیمت پایه {response.basePrice}</span></td>)
    return (
        <>
            <Header/>
            {errors.length !== 0 &&
            <div className="alert alert-danger" role="alert">
                {errors}
            </div>
            }
            <div className="form-group">
                <label htmlFor="exampleInputEmail1">قیمت پیشنهادی: </label>
                <input onChange={e => setBasePrice(e.target.value)} type="number" className="form-control"
                       id="exampleInputEmail1" aria-describedby="emailHelp"
                       placeholder="100"/>
            </div>
            <div className="form-group">
                <label htmlFor="exampleInputPassword1">آدرس</label>
                <input onChange={e => setAddress(e.target.value)} type="text" className="form-control"
                       id="exampleInputPassword1" placeholder="آدرس"/>
            </div>
            <div className="form-group">
                <label htmlFor="exampleInputPassword1">زمان انجام</label>
                <input onChange={e => setDate(e.target.value)} type="datetime-local" className="form-control"
                       id="exampleInputPassword1" placeholder="آدرس"/>
            </div>
            <table className="table table-hover">
                <tr>
                    {boxes}
                </tr>
            </table>

            <button onClick={saveOrder} className="btn btn-primary">ذخیره</button>
        </>
    )
}