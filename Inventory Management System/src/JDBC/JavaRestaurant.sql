

/*==============================================================*/
/* DBMS name:      ORACLE Version 21c                           */
/* Created on:     4/25/2024 6:20:10 PM                         */
/*==============================================================*/

create user c##javac identified by javac;

--c?p quy?n truy c?p
GRANT CONNECT,dba TO C##javac;
--Creating Sequence 'BAN_ID_SEQ'
CREATE SEQUENCE BAN_ID_SEQ
 INCREMENT BY 1
 START WITH 112
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/

--Creating Sequence 'NV_ID_SEQ'
CREATE SEQUENCE NV_ID_SEQ
 INCREMENT BY 1
 START WITH 401
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/

--Creating Sequence 'MA_ID_SEQ'
CREATE SEQUENCE MA_ID_SEQ
 INCREMENT BY 1
 START WITH 452
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/
--Creating Sequence 'HD_ID_SEQ'
CREATE SEQUENCE HD_ID_SEQ
 INCREMENT BY 1
 START WITH 637
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/
--Creating Sequence 'CTHD_ID_SEQ'
CREATE SEQUENCE CTHD_ID_SEQ
 INCREMENT BY 1
 START WITH 852
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/
--Creating Sequence 'ND_ID_SEQ'
CREATE SEQUENCE ND_ID_SEQ
 INCREMENT BY 1
 START WITH 1320
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/
--Creating Sequence 'VC_ID_SEQ'
CREATE SEQUENCE VC_ID_SEQ
 INCREMENT BY 1
 START WITH 1520
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/


--Creating Sequence 'HH_ID_SEQ'
CREATE SEQUENCE HH_ID_SEQ
 INCREMENT BY 1
 START WITH 1520
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/

--Creating Sequence 'PNK_ID_SEQ'
CREATE SEQUENCE PNK_ID_SEQ
 INCREMENT BY 1
 START WITH 1520
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/
--Creating Sequence 'PXK_ID_SEQ'
CREATE SEQUENCE PXK_ID_SEQ
 INCREMENT BY 1
 START WITH 1400
 NOMAXVALUE
 MINVALUE 1
 NOCYCLE
 NOCACHE
/
/*==============================================================*/
/* Table: BAN                                                   */
/*==============================================================*/
--drop table Ban;
create table BAN (
   MaBan NUMBER(8,0),
   TenBan varchar2(50),  
   Trangthai varchar2(50)
);
ALTER TABLE BAN MODIFY MABAN DEFAULT BAN_ID_SEQ.NEXTVAL;

--Them Check Constraint
alter table BAN
    add constraint Ban_TenBan_NNULL check ('TenBan' is not null)
    add constraint Ban_Trangthai_Ten check (Trangthai in ('C�n tr?ng','?ang ph?c v?'));
--Them khoa chinh
alter table BAN
    add constraint Ban_PK PRIMARY KEY (MaBan);

/*==============================================================*/
/* Table: CTHD                                                  */
/*==============================================================*/
create table CTHD (
   MAMONAN              NUMBER(20)            not null,
   SOHD                 NUMBER(20)            not null,
   SOLUONG              NUMBER(20)            not null,
   THANHTIEN            NUMBER(20,2)          not null,
   constraint pk_cthd primary key(SOHD,MAMONAN)
);


/*==============================================================*/
/* Table: HOADON                                                */
/*==============================================================*/
create table HOADON (
   SOHD                 NUMBER(20)            not null,
   MANV                 NUMBER(20)            not null,
   MAVC                 NUMBER(20),
   MABAN                NUMBER(20),
   NGHD                 DATE,
   TONGTIEN             NUMBER(20,2)          not null,
   TRANGTHAI            VARCHAR2(255),
   
   constraint PK_HOADON primary key (SOHD)
);
alter table hoadon modify sohd default HD_ID_SEQ.NEXTVAL;
alter table HOADON
ADD CONSTRAINT TRANGTHAI_HOADON_check CHECK (TRANGTHAI IN ('da thanh toan', 'chua thanh toan'));
/*==============================================================*/



/*==============================================================*/


/*==============================================================*/
/* Table: MONAN                                                 */
/*==============================================================*/
create table MONAN (
   MAMONAN              NUMBER(20)            not null,
   SOLUONG              NUMBER(20)            not null,
   TENMONAN             VARCHAR2(255)             not null,
   DVT                  VARCHAR2(255),
   LOAI                 VARCHAR2(255),
   GIA                  NUMBER(20,2),
   constraint PK_MONAN primary key (MAMONAN)
);
ALTER TABLE MONAN MODIFY MAMONAN DEFAULT MA_ID_SEQ.NEXTVAL;

/*==============================================================*/
/* Table: NGUOIDUNG                                             */
/*==============================================================*/
--DROP table NGUOIDUNG;
--Tao bang NguoiDung
create table NGUOIDUNG(
    userID NUMBER(8,0),
    HoTen varchar2(50),
    Matkhau varchar2(20),
    Email varchar2(50),
    SDT varchar2(20),
    VerifyCode varchar2(10)DEFAULT NULL, --Luu tru ma xac nhan
    Vaitro varchar2(20)
);
--Them rang buoc
alter table NGUOIDUNG
    add constraint ND_HoTen_NNULL check ('HoTen' is not null)
    add constraint ND_Matkhau_NNULL check ('Matkhau' is not null)
    add constraint ND_Email_NNULL check ('Email' is not null)
    add constraint ND_SDT_NNULL check ('SDT' is not null)
    add constraint ND_Vaitro_Ten check (Vaitro in ('Qu?n l�', 'Nh�n vi�n'));
--Them khoa chinh
alter table NGUOIDUNG
    add constraint NguoiDung_PK PRIMARY KEY (userID);
ALTER TABLE NGUOIDUNG MODIFY userID DEFAULT ND_ID_SEQ.NEXTVAL;


/*==============================================================*/
/* Table: NHANVIEN                                              */
/*==============================================================*/
create table NHANVIEN (
   MANV                 NUMBER(20)            not null,
   NGQL                 NUMBER(20),
   MAND                 NUMBER(20),
   HOTEN                VARCHAR2(255)             not null,
   NGSINH               DATE,
   NGVL                 DATE,
   SODT                 VARCHAR2(255),
    DIACHI              VARCHAR2(255),
   constraint PK_NHANVIEN primary key (MANV)
);
ALTER TABLE NHANVIEN MODIFY MANV DEFAULT NV_ID_SEQ.NEXTVAL;

/*==============================================================*/
/* Table: VOUCHER                                               */
/*==============================================================*/
create table VOUCHER (
   MAVC                 NUMBER(20)            not null,
   TENVC                VARCHAR2(255)             not null,
   PHANTRAM             NUMBER(20,2),
    SOLUONG              NUMBER(20),
   NGBD                 DATE,
   NGKT                 DATE,
  
   constraint PK_VOUCHER primary key (MAVC)
);
ALTER TABLE VOUCHER MODIFY MAVC DEFAULT VC_ID_SEQ.NEXTVAL;
-----------------------------------------------


/*==============================================================*/
/* Table: hanghoa                                              */
/*==============================================================*/
create table HANGHOA (
   MAHH                NUMBER(20)            not null,
   TENHH                 VARCHAR2(255) NOT NULL,
   SOLUONG                NUMBER(20),
   GIA              NUMBER(20),
   NHACUNGCAP              VARCHAR2(255),
   HSD           DATE,
   constraint PK_HANGHOA primary key (MAHH)
);
ALTER TABLE HANGHOA MODIFY MAHH DEFAULT HH_ID_SEQ.NEXTVAL;


/*==============================================================*/
/* Table: PHIEUNHAPKHO       
/*==============================================================*/
create table PHIEUNHAPKHO (
   MANK                NUMBER(20)            not null,
   NGNHAP              DATE,
   MANV               NUMBER(20),
   MAHH             NUMBER(20),
   SOLUONGNHAP            NUMBER(20),
   GIANHAP   NUMBER(20),
   constraint PK_PHIEUNHAPKHO primary key (MANK)
);
ALTER TABLE PHIEUNHAPKHO MODIFY MANK DEFAULT PNK_ID_SEQ.NEXTVAL;



/*==============================================================*/
/* Table: PHIEUXUATKHO       
/*==============================================================*/
create table PHIEUXUATKHO (
   MAXK                NUMBER(20)            not null,
   NGXUAT              DATE,
   MANV               NUMBER(20),
   MAHH             NUMBER(20),
   SOLUONGXUAT            NUMBER(20),
   GIAXUAT   NUMBER(20),
   constraint PK_PHIEUXUATKHO primary key (MAXK)
);
ALTER TABLE PHIEUXUATKHO MODIFY MAXK DEFAULT PXK_ID_SEQ.NEXTVAL;



alter table CTHD
   add constraint FK_CTHD_CTHD_MONAN foreign key (MAMONAN)
      references MONAN (MAMONAN);

alter table CTHD
   add constraint FK_CTHD_CTHD2_HOADON foreign key (SOHD)
      references HOADON (SOHD);

alter table HOADON
   add constraint FK_HOADON_AP_DUNG_VOUCHER foreign key (MAVC)
      references VOUCHER (MAVC);

alter table HOADON
   add constraint FK_HOADON_CUA_BAN foreign key (MABAN)
      references BAN (MABAN);

alter table HOADON
   add constraint FK_HOADON_TAO_NHANVIEN foreign key (MANV)
      references NHANVIEN (MANV);

--
--alter table NHANVIEN
--   add constraint FK_NHANVIEN_DUOC_CAP2_NGUOIDUN foreign key (userID)
--      references NGUOIDUNG (userID);
--
--alter table NHANVIEN
--   add constraint FK_NHANVIEN_QUAN_LY_NHANVIEN foreign key (NGQL)
--      references NHANVIEN (MANV);
      
      
      
      
alter table PHIEUNHAPKHO
   add constraint FK_PHIEUNHAPKHO_TAO_NHANVIEN foreign key (MANV)
      references NHANVIEN (MANV);
            
alter table PHIEUNHAPKHO
   add constraint FK_PHIEUNHAPKHO_CO_HANGHOA foreign key (MAHH)
      references HANGHOA (MAHH);

      
      
      
alter table PHIEUXUATKHO
   add constraint FK_PHIEUXUATKHO_TAO_NHANVIEN foreign key (MANV)
      references NHANVIEN (MANV);
            
alter table PHIEUXUATKHO
   add constraint FK_PHIEUXUATKHO_CO_HANGHOA foreign key (MAHH)
      references HANGHOA (MAHH);
      


