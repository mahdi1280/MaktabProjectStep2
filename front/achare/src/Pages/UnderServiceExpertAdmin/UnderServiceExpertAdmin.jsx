import Header from "../../Components/Header";
import {get} from '../../http';
import {getUser} from '../../Auth';
import {useEffect, useState} from "react";

export default function UnderServiceExpertAdmin(){

    const [userId,setUserId]=useState(getUser().id);
    const [underServiceExpert,setUnderServiceExpert] = useState([]);
    const [data, setData] = useState([]);
    const [user,setUser] = useState([]);

    useEffect(() => {
        get(`/underService/expert/${userId}`, {
            headers: {
                "Authorization": getUser().token
            }
        }).then(response => response.data)
            .then(response => setUnderServiceExpert(response))
            .catch(console.log);
    }, [userId])


    useEffect(() => {
        get(`/user/expert`, {
            headers: {
                "Authorization": getUser().token
            }
        }).then((response) => response.data)
            .then(response => {
                console.log(response)
                setUser(response)
            }).catch(response => console.log(response));
    }, [])

    useEffect(() => {
        get(`/underService`, {
            headers: {
                "Authorization": getUser().token
            }
        }).then((response) => response.data)
            .then(response => {
                setData(response)
            }).catch(response => console.log(response));
    }, [])

    function checkedBox(){

    }
    function saveUnderService(){

    }

    const checkBoxes = data.map(response => <td key={response.id}>{response.title}<input onClick={checkedBox} className="form-check-input" type="checkbox"
                                                                                         value={response.id}/></td>);
    const expertData = underServiceExpert.map(response => <li key={response.id}>{response.title}</li>)
    const users= user.map((response)=><option>{response.email}</option>)
    return <>
        <Header/>
        <div>
            <ul>
                {expertData}
            </ul>
        </div>
        <div>
            <select>
                {users}
            </select>
        </div>
        <table className={"table"}>
            <tr>
                {checkBoxes}
            </tr>
        </table>
        <button onClick={saveUnderService} className="btn btn-primary">ذخیره</button>
    </>
}