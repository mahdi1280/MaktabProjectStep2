import logo from './logo.svg';
import './App.css';
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap";
function App() {
  return (
    <div className="App">
      <nav className="navbar navbar-expand-lg navbar-light bg-light">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">آچاره</a>
          <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                  aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarNavDropdown">
            <ul className="navbar-nav">
              <li className="nav-item">
                <a className="nav-link" href="#">خدمت</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="#">خدمت</a>
              </li>
              <li className="nav-item dropdown">
                <a className="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button"
                   data-bs-toggle="dropdown" aria-expanded="false">
                  خدمت ها
                </a>
                <ul className="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                  <li><a className="dropdown-item" href="#">خدمت</a></li>
                  <li><a className="dropdown-item" href="#">خدمت</a></li>
                  <li><a className="dropdown-item" href="#">خدمت</a></li>
                </ul>
              </li>
            </ul>
          </div>
          <form className="d-flex">
            <input className="form-control me-2" type="search" placeholder="جست و جو" aria-label="Search"/>
              <button className="btn btn-outline-success" type="submit">search</button>
            <button className="btn btn-sm btn-info">ورود ثبت نام</button>
          </form>
        </div>
      </nav>
    </div>
  );
}

export default App;
