package lk.ijse.gdse72.findbridgelostandfoundplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private int code;
    private String status;
    private T data;
}
