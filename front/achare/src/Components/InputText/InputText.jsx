export default function InputText({id,value,type,changeHandler}){
    return  <div className="form-outline mb-4">
        <label className="form-label" htmlFor={id}>{value}</label>
        <input onChange={changeHandler} type={type} id={id} className="form-control" />
    </div>
}