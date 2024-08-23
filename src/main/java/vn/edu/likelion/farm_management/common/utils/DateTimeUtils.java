package vn.edu.likelion.farm_management.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * DateTimeUtils -
 *
 * @param
 * @return
 * @throws
 */
public class DateTimeUtils {
    /**
     * Chuyển một giá trị Integer đại diện cho số giây kể từ Unix epoch thành LocalDateTime.
     *
     * @param epochSeconds Số giây kể từ Unix epoch (1970-01-01T00:00:00Z).
     * @return Đối tượng LocalDateTime tương ứng.
     */
    public static LocalDateTime convertIntegerToLocalDateTime(Integer epochSeconds) {
        if (epochSeconds == null) {
            return null; // Xử lý trường hợp giá trị null
        }
        return LocalDateTime.ofEpochSecond(epochSeconds, 0, ZoneOffset.UTC);
    }
}
