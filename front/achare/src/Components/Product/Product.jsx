export default function Product({workTime,id,proposedPrice,address}){
    return <div className="col-xs-6 col-md-4">
        <div className="product tumbnail thumbnail-3"><a href="#"><img
            src="https://www.avp.ir/images/article/84-2.jpg" width={350} height={280} alt=""/></a>
            <div className="caption">
                <h6><p>{address}</p><p>{address}</p></h6><span className="price">
             </span><span className="price sale">{proposedPrice}</span>
            </div>
        </div>
    </div>;
}