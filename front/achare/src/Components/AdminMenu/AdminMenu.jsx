import {Link} from "react-router-dom";

export default function AdminMenu() {
    return (<>
        <li  className="nav-item dropdown">
                <span className="nav-link dropdown-toggle" id="navbarDropdownMenuLink" role="button"
                      data-bs-toggle="dropdown" aria-expanded="false">
                   مدیریت
                </span>
            <ul className="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                <li className="nav-item">
                    <Link className="dropdown-item" to={"/user-list"}>
                        لیست کاربران
                    </Link>
                </li>
                <li className="nav-item">
                    <Link  className="dropdown-item" to={"/service"}>
                        سرویس
                    </Link>
                </li>
                <li className="nav-item">
                    <Link  className="dropdown-item" to={"/under-service"}>
                        زیر سرویس
                    </Link>
                </li>
                <li className="nav-item">
                    <Link  className="dropdown-item" to={"/under-service-expert-admin"}>
                        اضافه کردن زیر سرویس
                    </Link>
                </li>
                <li className="nav-item">
                    <Link  className="dropdown-item" to={"/save-order"}>
                        ذخیره
                    </Link>
                </li>
            </ul>
        </li>
    </>)
}