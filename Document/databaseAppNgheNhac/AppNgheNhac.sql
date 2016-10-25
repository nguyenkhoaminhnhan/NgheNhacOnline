USE [AppNgheNhac]
GO
/****** Object:  Table [dbo].[Album]    Script Date: 24/10/2016 12:51:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Album](
	[Album_ID] [int] NOT NULL,
	[CaSi_ID] [int] NULL,
	[TenAlbum] [nvarchar](200) NULL,
	[NamPhatHanh] [datetime] NULL,
 CONSTRAINT [PK_Album] PRIMARY KEY CLUSTERED 
(
	[Album_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BaiHat]    Script Date: 24/10/2016 12:51:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BaiHat](
	[BH_ID] [int] NOT NULL,
	[TenBH] [nchar](10) NULL,
	[Album_ID] [int] NULL,
	[CaSi_ID] [int] NULL,
	[NhacSi_ID] [int] NULL,
	[TheLoai_ID] [int] NULL,
	[BitRate] [nchar](10) NULL,
	[Size] [nchar](10) NULL,
	[Length] [nchar](10) NULL,
	[ItemType] [nchar](10) NULL,
	[Year] [nchar](10) NULL,
	[Rating] [nchar](10) NULL,
 CONSTRAINT [PK_BaiHat] PRIMARY KEY CLUSTERED 
(
	[BH_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CaSi]    Script Date: 24/10/2016 12:51:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaSi](
	[CaSi_ID] [int] NOT NULL,
	[NgheDanh] [nvarchar](200) NULL,
	[TenThat] [nvarchar](200) NULL,
	[NamSinh] [datetime] NULL,
	[QueQuan] [nvarchar](200) NULL,
	[MoTa] [nvarchar](1000) NULL,
 CONSTRAINT [PK_CaSi] PRIMARY KEY CLUSTERED 
(
	[CaSi_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NhacSi]    Script Date: 24/10/2016 12:51:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhacSi](
	[NhacSi_ID] [int] NOT NULL,
	[NgheDanh] [nvarchar](200) NULL,
	[TenThat] [nvarchar](200) NULL,
	[NamSinh] [datetime] NULL,
	[QueQuan] [nvarchar](200) NULL,
	[MoTa] [nvarchar](1000) NULL,
 CONSTRAINT [PK_NhacSi] PRIMARY KEY CLUSTERED 
(
	[NhacSi_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[TheLoai]    Script Date: 24/10/2016 12:51:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoai](
	[TheLoai_ID] [int] NOT NULL,
	[TenTheLoai] [nvarchar](200) NULL,
	[MoTa] [nvarchar](1000) NULL,
 CONSTRAINT [PK_TheLoai] PRIMARY KEY CLUSTERED 
(
	[TheLoai_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[User]    Script Date: 24/10/2016 12:51:11 CH ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[User](
	[User_ID] [int] NOT NULL,
	[Username] [varchar](15) NULL,
	[Password] [varchar](50) NULL,
	[Fullname] [nvarchar](200) NULL,
	[Email] [varchar](25) NULL,
	[active] [bit] NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[User_ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Album]  WITH CHECK ADD  CONSTRAINT [FK_Album_CaSi] FOREIGN KEY([CaSi_ID])
REFERENCES [dbo].[CaSi] ([CaSi_ID])
GO
ALTER TABLE [dbo].[Album] CHECK CONSTRAINT [FK_Album_CaSi]
GO
ALTER TABLE [dbo].[BaiHat]  WITH CHECK ADD  CONSTRAINT [FK_BaiHat_Album] FOREIGN KEY([Album_ID])
REFERENCES [dbo].[Album] ([Album_ID])
GO
ALTER TABLE [dbo].[BaiHat] CHECK CONSTRAINT [FK_BaiHat_Album]
GO
ALTER TABLE [dbo].[BaiHat]  WITH CHECK ADD  CONSTRAINT [FK_BaiHat_CaSi] FOREIGN KEY([CaSi_ID])
REFERENCES [dbo].[CaSi] ([CaSi_ID])
GO
ALTER TABLE [dbo].[BaiHat] CHECK CONSTRAINT [FK_BaiHat_CaSi]
GO
ALTER TABLE [dbo].[BaiHat]  WITH CHECK ADD  CONSTRAINT [FK_BaiHat_NhacSi] FOREIGN KEY([NhacSi_ID])
REFERENCES [dbo].[NhacSi] ([NhacSi_ID])
GO
ALTER TABLE [dbo].[BaiHat] CHECK CONSTRAINT [FK_BaiHat_NhacSi]
GO
ALTER TABLE [dbo].[BaiHat]  WITH CHECK ADD  CONSTRAINT [FK_BaiHat_TheLoai] FOREIGN KEY([TheLoai_ID])
REFERENCES [dbo].[TheLoai] ([TheLoai_ID])
GO
ALTER TABLE [dbo].[BaiHat] CHECK CONSTRAINT [FK_BaiHat_TheLoai]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Kích thước file nhạc' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BaiHat', @level2type=N'COLUMN',@level2name=N'Size'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Thời lượng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BaiHat', @level2type=N'COLUMN',@level2name=N'Length'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'Định dạng' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BaiHat', @level2type=N'COLUMN',@level2name=N'ItemType'
GO
