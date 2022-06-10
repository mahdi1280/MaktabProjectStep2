import Header from "../../Components/Header";
import {useEffect, useState} from "react";
import {get, put} from '../../http';
import {getUser} from '../../Auth';
import UserTable from "../../Components/UserTable";
import {Link} from "react-router-dom";

export default function UserList() {
    const [userData, setUserData] = useState([]);
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');
    const [email, setEmail] = useState('');

    function getUsers() {
        let url = `/user?`
        if (firstname !== '')
            url += `firstname=${firstname}&`;
        if (lastname !== '')
            url += `lastname=${lastname}&`;
        if (email !== '')
            url += `email=${email}&`;
        get(url, {
            headers: {
                Authorization: getUser().token
            }
        }).then(response => response.data)
            .then(response => {
                setUserData(response.content);
            }).catch(response => console.log(response));
    }

    useEffect(() => {
        getUsers();
    }, [firstname, lastname, email])


    function acceptUser(userId) {
        put(`/user/accept/${userId}`, {}, {
            headers: {
                Authorization: getUser().token
            }
        }).then(() => getUsers())
            .catch(console.log);
    }

    const rows = userData.map((response) =>
        <tr key={response.id}>
            <th scope="row">{response.id}</th>
            <td>{response.firstname}</td>
            <td>{response.lastname}</td>
            <td>{response.email}</td>
            <td>{response.role}</td>
            <td>{response.score}</td>
            <td>{response.status}</td>
            <td>{response.credit}</td>
            <td><Link className={"btn"} to={"/expert-offer"}>مشاهده پیشنهادات</Link><Link to={"/myOrder"}
                                                                                          className={"btn"}>مشاهده
                سفارشات</Link>
                {response.role === 'EXPERT' &&
                <button onClick={(e) => acceptUser(response.id)} className={"btn btn-success"}>تایید کاربر</button>}
            </td>
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