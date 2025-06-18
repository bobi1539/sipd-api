package com.suzume.sipd.constant;

import com.suzume.sipd.constant.enums.GlobalMessage;
import com.suzume.sipd.exception.BusinessException;

public final class Constant {

    private Constant() {
        throw new BusinessException(GlobalMessage.CANNOT_INSTANCE_FINAL_CLASS);
    }

    public static final String SUCCESS = "Sukses";
    public static final String PAGE_NUMBER_NOT_VALID = "Page Number Not Valid";
    public static final String CANNOT_DELETE_THIS_DATA = "Tidak Bisa Menghapus Data Ini Karena Sudah Digunakan Oleh Entitas Lain";
    public static final String WRONG_EMAIL_OR_PASSWORD = "Email Atau Password Salah";
    public static final String REFRESH_TOKEN_NOT_VALID = "Refresh Token Tidak Valid";
    public static final String FILE_NOT_VALID = "File Tidak Valid";
    public static final String FILE_NOT_ALLOWED = "File Tidak Diizinkan";
    public static final String FILE_DOESNT_EXIST = "File Tidak Tersedia";
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String FORBIDDEN = "Forbidden";
    public static final String CANNOT_INSTANCE_FINAL_CLASS = "Cannot Instance Final Class";
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

    public static final String EMAIL_NOT_VALID = "Email Tidak Valid";
    public static final String EMAIL_REQUIRED = "Email Tidak Boleh Kosong";
    public static final String PASSWORD_REQUIRED = "Password Tidak Boleh Kosong";
    public static final String REFRESH_TOKEN_REQUIRED = "Refresh Token Tidak Boleh Kosong";
    public static final String BUDGET_NAME_REQUIRED = "Nama Anggaran Tidak Boleh Kosong";
    public static final String BUDGET_PRICE_REQUIRED = "Harga Anggaran Tidak Boleh Kosong";
    public static final String BUDGET_QUANTITY_REQUIRED = "Kuantitas Anggaran Tidak Boleh Kosong";
    public static final String TRIP_PURPOSE_REQUIRED = "Tujuan Perjalanan Dinas Tidak Boleh Kosong";
    public static final String TRIP_APPROVAL_FILE_REQUIRED = "File Persetujuan Perjalanan Dinas Tidak Boleh Kosong";
    public static final String TRIP_TYPE_REQUIRED = "Jenis Perjalanan Dinas Tidak Boleh Kosong";
    public static final String TRIP_PARTICIPANT_TYPE_REQUIRED = "Jenis Peserta Perjalanan Dinas Tidak Boleh Kosong";
    public static final String TRIP_PARTICIPANT_REQUIRED = "Peserta Perjalanan Dinas Tidak Boleh Kosong";
    public static final String TRIP_SEGMENT_REQUIRED = "Segmen Perjalanan Dinas Tidak Boleh Kosong";
    public static final String EMPLOYEE_ID_REQUIRED = "Id Karyawan Tidak Boleh Kosong";
    public static final String SEQUENCE_REQUIRED = "Urutan Tidak Boleh Kosong";
    public static final String DEPARTURE_DATE_REQUIRED = "Tanggal Berangkat Tidak Boleh Kosong";
    public static final String TRANSPORTATION_MODE_REQUIRED = "Moda Transportasi Tidak Boleh Kosong";
    public static final String DEPARTURE_CITY_ID_REQUIRED = "Id Kota Keberangkatan Tidak Boleh Kosong";
    public static final String DESTINATION_CITY_ID_REQUIRED = "Id Kota Tujuan Tidak Boleh Kosong";

    public static final String USER = "Data User";
    public static final String BUDGET = "Data Anggaran";
    public static final String CITY = "Data Kota";
    public static final String EMPLOYEE = "Data Karyawan";

    public static final String ERROR = "Error : {}";
    public static final String ID = "id";
    public static final String HEADER = "header";
    public static final String CONTENT_DISPOSITION = "inline; filename=";
    public static final String AUTHORIZATION = "Authorization";
    public static final String JWT = "JWT";
    public static final String BEARER = "Bearer";
    public static final String APPLICATION_JSON = "application/json";
    public static final String UTF_8 = "UTF-8";
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 10;
    public static final String DOT = ".";
    public static final String TEXT = "TEXT";
}
