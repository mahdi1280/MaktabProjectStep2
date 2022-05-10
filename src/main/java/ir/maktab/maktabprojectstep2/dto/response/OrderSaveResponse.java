package ir.maktab.maktabprojectstep2.dto.response;

public class OrderSaveResponse {

    private final long id;

    public OrderSaveResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
