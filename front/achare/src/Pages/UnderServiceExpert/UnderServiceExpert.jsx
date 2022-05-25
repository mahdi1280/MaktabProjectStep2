import Header from "../../Components/Header";
import {useEffect, useState} from "react";
import {get,put} from '../../http';
import {getUser} from '../../Auth';
import {useHistory} from "react-router-dom";

export default function UnderServiceExpert() {
    const [data, setData] = useState([]);
    const [underServiceExpert, setUnderServiceExpert] = useState([]);
    const history=useHistory();
    const [checked,setChecked] = useState([]);

    function saveUnderService(){
        put("/expert/setUnderService",{ids:checked,userId:getUser().id},{
            headers: {
                "Authorization": getUser().token
            }
        }).then(response=>response.data)
            .then(response=>history.replace("/"));
    }

    function checkedBox(e){
        if(!e.target.checked)
            setChecked(checked.pop(e.target.value))
        else
            setChecked([
                ...checked,e.target.value
            ])
    }

    useEffect(() => {
        get("/underService", {
            headers: {
                "Authorization": getUser().token
            }
        }).then((response) => response.data)
            .then(response => {
                setData(response)
            }).catch(response => console.log(response));
        get("/underService/expert", {
            headers: {
                "Authorization": getUser().token
            }
        }).then(response => response.data)
            .then(response => setUnderServiceExpert(response))
            .catch(console.log);
    }, [])
    const checkBoxes = data.map(response => <td key={response.id}>{response.title}<input onClick={checkedBox} className="form-check-input" type="checkbox"
                                                                       value={response.id}/></td>);
    const expertData = underServiceExpert.map(response => <li key={response.id}>{response.title}</li>)
    return <>
        <Header/>
        <div>
            <ul>
                {expertData}
            </ul>
        </div>
        <table>
            <tr>
                {checkBoxes}
            </tr>
        </table>
        <button onClick={saveUnderService} className="btn btn-primary">ذخیره</button>
    </>
}