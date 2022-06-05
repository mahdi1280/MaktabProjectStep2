package ir.maktab.maktabprojectstep2.dto.idpyresponserequest.reponse;

public class CallbackResponsePost {

    private final int status;
    private final String trackId;
    private final String id;
    private final String OrderId;
    private final long amount;
    private final String cardNo;
    private final String hashedCardNo;

    public CallbackResponsePost(int status, String track_id, String id, String order_id, long amount, String card_no, String hashed_card_no) {
        this.status = status;
        this.trackId = track_id;
        this.id = id;
        this.OrderId = order_id;
        this.amount = amount;
        this.cardNo = card_no;
        this.hashedCardNo = hashed_card_no;
    }

    public int getStatus() {
        return status;
    }

    public String getTrackId() {
        return trackId;
    }

    public String getId() {
        return id;
    }

    public String getOrderId() {
        return OrderId;
    }

    public long getAmount() {
        return amount;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getHashedCardNo() {
        return hashedCardNo;
    }
}
