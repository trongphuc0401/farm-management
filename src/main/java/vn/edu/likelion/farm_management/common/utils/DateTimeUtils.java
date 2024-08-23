package vn.edu.likelion.farm_management.common.utils;

import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    /**
     * Chuyển một giá trị Date sang String.
     *
     * @param date Ngày kiểu Date (sql).
     * @return Đối tượng String tương ứng.
     */
    public static String convertDateToString(Date date) {
        if (date == null) {
            return null;
        }
        // Tạo đối tượng SimpleDateFormat với định dạng mong muốn
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng: 2024-08-23
        return sdf.format(date);
    }

    /**
     * Chuyển một giá trị String sang LocalDate.
     *
     * @param date Ngày kiểu String.
     * @return Đối tượng LocalDate (sql). tương ứng.
     */
    public static LocalDate convertStringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng: 2024-08-23
        try {
            // Phân tích chuỗi thành LocalDate
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            // Xử lý lỗi phân tích chuỗi ngày tháng
            throw new AppException(ErrorCode.INVALID_DATE_FORMAT);
        }
    }
}
