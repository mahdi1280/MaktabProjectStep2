import {Link} from "react-router-dom";

export default function ExpertMenu(){
    return (
        <>
            <li className="nav-item">
                <span className="nav-link"><Link to={"/service"}>
                    سرویس
                </Link></span>
            </li>
            <li className="nav-item">
                <span className="nav-link"><Link to={"/under-service"}>
                    زیر سرویس
                </Link></span>
            </li>
            <li className="nav-item">
                <span className="nav-link"><Link to={"/under-service-expert"}>
                    اضافه شدن سرویس
                </Link></span>
            </li>
        </>
    )
}