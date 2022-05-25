export default function UserTable({children}){
    return <table className="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">نام</th>
            <th scope="col">نام خانوادگی</th>
            <th scope="col">ایمیل</th>
            <th scope="col">دسترسی</th>
            <th scope="col">امتیاز</th>
            <th scope="col">وضعیت</th>
        </tr>
        </thead>
        <tbody>
        {children}
        </tbody>
    </table>
}