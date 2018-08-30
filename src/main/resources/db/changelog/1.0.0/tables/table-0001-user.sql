CREATE TABLE [USERS] (
	[ID] [numeric](19,0) NOT NULL,
	[NAME] [varchar](200) NOT NULL.
	[AGE] [numeric](19,0) NOT NULL,
	
	CONSTRAINT [PK_USERS_ID] PRIMARY_KEY CLUSTERED ([ID] ASC)
)
GO

CREATE SEQUENCE [SEQ_USERS_ID]
	AS numeric(28)
	START WITH 1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9999999999999999999999999999
	NO CYCLE
	CACHE 20
	
CREATE UNIQUE NONCLUSTERED INDEX [IDX_CK_USERS_NAME] ON [USERS]
(
	[NAME] ASC
)
GO