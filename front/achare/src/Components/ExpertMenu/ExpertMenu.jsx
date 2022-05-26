import {Link} from "react-router-dom";

export default function ExpertMenu(){
    return (
        <>
            <li  className="nav-item dropdown">
                <span className="nav-link dropdown-toggle" id="navbarDropdownMenuLink" role="button"
                      data-bs-toggle="dropdown" aria-expanded="false">
                   مدیریت
                </span>
                <ul className="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                    <li className="nav-item">
                        <Link className="dropdown-item"  to={"/service"}>
                            سرویس
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link className="dropdown-item" to={"/under-service"}>
                            زیر سرویس
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link className="dropdown-item" to={"/under-service-expert"}>
                            اضافه شدن سرویس
                        </Link>
                    </li>
                    <li className="nav-item">
                        <Link className="dropdown-item" to={"/expert-offer"}>
                            مشاهده پیشنهادات
                        </Link>
                    </li>
                </ul>
            </li>
        </>
    )
}