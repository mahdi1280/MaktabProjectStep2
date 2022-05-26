import {Link} from "react-router-dom";

export default function AdminMenu() {
    return (<>
        <li className="nav-item">
                <span className="nav-link"><Link to={"/user-list"}>
                    لیست کاربران
                </Link></span>
        </li>
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
                <span className="nav-link"><Link to={"/under-service-expert-admin"}>
                  اضافه کردن زیر سرویس
                </Link></span>
        </li>
        <li className="nav-item">
                <span className="nav-link"><Link to={"/save-order"}>
                  ذخیره
                </Link></span>
        </li>
    </>)
}