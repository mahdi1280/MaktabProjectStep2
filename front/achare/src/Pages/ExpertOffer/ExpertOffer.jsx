import Header from "../../Components/Header";
import {get} from '../../http';
import {getUser} from '../../Auth';
import {useEffect, useState} from "react";

export default function ExpertOffer(){
    const [data,setData] = useState([]);
    useEffect(()=>{
        get("/offer/user",{
            headers: {
                "Authorization": getUser().token
            }
        }).then(response=>response.data)
            .then(response=>setData(response))
            .catch(console.log);
    },[])

    const datas = data.map((response)=><tr>
        <td>{response.id}</td>
        <td>{response.periodOfTime}</td>
        <td>{response.proposedPrice}</td>
        <td>{response.createdAt}</td>
        <td>{response.startTime}</td>
        <td>{response.selected && "بله"}</td>
    </tr>);

    return <>
        <Header/>
        <table className="table">
            <tr>
                <th>آیدی</th>
                <th>زمان پیشنهادی</th>
                <th>قیمت</th>
                <th>زمان ایجاد</th>
                <th>زمان شروع</th>
                <th>انتخاب شده</th>

            </tr>
            {datas}
        </table>
    </>
}