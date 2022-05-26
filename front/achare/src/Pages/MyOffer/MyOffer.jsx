import Header from "../../Components/Header";
import {useParams,useHistory} from "react-router-dom";
import {useEffect, useState} from "react";
import {get,put} from '../../http';
import {getUser} from '../../Auth';

export default function MyOffer(){
    const {postId} = useParams();
    const [data,setData] = useState([]);
    const history = useHistory();
    const [error,setError] = useState([]);

    useEffect(()=>{
        get(`/offer/order/${postId}`,{
            headers: {
                "Authorization": getUser().token
            }
        })
            .then(response=>response.data)
            .then(response=>{
                setData(response.content);
            }).catch(console.log);
    },[])

    function choseOffer(id){
       put(`/offer/${id}/order/${postId}`,{},{
           headers: {
               "Authorization": getUser().token
           }
       }).then(()=>{
           alert("success");
           history.replace("/");
       }).catch(response=>setError(response.response.data));
    }

    const rows= data.map((response)=><tr key={response.id}>
        <td>{response.id}</td>
        <td>{response.periodOfTime}</td>
        <td>{response.proposedPrice}</td>
        <td>{response.createdAt}</td>
        <td>{response.startTime}</td>
        <td>{response.userId}</td>
        <td><button onClick={(e)=>choseOffer(response.id)} className="button btn-info">انتخاب</button></td>
    </tr>)

    const errors = error.map(response =>
        <li>{response.defaultMessage ? response.defaultMessage : response.message}</li>);

    return<>
        <Header/>
        {errors.length !== 0 &&
        <div className="alert alert-danger" role="alert">
            {errors}
        </div>
        }
        <table className="table">
            <tr>
                <th>آیدی</th>
                <th>تاریخ پایان</th>
                <th>قیمت</th>
                <th>زمان ایجاد</th>
                <th>تاریخ شروع</th>
                <th>کاربر</th>
                <th>...</th>
            </tr>
            {rows}
        </table>
    </>
}