import Header from "../../Components/Header";
import {useEffect, useState} from "react";
import {get} from '../../http';
import Product from '../../Components/Product';
import {getUser} from '../../Auth';

export default function MyOrder(){
    const [post,setPost] = useState([]);
    useEffect(()=>{
        get("/order/myOrder",{
            headers: {
                "Authorization": getUser().token
            }
        })
            .then((response)=>{
                setPost(response.data)
            }).catch(console.log);
    },[])

    const products=post.map((response)=> <Product key={response.id} workTime={response.workTime} proposedPrice={response.proposedPrice} id={response.id} address={response.address} />);

    return <>
        <Header/>
        <div className="container bootstrap snipets">
        <h1 className="text-center text-muted">محصولات</h1>
        <div className="row flow-offset-1">
            {products.length!==0 ? products :  <p>محصولی وجود ندارد</p>}
        </div>
    </div>
    </>;
}