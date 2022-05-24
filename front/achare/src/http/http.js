import axios from "axios";

axios.defaults.baseURL = process.env.REACT_APP_BASE_URL

export function get(url,config={}){
    return axios.get(url,config);
}
export function del(url,config={}){
    return axios.delete(url,config);
}
export function put(url,data={},config={}){
    return axios.put(url,data,config);
}
export function post(url,data={},config={}){
    return axios.post(url,data,config);
}
