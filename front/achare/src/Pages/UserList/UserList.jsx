import Header from "../../Components/Header";
import {useEffect, useState} from "react";
import {get} from '../../http';
import {getUser} from '../../Auth';
import UserTable from "../../Components/UserTable";

export default function UserList(){
    const [userData,setUserData] = useState([]);
    const [firstname,setFirstname] = useState('');
    const [lastname,setLastname] = useState('');
    const [email,setEmail] = useState('');

    useEffect(()=>{
        let url=`/user?`
        if(firstname!=='')
            url +=`firstname=${firstname}&`;
        if(lastname!=='')
            url +=`lastname=${lastname}&`;
        if(email!=='')
            url +=`email=${email}&`;
        get(url,{
            headers:{
                Authorization: getUser().token
            }
        }).then(response=>response.data)
            .then(response=>{
                setUserData(response.content);
            }).catch(response=>console.log(response));
    },[firstname,lastname,email])


    const rows= userData.map((response)=>
        <tr key={response.id}>
            <th scope="row">{response.id}</th>
            <td>{response.firstname}</td>
            <td>{response.lastname}</td>
            <td>{response.email}</td>
            <td>{response.role}</td>
            <td>{response.score}</td>
            <td>{response.status}</td>
        </tr>
    );

    return <><Header/>
        <div className="row">
            <div className="col">
                <input value={firstname} onChange={(e)=>setFirstname(e.target.value)} type="text" className="form-control" placeholder="نام"/>
            </div>
            <div className="col">
                <input value={lastname} onChange={(e)=>setLastname(e.target.value)} type="text" className="form-control" placeholder="نام خانوادگی"/>
            </div>
            <div className="col">
                <input value={email} onChange={(e)=>setEmail(e.target.value)} type="text" className="form-control" placeholder="ایمیل"/>
            </div>


        </div>
        <UserTable>
            {rows}
        </UserTable>
        </>
}