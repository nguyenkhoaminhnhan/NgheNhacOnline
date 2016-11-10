-- Album
CREATE TABLE Album(
	ID bigint NOT NULL IDENTITY(1000,1) PRIMARY KEY,
	Name nvarchar(256),
	DateIssue nvarchar NULL,
	Detail nvarchar(256) NULL,
	ImagePath nvarchar(256) NULL,
	CustomString1 nvarchar(256) NULL,
	CustomString2 nvarchar(256) NULL,
	CustomString3 nvarchar(256) NULL,
	CustomString4 nvarchar(256) NULL,
	CustomString5 nvarchar(256) NULL,
	CustomInt1 int DEFAULT 0,
	CustomInt2 int DEFAULT 0,
	CustomInt3 int DEFAULT 0,
	CustomInt4 int DEFAULT 0,
	CustomInt5 int DEFAULT 0,
	CustomBool1 bit,
	CustomBool2 bit,
	CustomBool3 bit,
	CustomBool4 bit,
	CustomBool5 bit,
	)
-- Bài hát
CREATE TABLE Song(
	ID bigint NOT NULL IDENTITY(1000,1) PRIMARY KEY,
	Name nvarchar(256) NULL,
	Album_ID bigint,
	Singer_ID bigint,
	Musician_ID bigint,
	ImagePath nvarchar(256) NULL,
	SourcePath nvarchar(256) NULL,
	Year nvarchar(16) NULL,
	Format varchar(10) NULL,
	BitRate int DEFAULT 0,
	Tag nvarchar(256) NULL,
	Size int DEFAULT 0,
	Length int DEFAULT 0,
	Rating float DEFAULT 0,
	CustomString1 nvarchar(256) NULL,
	CustomString2 nvarchar(256) NULL,
	CustomString3 nvarchar(256) NULL,
	CustomString4 nvarchar(256) NULL,
	CustomString5 nvarchar(256) NULL,
	CustomInt1 int DEFAULT 0,
	CustomInt2 int DEFAULT 0,
	CustomInt3 int DEFAULT 0,
	CustomInt4 int DEFAULT 0,
	CustomInt5 int DEFAULT 0,
	CustomBool1 bit,
	CustomBool2 bit,
	CustomBool3 bit,
	CustomBool4 bit,
	CustomBool5 bit,
	)
	
-- Ca sĩ
CREATE TABLE Singer(
	ID bigint NOT NULL IDENTITY(1000,1) PRIMARY KEY,
	Name nvarchar(256) NULL,
	Birthday nvarchar(256) NULL,
	Nationality nvarchar(256) NULL,
	Detail nvarchar(1024) NULL,
	ImagePath nvarchar(256) NULL,
	CustomString1 nvarchar(256) NULL,
	CustomString2 nvarchar(256) NULL,
	CustomString3 nvarchar(256) NULL,
	CustomString4 nvarchar(256) NULL,
	CustomString5 nvarchar(256) NULL,
	CustomInt1 int DEFAULT 0,
	CustomInt2 int DEFAULT 0,
	CustomInt3 int DEFAULT 0,
	CustomInt4 int DEFAULT 0,
	CustomInt5 int DEFAULT 0,
	CustomBool1 bit,
	CustomBool2 bit,
	CustomBool3 bit,
	CustomBool4 bit,
	CustomBool5 bit,
	)
	
CREATE TABLE Musician(
	ID bigint NOT NULL IDENTITY(1000,1) PRIMARY KEY,
	Name nvarchar(256) NULL,
	Birthday datetime NULL,
	Nationality nvarchar(640) NULL,
	Detail nvarchar(1000) NULL,
	ImagePath nvarchar(256) NULL,
	CustomString1 nvarchar(256) NULL,
	CustomString2 nvarchar(256) NULL,
	CustomString3 nvarchar(256) NULL,
	CustomString4 nvarchar(256) NULL,
	CustomString5 nvarchar(256) NULL,
	CustomInt1 int DEFAULT 0,
	CustomInt2 int DEFAULT 0,
	CustomInt3 int DEFAULT 0,
	CustomInt4 int DEFAULT 0,
	CustomInt5 int DEFAULT 0,
	CustomBool1 bit,
	CustomBool2 bit,
	CustomBool3 bit,
	CustomBool4 bit,
	CustomBool5 bit,
	)
	
-- Thể loại
CREATE TABLE Category(
	ID bigint NOT NULL IDENTITY(1000,1) PRIMARY KEY,
	Name nvarchar(256) NULL,
	Type nvarchar(256) NULL,
	Detail nvarchar(1000) NULL,
	ImagePath nvarchar(256) NULL,	
	CustomString1 nvarchar(256) NULL,
	CustomString2 nvarchar(256) NULL,
	CustomString3 nvarchar(256) NULL,
	CustomString4 nvarchar(256) NULL,
	CustomString5 nvarchar(256) NULL,
	CustomInt1 int DEFAULT 0,
	CustomInt2 int DEFAULT 0,
	CustomInt3 int DEFAULT 0,
	CustomInt4 int DEFAULT 0,
	CustomInt5 int DEFAULT 0,
	CustomBool1 bit,
	CustomBool2 bit,
	CustomBool3 bit,
	CustomBool4 bit,
	CustomBool5 bit,
	)
-- User
CREATE TABLE Users(
	ID bigint NOT NULL IDENTITY(1000,1) PRIMARY KEY,
	Username varchar(16) NULL,
	Password varchar(16) NULL,
	Fullname nvarchar(256) NULL,
	Email varchar(32) NULL,
	Active bit NULL,
	CustomString1 nvarchar(256) NULL,
	CustomString2 nvarchar(256) NULL,
	CustomString3 nvarchar(256) NULL,
	CustomString4 nvarchar(256) NULL,
	CustomString5 nvarchar(256) NULL,
	CustomInt1 int DEFAULT 0,
	CustomInt2 int DEFAULT 0,
	CustomInt3 int DEFAULT 0,
	CustomInt4 int DEFAULT 0,
	CustomInt5 int DEFAULT 0,
	CustomBool1 bit,
	CustomBool2 bit,
	CustomBool3 bit,
	CustomBool4 bit,
	CustomBool5 bit,
	)
	
CREATE TABLE SongCategory_junction
(
  Song_ID bigint,
  Category_ID bigint,
  CONSTRAINT song_cat_pk PRIMARY KEY (Song_ID, Category_ID),
  CONSTRAINT FK_song_SongCategory
      FOREIGN KEY (Song_ID) REFERENCES Song(ID),
  CONSTRAINT FK_category_SongCategory
      FOREIGN KEY (Category_ID) REFERENCES category (ID)
);

ALTER TABLE Song  WITH CHECK ADD  CONSTRAINT FK_Song_Album FOREIGN KEY(Album_ID)
REFERENCES Album (ID)
GO
ALTER TABLE Song CHECK CONSTRAINT FK_Song_Album
GO
ALTER TABLE Song  WITH CHECK ADD  CONSTRAINT FK_Song_Singer FOREIGN KEY(Singer_ID)
REFERENCES Singer (ID)
GO
ALTER TABLE Song CHECK CONSTRAINT FK_Song_Singer
GO
ALTER TABLE Song  WITH CHECK ADD  CONSTRAINT FK_Song_Musician FOREIGN KEY(Musician_ID)
REFERENCES Musician (ID)
GO
ALTER TABLE Song CHECK CONSTRAINT FK_Song_Musician