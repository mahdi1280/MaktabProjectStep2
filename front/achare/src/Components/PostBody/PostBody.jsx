import {useParams} from "react-router-dom";

export default function PostBody(){
    const param=useParams();
    return <>
        <p>asdas:{param.underServiceId}</p>
    </>;
}