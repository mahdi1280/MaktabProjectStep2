import {useParams,useHistory} from "react-router-dom";
import Header from "../../Components/Header";
import {useState} from "react";
import './style.css';
import {getUser} from '../../Auth';
import {post} from '../../http';

export default function CommentSave(){
    const history = useHistory();
    const {offerId} = useParams();
    const [score,setScore] = useState(1);
    const [details,setDetails] = useState('');
    const [error,setError] = useState([]);

    function saveComment(){
            post("/comment",{
                score:score,
                offerId:offerId,
                details:details
            },{
                headers: {
                    "Authorization": getUser().token
                }
            }).then((response)=>response.data())
                .then(()=>{
                    alert("insert success");
                    history.replace("/");
                })
                .catch(response=>setError(response.response.data));
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
                <label htmlFor="exampleInputEmail1">توضیحات</label>
                <input onChange={(e)=>setDetails(e.target.value)} type="text" className="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="توضیحات"/>
            </div>
            <div className="form-group">
                <label htmlFor="customRange2">امتیاز</label>
                <div className="range">
                    <span>score: {score}</span>
                    <input type="range" min="1" value={score} onChange={(e)=>setScore(e.target.value)} max="5" className="form-range range" id="customRange1"/>
                </div>
            </div>
            <button onClick={saveComment}  className="btn btn-primary">ذخیره</button>
    </>
}