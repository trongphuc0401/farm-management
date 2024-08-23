package vn.edu.likelion.farm_management.common.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Convert {
    public static synchronized LocalDateTime timeStampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }


    public static synchronized Integer longToInterger(Long l) {
        if (l == null) {
            return null;
        }
        return l.intValue();
    }
}
