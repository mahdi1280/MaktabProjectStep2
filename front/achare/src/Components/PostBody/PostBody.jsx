import {useParams} from "react-router-dom";
import {get} from '../../http';
import {useEffect, useState} from "react";
import Product from "../Product";
export default function PostBody(){
    const param=useParams();
    const [post,setPost] = useState([]);
    useEffect(()=>{
        get("/order/"+param.underServiceId)
            .then((response)=>{
                setPost(response.data.content)
            }).catch((response)=>{
                alert(response.response.data[0].message);
        });
        },[param])

    const products=post.map((response)=> <Product key={response.id} workTime={response.workTime} proposedPrice={response.proposedPrice} id={response.id} address={response.address} />);

    return <> <div className="container bootstrap snipets">
            <h1 className="text-center text-muted">محصولات</h1>
            <div className="row flow-offset-1">
                {products.length!==0 ? products :  <p>محصولی وجود ندارد</p>}
            </div>
        </div>
    </>;
}